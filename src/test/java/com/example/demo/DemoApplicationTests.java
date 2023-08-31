//package com.example.demo;
//
//import com.syswin.systoon.util.HashUtils;
//import com.syswin.systoon.util.HttpXmlClient;
//import com.syswin.systoon.util.SignUtil;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.util.LinkedHashMap;
//
//@SpringBootTest
//class DemoApplicationTests {
//
//    @Test
//    public void getPrivateKey() throws IOException {
//        // 组装参数
//        LinkedHashMap<String, String> params = new LinkedHashMap<>(16);
//        params.put("client_id", "100100000469");
//        params.put("client_secret", "447ec388977b3ad3a866028a8657683a");
//        params.put("sign_type", "MD5");// 支持SM3
//        params.put("char_set", "UTF-8");
//        params.put("time_stamp", String.valueOf(System.currentTimeMillis()));
//        params.put("sign", HashUtils.md5Hash(SignUtil.getSignContent(params)));
//        params.remove("client_secret");
//
//        String jsonStr = HttpXmlClient.httpGet("https://bjt.beijing.gov.cn/renzheng/open/api/validatePv", params);
//        System.out.println("私钥：" + jsonStr);
//    }
//
//}
