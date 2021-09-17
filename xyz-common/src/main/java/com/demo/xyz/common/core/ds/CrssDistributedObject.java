package com.demo.xyz.common.core.ds;

import com.demo.xyz.common.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 分布式对象
 *
 * @author charles.ouyang
 */
@Slf4j
public class CrssDistributedObject<T> {

  private RedissonClient _redisson;

  public static final String DATA_KEY = "{key}:data";

  public CrssDistributedObject(RedissonClient redisson) {

    this._redisson = redisson;

  }

  /**
   * 实现分布式wait操作
   *
   * @param key          redis存储的key
   * @param count        同步等待次数
   * @param timeout      等待超时时间
   * @param isReturnData notify操作是否会返回数据
   * @return
   */
  public T doWait(WaitAction waitAction, String key, int count, long timeout, boolean isReturnData)
      throws InterruptedException, ExecutionException {
    RCountDownLatch latch = null;
    long t1 = System.currentTimeMillis();
    final String dataKey = getDataKey(key);
    try {
      latch = this._redisson.getCountDownLatch(key);
      if (count < 1) {
        count = 1;
      }
      log.info("设置latch,key:{},count:{},timeout:{}ms", key, count, timeout);
      latch.trySetCount(count);
      RFuture<Boolean> future = latch.awaitAsync(timeout, TimeUnit.MILLISECONDS);
      if (waitAction != null) {
        try {
          waitAction.execute();
        } catch (Exception ex) {
          ex.printStackTrace();
          throw new ServiceException("waitAction.execute执行异常:", ex);
        }
      }
      boolean result = future.get();
      log.info("latch wakeup,key:{},count:{},result:{}", key, latch.getCount(), result);
      if (isReturnData) {
        RBucket<T> bucket = this._redisson.getBucket(dataKey);
        T data = bucket.get();
        log.info("doWait读取返回的数据，key:{},dataKey:{},data:{}", key, dataKey, data);
        return data;
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
      log.error("doWait中断异常,key：{}", key, e);
      throw e;

    } finally {
      long t2 = System.currentTimeMillis();
      log.info("doWait耗时统计，key:{},cost:{} ms", key, (t2 - t1));
      if (latch != null) {
        log.info("删除latch,latch:{}", latch);
        latch.delete();
      }
      if (isReturnData) {
        log.info("删除latch关联的bucket数据,key:{},dataKey:{}", key, dataKey);
        this._redisson.getBucket(dataKey).delete();
      }
    }

    return null;
  }

  /**
   * 实现分布式对象通知
   *
   * @param key          和wait对应的通知key
   * @param isReturnData 通知是否携带数据
   * @param data         携带的json数据
   * @param dataTtl      携带的数据过期ms
   */
  public void doNotify(String key, boolean isReturnData, T data, Long dataTtl) {

    // 存储终端上报数据
    if (isReturnData) {
      String dataKey = getDataKey(key);
      RBucket<T> bucket = _redisson.getBucket(dataKey);
      if (dataTtl == null) {
        dataTtl = -1L;
      }
      bucket.set(data, dataTtl, TimeUnit.MILLISECONDS);
    }
    this._redisson.getCountDownLatch(key).countDown();
  }

  private String getDataKey(String key) {

    return DATA_KEY.replace("{key}", key);

  }

  public static interface WaitAction {

    public void execute();

  }


}
