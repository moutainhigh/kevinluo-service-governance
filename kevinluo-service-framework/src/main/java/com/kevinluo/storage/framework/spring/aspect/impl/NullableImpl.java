package com.kevinluo.storage.framework.spring.aspect.impl;

/*
 * Creates on 2020/5/15.
 */

import com.kevinluo.storage.framework.beans.ApiJsonTemplate;
import com.kevinluo.storage.framework.beans.HttpCode;
import com.kevinluo.storage.framework.spring.aspect.Nullable;
import com.kevinluo.storage.framework.utils.reflect.Aspects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lts
 */
@Aspect
@Component
public class NullableImpl {

  @SuppressWarnings({"ConstantConditions"})
  @Around("execution(public * *(..)) && @annotation(com.kevinluo.storage.framework.spring.aspect.Nullable)")
  public Object handle(ProceedingJoinPoint pjp) throws Throwable {
    Map<String, Object> fieldMap = Aspects.getFields(pjp);
    Nullable nullable = Aspects.getAnnotation(pjp, Nullable.class);
    String[] values = nullable.value();
    if(values.length != 0) {
      for (String value : values) {
        if(fieldMap.get(value) == null) {
          return new ApiJsonTemplate(HttpCode.STATUS_400, value + "参数不能为空");
        }
      }
    }
    return pjp.proceed();
  }

}