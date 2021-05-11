package com.daoyun;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashMap;

public class JWTtest {
    @Test
    void contextLoads() {
        //创建一个日历
        Calendar instance = Calendar.getInstance();//获取当前时间
        instance.add(Calendar.SECOND,300);//在当前时间后面加上30秒
        //创建一个Token
        String token = JWT.create()
                .withClaim("userName", "xiaochen")//playload
                .withClaim("userId", 21)
                .withExpiresAt(instance.getTime())//指定令牌过期时间
                .sign(Algorithm.HMAC256("1021cqq"));// 签名
        System.out.println(token);
    }
    @Test
    public void test(){
        //创建验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("1021cqq")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6InhpYW9jaGVuIiwiZXhwIjoxNjIwMzAzNjg2LCJ1c2VySWQiOjIxfQ.iL0eaTJ9VjWzNl8un-qcXNG-ZRpEFKzN6TCD94AXpk8");
        System.out.println(verify.getClaim("userName").asString());
        System.out.println(verify.getClaim("userId").asInt());
    }
}
