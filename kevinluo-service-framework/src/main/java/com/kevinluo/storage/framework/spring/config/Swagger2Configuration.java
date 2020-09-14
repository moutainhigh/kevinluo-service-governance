package com.kevinluo.storage.framework.spring.config;

/*
 * Creates on 2019/11/13.
 */

import com.kevinluo.storage.framework.common.ConstVariable;
import com.kevinluo.storage.framework.conf.MyConfiguration;
import com.kevinluo.storage.framework.utils.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lts
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration // extends WebMvcConfigurationSupport
{

  private final MyConfiguration.ConfigModel config = MyConfiguration.getInstance();

  @Bean
  public Docket createRestApi()
  {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
            .paths(PathSelectors.any()).build()
            .securitySchemes(securitySchemes())
            .securityContexts(securityContexts());
  }

  private ApiInfo apiInfo()
  {
    return new ApiInfoBuilder()
            .title("文件存储服务器开放API")
            .description("文件存储服务器开放API")
            .version("v1.0.0")
            .contact(new Contact("kevinluo", "storage.kevinluo.com", "kevinluoa@foxmail.com"))
            .license("Apache License2.0")
            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
            .build();
  }

  private List<ApiKey> securitySchemes()
  {
    return Lists.newArrayList(
            new ApiKey(ConstVariable.AUTHORIZATION, ConstVariable.AUTHORIZATION, "header")
    );
  }


  private List<SecurityContext> securityContexts() {
    List<SecurityContext> securityContexts=Lists.newArrayList();
    securityContexts.add(
            SecurityContext.builder()
                    .securityReferences(defaultAuth())
                    .forPaths(PathSelectors.regex("^(?!auth).*$"))
                    .build());
    return securityContexts;
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    List<SecurityReference> securityReferences=new ArrayList<>();
    securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
    return securityReferences;
  }

}
