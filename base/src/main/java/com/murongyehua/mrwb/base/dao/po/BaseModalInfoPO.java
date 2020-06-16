package com.murongyehua.mrwb.base.dao.po;

import java.io.Serializable;
import java.util.Date;

/**
 * base_modal_info
 * @author 
 */
public class BaseModalInfoPO implements Serializable {
    private String id;

    private String name;

    private String modalState;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModalState() {
        return modalState;
    }

    public void setModalState(String modalState) {
        this.modalState = modalState;
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