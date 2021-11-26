package com.demo.xyz.common.core;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author lengleng
 */
@Data
public class R<T> implements Serializable {
    private static final long serialVersionUID = -5663717945191277844L;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * ID
     */
    private String uId;

    public R() {
        this.code = 200;
    }

    public R(final String message) {
        this(null, message, null);
    }

    public R(final Integer code, final String message) {
        this(code, message, null);
    }

    public R(final Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public R(final T data) {
        this(data, null);
    }

    public R(final T data, final String message) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    @Deprecated
    public R(Throwable e) {
        this.code = 500;
        this.message = e.getMessage();
    }

    /**
     * 响应
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> R<T> error(final int code, final String msg) {
        return new R(code, msg);
    }

    /**
     * 响应
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> R<T> error(final String msg) {
        return new R(msg);
    }

    /**
     * 成功结果
     *
     * @return
     */
    public static R success() {
        return new R(0, "success", null);
    }

    /**
     * 获取成功后的接口
     *
     * @param response
     * @param <T>
     * @return
     */
    public static <T> T getSuccessData(R<T> response) {
        if (response == null) {
            throw new ServiceException(500,"系统异常");
        }
        final boolean isSuccess = R.isSuccess(response);
        if (isSuccess) {
            return response.getData();
        }
        throw new ServiceException(response.getCode(), response.getMessage());
    }

    /**
     * 是否成功
     *
     * @param response
     * @return
     */
    public static boolean isSuccess(R response) {
        if (response == null) {
            return false;
        }
        if (Objects.equals(200, response.getCode())) {
            return true;
        }
        return false;
    }
}
