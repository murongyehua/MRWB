package com.murongyehua.mrwb.base.dao.po;

import java.io.Serializable;
import java.util.Date;

/**
 * base_modal_in_project
 * @author 
 */
public class BaseModalInProjectPO implements Serializable {
    private String id;

    private String projectId;

    private String modalId;

    private String createUser;

    private Date createTime;

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

    public String getModalId() {
        return modalId;
    }

    public void setModalId(String modalId) {
        this.modalId = modalId;
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
}