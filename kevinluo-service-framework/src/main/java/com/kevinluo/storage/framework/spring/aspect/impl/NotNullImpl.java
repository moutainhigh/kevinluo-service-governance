package com.kevinluo.storage.framework.spring.aspect.impl;

/*
 * Creates on 2020/5/15.
 */

import com.kevinluo.storage.framework.beans.ApiJsonTemplate;
import com.kevinluo.storage.framework.beans.HttpCode;
import com.kevinluo.storage.framework.spring.aspect.NotNull;
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
public class NotNullImpl {

  @SuppressWarnings({"ConstantConditions"})
  @Around("execution(public * *(..)) && @annotation(com.kevinluo.storage.framework.spring.aspect.NotNull)")
  public Object handle(ProceedingJoinPoint pjp) throws Throwable {
    ApiJsonTemplate template = new ApiJsonTemplate();
    Map<String, Object> fieldMap = Aspects.getFields(pjp);
    NotNull notNull = Aspects.getAnnotation(pjp, NotNull.class);
    String[] values = notNull.value();
    if (values.length != 0) {
      for (String value : values) {
        if (fieldMap.get(value) == null) {
          template.setJsonResult(HttpCode.STATUS_400, value + "参数不能为空");
          return template;
        }
      }
    } else {
      for (Map.Entry<String, Object> field : fieldMap.entrySet()) {
        if (field.getValue() == null) {
          template.setJsonResult(HttpCode.STATUS_400, field.getKey() + "参数不能为空");
          return template;
        }
      }
    }
    return pjp.proceed();
  }

}