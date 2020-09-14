package com.kevinluo.storage.framework.spring.aspect;

/*
 * Creates on 2019/11/13.
 */

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * 使用SecureMapping映射的路径会进行JWT TOKEN认证
 *
 * @author lts
 */
@Documented
@RequestMapping
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecureMapping
{

  @AliasFor(annotation = RequestMapping.class)
  String name() default "";

  @AliasFor(annotation = RequestMapping.class)
  String[] value() default {};

  @AliasFor(annotation = RequestMapping.class)
  String[] path() default {};

  @AliasFor(annotation = RequestMapping.class)
  String[] params() default {};

  @AliasFor(annotation = RequestMapping.class)
  RequestMethod[] method() default {};

  @AliasFor(annotation = RequestMapping.class)
  String[] headers() default {};

  @AliasFor(annotation = RequestMapping.class)
  String[] consumes() default {};

  @AliasFor(annotation = RequestMapping.class)
  String[] produces() default {};

}
