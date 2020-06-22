package com.murongyehua.mrwb.base.dao.po;

import java.io.Serializable;

/**
 * base_modal_menu
 * @author 
 */
public class BaseModalMenuPO implements Serializable {
    private String id;

    private String modalId;

    private String menuId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModalId() {
        return modalId;
    }

    public void setModalId(String modalId) {
        this.modalId = modalId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}