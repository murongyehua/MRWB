package com.murongyehua.mrwb.commom.enums;

import lombok.Getter;

/**
 * @author liul
 * @version 1.0 2020/6/30
 */
@Getter
public enum  ENJournalState {

    /**
     * 通用状态枚举
     */
    ACTIVE("0","正常"),
    HISTORY("1","历史版本"),
    WAIT_DELETE("2", "待删除");

    private String value;
    private String label;

    ENJournalState(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getLabelByValue(String value) {
        if (value == null) {
            return null;
        }
        ENJournalState[] enJournalStates = ENJournalState.values();
        for (ENJournalState enJournalState : enJournalStates) {
            if (enJournalState.value.equals(value)) {
                return enJournalState.label;
            }
        }
        return value;
    }

}
