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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}