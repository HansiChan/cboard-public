package com.dachen.cboardpublic.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class DimensionDTO {
    @ApiModelProperty(dataType = "String", name = "dimension_sub", notes = "維度")
    private String dimension_sub;
    @ApiModelProperty(dataType = "String", name = "text", notes = "描述")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDimension_sub() {
        return dimension_sub;
    }

    public void setDimension_sub(String dimension_sub) {
        this.dimension_sub = dimension_sub;
    }
}
