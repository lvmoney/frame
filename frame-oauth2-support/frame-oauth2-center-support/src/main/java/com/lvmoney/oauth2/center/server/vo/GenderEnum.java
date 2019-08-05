package com.lvmoney.oauth2.center.server.vo;

public enum GenderEnum {

    MALE("男"),
    FEMALE("女"),
    UNKNOWN("未知");

    private String meaning;

    public String getMeaning() {
        return meaning;
    }

    GenderEnum() {
    }

    GenderEnum(String meaning) {
        this.meaning = meaning;
    }
}
