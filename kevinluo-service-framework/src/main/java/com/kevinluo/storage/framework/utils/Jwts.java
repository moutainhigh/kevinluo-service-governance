package com.kevinluo.storage.framework.utils;

/*
 * Creates on 2019/11/13.
 */

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kevinluo.storage.framework.beans.SystemErrorResult;
import com.kevinluo.storage.framework.conf.MyConfiguration;
import com.kevinluo.storage.framework.exception.BusinessException;
import com.kevinluo.storage.framework.logging.Log;
import com.kevinluo.storage.framework.logging.LogFactory;
import com.kevinluo.storage.framework.utils.collect.Maps;

import java.util.Date;
import java.util.Map;

/**
 * token验证或生成
 *
 * @author lts
 */
public class Jwts
{

  public static final String TOKEN_PREFIX = "Bearer ";

  public static final String AUTH_HEADER_KEY = "Authorization";

  private static final Log LOG = LogFactory.getLog(Jwts.class);

  private static final MyConfiguration.ConfigModel config = MyConfiguration.getInstance();

  /**
   * 生成token
   *
   * @param claims token中的内容
   * @return token
   */
  public static String genToken(Map<String, Object> claims)
  {
    // 设置过期时间
    Date exp = new Date(System.currentTimeMillis() + (config.getJwtExpiresSecond() * 1000));
    JWTCreator.Builder builder = JWT.create()
            .withHeader(Maps.newHashMap("alg", "HS256", "typ", "JWT"));
    for (Map.Entry<String, Object> claim : claims.entrySet())
    {
      builder.withClaim(claim.getKey(), String.valueOf(claim.getValue()));
    }
    builder.withExpiresAt(exp)
            .withIssuedAt(new Date());
    return builder.sign(Algorithm.HMAC256(config.getJwtBase64Secret()));
  }

  /**
   * 验证token是否正确或过期
   *
   * @param token token
   */
  public static Map<String, Claim> verifyToken(String token)
  {
    try
    {
      Assert.loginTokenNonNull(token);
      JWTVerifier verifier = JWT.require(Algorithm.HMAC256(config.getJwtBase64Secret())).build();
      DecodedJWT jwt = verifier.verify(token);
      return Assert.loginTokenNonNull(jwt.getClaims());
    } catch (Exception e)
    {
      throw new BusinessException(SystemErrorResult.TOKEN_EXP);
    }
  }

}
