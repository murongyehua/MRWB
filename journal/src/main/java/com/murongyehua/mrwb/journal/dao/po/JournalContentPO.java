package com.murongyehua.mrwb.journal.dao.po;

import java.io.Serializable;

/**
 * journal_content
 * @author 
 */
public class JournalContentPO implements Serializable {
    private String id;

    private String summaryId;

    private String fieldId;

    private String content;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}