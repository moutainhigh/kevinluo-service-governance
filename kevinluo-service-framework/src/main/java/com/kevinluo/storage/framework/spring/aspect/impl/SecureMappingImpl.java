package com.kevinluo.storage.framework.spring.aspect.impl;

/*
 * Creates on 2019/11/13.
 */

import com.auth0.jwt.interfaces.Claim;
import com.kevinluo.storage.framework.common.ConstVariable;
import com.kevinluo.storage.framework.utils.Charsets;
import com.kevinluo.storage.framework.utils.Jwts;
import com.kevinluo.storage.framework.utils.web.WebRequest;
import com.kevinluo.storage.framework.utils.web.WebResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author lts
 */
@Aspect
@Component
public class SecureMappingImpl
{

  @Around("execution(public * *(..)) && @annotation(com.kevinluo.storage.framework.spring.aspect.SecureMapping)")
  public Object handle(ProceedingJoinPoint pjp) throws Throwable {
    HttpServletRequest request = WebRequest.getHttpServletRequest();
    HttpServletResponse response = WebResponse.getHttpServletResponse();

    response.setCharacterEncoding(Charsets.UTF_8.displayName());

    // 获取header中的token
    String token = request.getHeader(ConstVariable.AUTHORIZATION);

    // 除了OPTIONS外, 其他应该有JWT检查
    if (ConstVariable.OPTIONS.equals(request.getMethod()))
    {
      response.setStatus(HttpServletResponse.SC_OK);
    } else
    {
      Map<String, Claim> claims = Jwts.verifyToken(token);
      request.setAttribute(ConstVariable.USER_ID, claims.get(ConstVariable.USER_ID).asString());
      request.setAttribute(ConstVariable.USERNAME, claims.get(ConstVariable.USERNAME).asString());
      request.setAttribute(ConstVariable.NICKNAME, claims.get(ConstVariable.NICKNAME).asString());
    }
    return pjp.proceed();
  }

}
