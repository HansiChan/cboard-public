package com.dachen.cboardpublic.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class IndexDTO {
    @ApiModelProperty(dataType = "String", name = "module", notes = "指标")
    private String module;
    @ApiModelProperty(dataType = "String", name = "text", notes = "描述")
    private String text;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
