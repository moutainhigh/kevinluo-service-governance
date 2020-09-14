package com.kevinluo.storage.framework.logging.jdk;

import com.kevinluo.storage.framework.logging.Log;
import com.kevinluo.storage.framework.logging.LogAdapter;

/*
 * creates on 2020/5/11 20:53.
 */

/**
 * @author lts
 */
public class JdkAdapter implements LogAdapter
{
	public Log getLog(Class<?> key) {
		return new JdkLog(java.util.logging.Logger.getLogger(key == null ? "" : key.getName()));
	}
	public Log getLog(String key) {
		return new JdkLog(java.util.logging.Logger.getLogger(key));
	}
}
