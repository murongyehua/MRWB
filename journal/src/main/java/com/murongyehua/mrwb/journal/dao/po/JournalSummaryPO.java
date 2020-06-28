package com.murongyehua.mrwb.journal.dao.po;

import java.io.Serializable;
import java.util.Date;

/**
 * journal_summary
 * @author 
 */
public class JournalSummaryPO implements Serializable {
    private String id;

    private String projectId;

    private String dealUser;

    private String dealDate;

    private String state;

    private String attach;

    private Date lastModifyTime;

    private String createUser;

    private Date createTime;

    private String historyForId;

    private String tagId;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHistoryForId() {
        return historyForId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public void setHistoryForId(String historyForId) {
        this.historyForId = historyForId;
    }

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

    public String getDealUser() {
        return dealUser;
    }

    public void setDealUser(String dealUser) {
        this.dealUser = dealUser;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
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

    public String getVersion() {
        return historyForId;
    }

    public void setVersion(String historyForId) {
        this.historyForId = historyForId;
    }
}