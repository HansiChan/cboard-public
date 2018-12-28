package com.dachen.cboardpublic.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class EventDTO {
    @ApiModelProperty(dataType = "String", name = "event", notes = "事件")
    private String event;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
