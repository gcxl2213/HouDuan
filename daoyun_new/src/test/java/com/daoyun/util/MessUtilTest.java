package com.daoyun.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessUtilTest {
    @Test
    public void sendMsTest(){
        MessUtil.sendMess("15161133605", "1234");
    }
}