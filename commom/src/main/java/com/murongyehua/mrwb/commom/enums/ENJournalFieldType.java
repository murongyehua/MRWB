package com.murongyehua.mrwb.commom.enums;

/**
 * @author liul
 * @version 1.0 2020/6/29
 */
public enum ENJournalFieldType {

    /**
     * 日志字段类型
     */
    INPUT("1","输入框"),
    RADIO("2","单选框"),
    CHECKBOX("3","复选框"),
    TEXTAREA("4","文本域");

    private String value;
    private String label;

    ENJournalFieldType(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getLabelByValue(String value) {
        if (value == null) {
            return null;
        }
        ENJournalFieldType[] eNJournalFieldTypes = ENJournalFieldType.values();
        for (ENJournalFieldType enJournalFieldType : eNJournalFieldTypes) {
            if (enJournalFieldType.value.equals(value)) {
                return enJournalFieldType.label;
            }
        }
        return value;
    }

}
