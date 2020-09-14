package com.kevinluo.storage.framework.logging;

/*
 * creates on 2020/5/11 20:10.
 */

/**
 * 日志适配器，可以在多个日志框架中来回切换
 *
 * @author lts
 */
public interface LogAdapter {
  Log getLog(String key);
  Log getLog(Class<?> key);
}
