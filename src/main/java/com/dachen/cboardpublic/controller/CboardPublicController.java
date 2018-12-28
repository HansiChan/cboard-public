package com.dachen.cboardpublic.controller;

import com.dachen.cboardpublic.model.dto.DimensionDTO;
import com.dachen.cboardpublic.model.dto.EventDTO;
import com.dachen.cboardpublic.model.dto.IndexDTO;
import com.dachen.cboardpublic.service.CboardPublicServiceImpl;
import com.dachen.cboardpublic.util.JSONMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@Api(description = "公共组件")
public class CboardPublicController {

    @Autowired
    CboardPublicServiceImpl cboardPublicServiceImpl;

    @ApiOperation(value = "指标下拉接口",notes = "成功返回{\"data\": {},\"resultCode\": 1,\"resultMsg\": xx}",response = IndexDTO.class)
    @RequestMapping(value = "/module",method = {RequestMethod.GET})
    public JSONMessage indexList() {
        return JSONMessage.success("success",cboardPublicServiceImpl.getIndexList());
    }

    @ApiOperation(value = "維度下拉接口",notes = "成功返回{\"data\": {},\"resultCode\": 1,\"resultMsg\": xx}",response = DimensionDTO.class)
    @RequestMapping(value = "/dimension",method = {RequestMethod.POST})
    public JSONMessage dimensionList(@RequestParam String module) {
        return JSONMessage.success("success",cboardPublicServiceImpl.getDimensionList(module));
    }

    @ApiOperation(value = "事件下拉接口",notes = "成功返回{\"data\": {},\"resultCode\": 1,\"resultMsg\": xx}",response = EventDTO.class)
    @RequestMapping(value = "/event",method = {RequestMethod.GET})
    public JSONMessage eventList() {
        return JSONMessage.success("success",cboardPublicServiceImpl.getEventList());
    }

    @ApiOperation(value = "维度筛选接口",notes = "成功返回{\"data\": {},\"resultCode\": 1,\"resultMsg\": xx}",response = List.class)
    @RequestMapping(value = "/dimensionsub",method = {RequestMethod.POST})
    public JSONMessage dimensionSubList(@RequestParam String dimension) {
        return JSONMessage.success("success",cboardPublicServiceImpl.getDimensionSubList(dimension));
    }

}
