package com.daoyun.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    private static final String SIGNATURE = "1021caiqiquan";
    /**
     * 生成token header.playload.signature
     */
    public static String getToken(Map<String, String> map){
        //创建一个日历
        Calendar instance = Calendar.getInstance();//获取当前时间
        instance.add(Calendar.DATE,7);//默认七天过期
        //创建jwtBuilder
        JWTCreator.Builder builder = JWT.create();
        //加载playload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        //指定过期时间
        builder.withExpiresAt(instance.getTime());
        String token = builder.sign(Algorithm.HMAC256(SIGNATURE));
        return token;
    }

    /**
     * 验证token
     */
    public static DecodedJWT verify(String token){
        //有问题会抛出异常
        return JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }
}
