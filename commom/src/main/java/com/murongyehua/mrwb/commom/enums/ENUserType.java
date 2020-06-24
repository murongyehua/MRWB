package com.murongyehua.mrwb.commom.enums;

import lombok.Getter;

/**
 * @author liul
 * @version 1.0 2020/6/17
 */
@Getter
public enum ENUserType {

    /**
     * 用户类型
     */
    NORMAL("0","普通用户"),
    MANAGER("1","管理员");

    private String value;
    private String label;

    ENUserType(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getLabelByValue(String value) {
        if (value == null) {
            return null;
        }
        ENUserType[] enUserTypes = ENUserType.values();
        for (ENUserType enUserType : enUserTypes) {
            if (enUserType.value.equals(value)) {
                return enUserType.label;
            }
        }
        return value;
    }
}
