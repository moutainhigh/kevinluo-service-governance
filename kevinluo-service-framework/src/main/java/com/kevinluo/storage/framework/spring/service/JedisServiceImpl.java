package com.kevinluo.storage.framework.spring.service;

/*
 * Creates on 2019/11/13.
 */

import com.kevinluo.storage.framework.logging.Log;
import com.kevinluo.storage.framework.logging.LogFactory;
import com.kevinluo.storage.framework.utils.DateUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author lts
 */
@Service
public class JedisServiceImpl implements JedisService
{

  @Resource
  private RedisTemplate<String, Object> redisTemplate;

  private static final Log mLog = LogFactory.getLog(JedisServiceImpl.class);

  @Override
  public void set(String key, String value)
  {
    set(key, value, DateUtils.DAY);
  }

  @Override
  public void set(String key, String value, long timeMillis)
  {
    set(key, value, timeMillis, TimeUnit.MILLISECONDS);
  }

  @Override
  public void set(String key, String value, long timeMillis, TimeUnit timeUnit)
  {
    redisTemplate.opsForValue().set(key, value, timeMillis, timeUnit);
  }

  @Override
  public String getString(String key)
  {
    return String.valueOf(redisTemplate.opsForValue().get(key));
  }

  @Override
  public Integer getInteger(String key)
  {
    return Integer.valueOf(getString(key));
  }

  @Override
  public Long getLong(String key)
  {
    return Long.valueOf(getString(key));
  }

  @Override
  public Boolean getBoolean(String key)
  {
    return Boolean.valueOf(getString(key));
  }

  @Override
  public BigDecimal getBigDecimal(String key)
  {
    return new BigDecimal(getString(key));
  }

  @Override
  public void remove(String key)
  {
    redisTemplate.delete(key);
  }

}
