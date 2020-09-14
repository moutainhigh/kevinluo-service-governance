package com.kevinluo.storage.framework.spring.config;

/*
 * Creates on 2020/5/13.
 */

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lts
 */
@Configuration
@SuppressWarnings("Convert2Lambda")
public class MyBatisPlusConfiguration {
  //自动识别数据库类型
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }

  //pagehelper分页插件
  @Bean
  public ConfigurationCustomizer configurationCustomizer() {
    return new ConfigurationCustomizer() {
      @Override
      public void customize(MybatisConfiguration configuration) {
        configuration.addInterceptor(new PageInterceptor());
      }
    };
  }

}