package com.dachen.cboardpublic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dachen.cboardpublic.util.JSONMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/test")
@Api(description = "测试")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	
	@ApiOperation(value = "测试",notes = "成功返回{\"data\": {},\"resultCode\": 1,\"resultMsg\": xx}")
    @RequestMapping(value = "/testSwagger/{id}",method = {RequestMethod.GET})
    public JSONMessage testSwagger(@PathVariable String id) {
		logger.info("testSwagger id-{}",id);
		return JSONMessage.success("success",id);
    }
}