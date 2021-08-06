package com.demo.xyz.demo.util;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc 提取集合属性数据的工具类
 * @author alexx.xu
 * @date 2019年8月14日
 * @version 1.0
 */
@Slf4j
public class CollectionUtil {
	
	
	/**
	　* <p>Title: convert2Map<／p>
	　* <p>Description: 转化为以集合中的对象元素的某个属性值为KEY,以该集合元素为Value的Map  <／p>
	　* @param list
	　* @param propName
	　* @return
	　*/
	public static <K,V> Map<K,V> convert2Map(Collection<V> list,String propName){
		
		Map<K,V> map=new HashMap<>();
		if(Strings.isNullOrEmpty(propName)){
			return map;
		}
		if(list!=null && !list.isEmpty()){
			for(V v:list){
				try{
					K k=(K) PropertyUtils.getProperty(v, propName);
					map.put(k, v);
				}catch(Exception e){
					log.error(e.getMessage(),e);
				}
			}
		}
		
		return map;
	}
	
	/**
	　* <p>Title: convert2ListByProperty<／p>
	　* <p>Description: 转化为以集合中的对象元素的某个属性值为Value的List<／p>
	　* @param list
	　* @param propName
	　* @return
	　*/
	public static <K,V> List<K> convert2ListByProperty(Collection<V> list,String propName){
		
		List<K> retList=new ArrayList<>();
		if(Strings.isNullOrEmpty(propName)){
			return retList;
		}
		if(list!=null && !list.isEmpty()){
			for(V v:list){
				try{
					K k=(K) PropertyUtils.getProperty(v, propName);
					retList.add(k);
				}catch(Exception e){
					log.error(e.getMessage(),e);
				}
			}
		}
		
		return retList;
	}

	/**
	　* <p>Title: contains<／p>
	　* <p>Description: 判断字符数组是否包含有某个字符串<／p>
	　* @param src
	　* @param ele
	　* @return
	　*/
	public static boolean contains(String[] src,String ele){
		boolean result=false;
		if(src!=null && src.length>0 && !Strings.isNullOrEmpty(ele)){
			result=Arrays.asList(src).contains(ele);
		}
		
		return result;
	}
	


}
