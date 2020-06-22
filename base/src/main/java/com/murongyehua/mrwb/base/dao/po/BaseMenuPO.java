package com.murongyehua.mrwb.base.dao.po;

import java.io.Serializable;

/**
 * base_menu
 * @author 
 */
public class BaseMenuPO implements Serializable {
    private String id;

    private String icon;

    private String routeIndex;

    private String title;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRouteIndex() {
        return routeIndex;
    }

    public void setRouteIndex(String routeIndex) {
        this.routeIndex = routeIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}