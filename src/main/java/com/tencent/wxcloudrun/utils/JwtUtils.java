package com.tencent.wxcloudrun.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tencent.wxcloudrun.exception.BizException;
import com.tencent.wxcloudrun.exception.CommonEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class JwtUtils {


    public static final String ISSUER = "Test.com";
    //Audience
    public static final String AUDIENCE = "Client";
    //密钥
    public static final String KEY = "ThisIsMySecretKey";
    //算法
    public static final Algorithm ALGORITHM = Algorithm.HMAC256(JwtUtils.KEY);

    /**
     签发对象：这个用户的id
     签发时间：现在
     有效时间：2小时
     载荷内容：暂时设计为：这个人的名字，这个人的昵称
     加密密钥：这个人的id加上一串字符串
     */
    public static String createToken(String userId,String realName, String userName) {

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,2*60);
        Date expiresDate = nowTime.getTime();

        return JWT.create().withAudience(userId)   //签发对象
                .withIssuedAt(new Date())    //发行时间
                .withIssuer(JwtUtils.ISSUER)
                .withExpiresAt(expiresDate)  //有效时间
                .withClaim("userName", userName)    //载荷，随便写几个都可以
                .withClaim("realName", realName)
                .sign(Algorithm.HMAC256(JwtUtils.KEY));   //加密
    }


    /**
     * 检验合法性，其中secret参数就应该传入的是用户的id
     * @param token
     */
    public static void verifyToken(String token){
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JwtUtils.KEY)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            //效验失败
            //这里抛出的异常是我自定义的一个异常，你也可以写成别的
            throw new BizException(CommonEnum.TOKEN_CHECK_FAILED);
        }
    }

    /**
     * 获取签发对象
     */
    public static String getAudience(String token)  {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            //这里是token解析失败
            throw new BizException(CommonEnum.TOKEN_PARSING_FAILED);
        }
        return audience;
    }


    /**
     * 通过载荷名字获取载荷的值
     */
    public static Claim getClaimByName(String token, String name){
        return JWT.decode(token).getClaim(name);
    }

}
