package com.supplies.interceptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;

import com.supplies.joint.develop.redis.RedisStringCache;
import com.supplies.utils.RedisUtil;

/**
 * 切面 MethodCacheInterceptor,这是用来给不同的方法加入判断如果缓存存在数据,从缓存取数据,否则第一次从数据库取,并将结果保存到缓存中取.
 * @author AQ
 *
 */
public class MethodCacheInterceptor implements MethodInterceptor{

	private RedisUtil redisUtil;
	private List<String> targetNamesList;//不加入缓存的service名称
	private List<String> methodNameList;//不加入缓存的方法名称
	private Long defaultCacheExpireTime;//默认缓存的过期时间
	private Long xxxRecordManagerTime;//
	private Long xxxSetRecordManagerTime;//
	private boolean autoCache = true;
	@Autowired
	private RedisStringCache redisStringCache;
	
	
	
	public MethodCacheInterceptor() {
		try {
			InputStream in =  this.getClass().getClassLoader().getResourceAsStream("redis.properties");
			Properties p = new Properties();
			p.load(in);
			//分割字符串
			String[] targetNames = p.getProperty("targetNames").split(",");
			String[] methodNames = p.getProperty("methodNames").split(",");
			//加载过期时间设置
			defaultCacheExpireTime = Long.valueOf(p.getProperty("defalutCacheExpireTime"));
			xxxRecordManagerTime = Long.valueOf(p.getProperty("com.service.impl.xxxRecordManager"));
			xxxSetRecordManagerTime = Long.valueOf(p.getProperty("com.service.impl.xxxSetRecordManager"));
			//创建list
			targetNamesList = new ArrayList<String>(targetNames.length);
			methodNameList = new ArrayList<String>(methodNames.length);
			Integer maxLen = targetNames.length > methodNames.length ? targetNames.length:methodNames.length;
			//将不需要缓存的类名和方法名添加到list中
			for (int i = 0; i < maxLen; i++) {
				if(i < targetNames.length){
					targetNamesList.add(targetNames[i]);
				}
				if(i < methodNames.length){
					methodNameList.add(methodNames[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		if(!autoCache){
			return invocation.proceed();
		}
		Object value = null;
		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		//不需要缓存的内容
		if(!isAddCache(targetName,methodName)){
			//执行结果返回结果集
			return invocation.proceed();
		}
		Object[] arguments = invocation.getArguments();
		String key = getCacheKey(targetName, methodName, arguments);
		System.out.println(key);
		try {
			//判断是否有缓存			
			if(redisStringCache.exists(key)){
				System.out.println("KEY存在");
				Object obj = redisStringCache.get(key);
				return obj;
			}
			System.out.println("KEY不存在");
			//写入缓存
			value = invocation.proceed();
			if(value != null){
				final String tkey = key;
				final Object tvalue = value;
				new Thread(new Runnable() {
					@Override
					public void run() {
						if(tkey.startsWith("com.service.impl.xxxRecordManager")){
							//redisUtil.set(tkey, tvalue, xxxRecordManagerTime);
							redisStringCache.set(tkey, tvalue, xxxRecordManagerTime, TimeUnit.MINUTES);
						}else if(tkey.startsWith("com.service.impl.xxxSetRecordManager")){
							//redisUtil.set(tkey, tvalue, xxxSetRecordManagerTime);
							redisStringCache.set(tkey, tvalue, xxxSetRecordManagerTime, TimeUnit.MINUTES);
						}else{
							//redisUtil.set(tkey, tvalue, defaultCacheExpireTime);
							redisStringCache.set(tkey, tvalue, defaultCacheExpireTime, TimeUnit.MINUTES);
						}
						
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(value == null){
				return invocation.proceed();
			}
		}
		return value;
	}
	
	
	
	/**
	 * 是否加入缓存
	 * @param targetNamme
	 * @param methodName
	 * @return
	 */
	public boolean isAddCache(String targetName,String methodName){
		boolean flag = true;
		if(targetNamesList.contains(targetName) || methodNameList.contains(methodName)){
			flag =  false;
		}
		return flag;
	}
	
	
	/**
	 * 创建缓存key
	 * @param targetName
	 * @param methodName
	 * @param arguments
	 * @return
	 */
	private String getCacheKey(String targetName,String methodName,Object[] arguments){
		StringBuilder sb = new StringBuilder();
		sb.append(targetName).append("_").append(methodName);
		if((arguments != null) && (arguments.length != 0)){
			for (int i = 0; i < arguments.length; i++) {
				sb.append("_").append(arguments[i]);
			}
		}
		return sb.toString();
	}


	public void setRedisUtil(RedisUtil redisUtil) {
		this.redisUtil = redisUtil;
	}
	public boolean isAutoCache() {
		return autoCache;
	}
	public void setAutoCache(boolean autoCache) {
		this.autoCache = autoCache;
	}

	
	
}
