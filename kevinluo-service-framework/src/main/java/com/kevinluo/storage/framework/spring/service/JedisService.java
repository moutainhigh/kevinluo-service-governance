package com.kevinluo.storage.framework.spring.service;

/*
 * Creates on 2019/11/13.
 */

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author lts
 */
public interface JedisService
{

  /**
   * 获取和配置项有关的缓存
   */
  static String getConfig(String key)
  {
    return "config_".concat(key);
  }

  /**
   * 获取和验证码有关的缓存
   */
  static String getVerifyCode(String key)
  {
    return "verifyc_".concat(key);
  }

  /**
   * 添加缓存，默认存在时间24小时
   */
  void set(String key, String value);

  /**
   * 添加缓存，并设置过期时间
   */
  void set(String key, String value, long timeMillis);

  /**
   * 添加缓存，并设置过期时间
   */
  void set(String key, String value, long timeMillis, TimeUnit timeUnit);

  /**
   * 将获取到的内容转换为String
   */
  String getString(String key);

  /**
   * 将获取到的内容转换为Integer
   */
  Integer getInteger(String key);

  /**
   * 将获取到的内容转换为Long
   */
  Long getLong(String key);

  /**
   * 将获取到的内容转换为Boolean
   */
  Boolean getBoolean(String key);

  /**
   * 将获取到的内容转换为BigDecimal
   */
  BigDecimal getBigDecimal(String key);

  /**
   * 删除key
   */
  void remove(String key);

}
