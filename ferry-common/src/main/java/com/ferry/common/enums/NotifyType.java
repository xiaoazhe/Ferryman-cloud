package com.ferry.common.enums;


import lombok.Getter;

@Getter
public enum NotifyType {

    SEND_NOTIFY("发送通知"),

    RECEIVED_NOTIFY("收到通知");

    private String type;

    NotifyType(String type) {
        this.type = type;
    }
}
