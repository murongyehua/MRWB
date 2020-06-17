package com.murongyehua.mrwb.commom.enums;

import lombok.Getter;

/**
 * @author liul
 * @version 1.0 2020/6/17
 */
@Getter
public enum ENCommonState {
    /**
     * 通用状态枚举
     */
    ACTIVE("0","正常"),
    STOP_USE("1","停用或冻结");

    private String value;
    private String label;

    ENCommonState(String value, String label) {
        this.value = value;
        this.label = label;
    }

}
