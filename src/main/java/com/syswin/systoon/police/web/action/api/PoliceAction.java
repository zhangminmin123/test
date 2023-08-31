//package com.syswin.systoon.police.web.action.api;
//
//import com.alibaba.fastjson.JSON;
//import com.syswin.systoon.framework.bean.ResponseResult;
//import com.syswin.systoon.police.police.AuthenticationResultDto;
//import com.syswin.systoon.police.web.action.BaseAction;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@Slf4j
//public class PoliceAction extends BaseAction {
//
//
//	@Value("${status}")
//	private Integer status ;
//
//	@RequestMapping(value = "/checkCard", method = RequestMethod.POST)
//	public ResponseResult<AuthenticationResultDto> checkCard(String bizParams) throws Exception {
//		//正常结果
//		if(status.equals(1)){
//			String result = "{" +
//					"  \"appName\":\"syswin\"," +
//					"  \"authResult\":\"01XX\"," +
//					"  \"authResultRetainData\":\"{\\\"rxfs\\\":\\\"252.52\\\"}\"," +
//					"  \"businessSerialNumber\":\"443C2E521311130226\"," +
//					"  \"customNumber\":\"ywzd212\"," +
//					"  \"errorDesc\":\"\"," +
//					"  \"success\":\"true\"," +
//					"  \"timeStamp\":\"20230218191719986\"" +
//					" }";
//			AuthenticationResultDto authenticationResultDto = JSON.parseObject(result, AuthenticationResultDto.class);
//			return ResponseResult.success(authenticationResultDto);
//		}
//		// 超时
//		if(status.equals(2)){
//			Thread.sleep(10000);
//			return ResponseResult.success(null);
//		}
//		return ResponseResult.success(null);
//	}
//
//
//}
