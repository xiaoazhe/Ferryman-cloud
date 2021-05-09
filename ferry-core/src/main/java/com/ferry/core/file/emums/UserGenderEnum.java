package com.ferry.core.file.emums;


public enum UserGenderEnum {
    MALE(1, "男"), FEMALE(0, "女"), UNKNOW(-1, "");
    private int code;
    private String desc;

    UserGenderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static UserGenderEnum getUserSex(Integer code) {
        if (code == null) {
            return UNKNOW;
        }
        for (UserGenderEnum userSexEnum : UserGenderEnum.values()) {
            if (userSexEnum.getCode() == code) {
                return userSexEnum;
            }
        }
        return UNKNOW;
    }

    public static UserGenderEnum getUserSex(String code) {
        if (code == null) {
            return UNKNOW;
        }
        if("m".equals(code)){
            return MALE;
        }
        if("f".equals(code)){
            return FEMALE;
        }
        return UNKNOW;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
