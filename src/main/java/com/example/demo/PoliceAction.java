package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.syswin.systoon.police.police.AuthenticationResultDto;
import com.syswin.systoon.police.web.action.BaseAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
public class PoliceAction extends BaseAction {


    @Value("${status}")
    private Integer status;

    private Integer requestNumber = 1;

    @RequestMapping(value = "/checkCard", method = RequestMethod.POST)
    public String checkCard(String bizParams) throws Exception {

        //增加模拟 0.1 比例报错
        testSentinel();

        if(status.equals(4)){
            if(requestNumber % 10 == 0 || requestNumber % 10 == 1){
                System.out.println("增加模拟 0.2 比例报错....."  + requestNumber);
                return null;
            }
        }

        log.info("status ----> " + status);
        //正常结果
        if (status.equals(1)) {
            String result = "{" +
                    "  \"appName\":\"syswin\"," +
                    "  \"authResult\":\"00XX\"," +
                    "  \"authResultRetainData\":\"{\\\"rxfs\\\":\\\"252.52\\\"}\"," +
                    "  \"businessSerialNumber\":\"443C2E521311130226\"," +
                    "  \"customNumber\":\"ywzd212\"," +
                    "  \"errorDesc\":\"\"," +
                    "  \"success\":\"true\"," +
                    "  \"timeStamp\":\"20230218191719986\"" +
                    " }";
            AuthenticationResultDto authenticationResultDto = JSON.parseObject(result, AuthenticationResultDto.class);
            return result;
        }
        // 超时
        if (status.equals(2)) {
            Thread.sleep(20000);
            return null;
        }
        //不通过
        if (status.equals(3)) {
            String result = "{" +
                    "  \"appName\":\"syswin\"," +
                    "  \"authResult\":\"51XX\"," +
                    "  \"authResultRetainData\":\"{\\\"rxfs\\\":\\\"252.52\\\"}\"," +
                    "  \"businessSerialNumber\":\"443C2E521311130226\"," +
                    "  \"customNumber\":\"ywzd212\"," +
                    "  \"errorDesc\":\"\"," +
                    "  \"success\":\"true\"," +
                    "  \"timeStamp\":\"20230218191719986\"" +
                    " }";
            AuthenticationResultDto authenticationResultDto = JSON.parseObject(result, AuthenticationResultDto.class);
            return result;
        }
        return null;
    }

    public synchronized void testSentinel() {
        requestNumber++;

    }

    ///identity/checkIdentity
    @RequestMapping(value = "/identity/checkIdentity", method = RequestMethod.POST)
    public String checkIdentity(String bizParams) throws Exception {
        System.out.println("identity/checkIdentity");
        return "{\"status\":200,\"message\":\"成功\",\"trace\":\"\",\"data\":{\"resultCode\":200,\"resultMessage\":\"验证成功\",\"userTransId\":\"fc5dd151-fde8-373f-977b-959064ec2d22\",\"transId\":\"ID2680355276729118720\"}}";
    }

    @RequestMapping(value = "/identityFace/checkIdface", method = RequestMethod.POST)
    public String checkIdface(String bizParams) throws Exception {
        System.out.println("identity/checkIdface");
        return "{\"status\":200,\"message\":\"成功\",\"trace\":\"\",\"data\":{\"resultCode\":200,\"resultMessage\":\"验证成功\",\"userTransId\":\"fc5dd151-fde8-373f-977b-959064ec2d22\",\"transId\":\"ID2680355276729118720\"}}";
    }

    // /bankcard/checkBankcardFour
    @RequestMapping(value = "/bankcard/checkBankcardFour", method = RequestMethod.POST)
    public String checkBankcardFour(String bizParams) throws Exception {
        System.out.println("identity/checkBankcardFour");
        return "{\"status\":200,\"message\":\"成功\",\"trace\":\"\",\"data\":{\"resultCode\":200,\"resultMessage\":\"验证成功\",\"userTransId\":\"fc5dd151-fde8-373f-977b-959064ec2d22\",\"transId\":\"ID2680355276729118720\"}}";
    }


    @RequestMapping(value = "/checkCardGAT", method = RequestMethod.POST)
    public String checkCardGAT(String bizParams) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("result", "2");
        return "{\"status\":200,\"message\":\"成功\",\"trace\":\"\",\"data\":{\"resultCode\":200,\"resultMessage\":\"验证成功\",\"userTransId\":\"fc5dd151-fde8-373f-977b-959064ec2d22\",\"transId\":\"ID2680355276729118720\"}}";
    }
}
