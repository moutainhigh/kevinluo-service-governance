package com.kevinluo.storage.framework.spring.aspect;

/*
 * Creates on 2020/5/15.
 */

import java.lang.annotation.*;

/**
 * 被注解的请求指定参数可为空，其他参数不能为空
 *
 * @author lts
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Nullable {

  /**
   * 设置的参数不能为空其余的都可为空，默认代表所有参数都可以为空
   */
  String[] value() default {};

}