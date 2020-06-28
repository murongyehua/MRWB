package com.murongyehua.mrwb.journal.dao.po;

import java.io.Serializable;
import java.util.Date;

/**
 * journal_field
 * @author 
 */
public class JournalFieldPO implements Serializable {
    private String id;

    private String projectId;

    private String fieldName;

    private String fieldType;

    private String fieldContent;

    private String maxLength;

    private String sortnum;

    private String createUser;

    private Date createTime;

    private Integer version;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldContent() {
        return fieldContent;
    }

    public void setFieldContent(String fieldContent) {
        this.fieldContent = fieldContent;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public String getSortnum() {
        return sortnum;
    }

    public void setSortnum(String sortnum) {
        this.sortnum = sortnum;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}