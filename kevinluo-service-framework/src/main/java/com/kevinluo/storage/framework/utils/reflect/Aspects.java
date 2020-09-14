package com.kevinluo.storage.framework.utils.reflect;

/*
 * Creates on 2020/5/15.
 */

import com.kevinluo.storage.framework.utils.collect.Maps;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author lts
 */
public class Aspects
{

  /**
   * 获取切面目标对象的的注解
   */
  public static <A extends Annotation> A getAnnotation(ProceedingJoinPoint pjp, Class<A> annotation){
    Method method = getMethod(pjp);
    A a = Annotations.isAnnotation(method, annotation);
    if(a == null) return null;
    return a;
  }

  /**
   * 获取目标对象的方法对象
   */
  public static Method getMethod(ProceedingJoinPoint pjp){
    try {
      MethodSignature signature = (MethodSignature) pjp.getSignature();
      Class<?> target = pjp.getTarget().getClass();
      return target.getMethod(signature.getName(),signature.getParameterTypes());
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 获取目标对象的名称和值
   */
  public static Map<String, Object> getFields(ProceedingJoinPoint pjp) {
    Map<String, Object> fieldMap = Maps.newHashMap();
    MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
    String[] names = methodSignature.getParameterNames();
    Object[] values = pjp.getArgs();
    for(int i=0; i<names.length; i++) {
      fieldMap.put(names[i], values[i]);
    }
    return fieldMap;
  }

}