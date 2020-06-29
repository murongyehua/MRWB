package com.murongyehua.mrwb.commom.user;

import cn.hutool.core.lang.Assert;

/**
 * @author liul
 * @version 1.0 2020/6/16
 */
public class UserContext {

    private static ThreadLocal<UserInfo> userThreadLocal = new ThreadLocal<>();

    public static final String USER_SESSION = "loginUser";

    public static final String PROJECT_SESSION = "projectSession";

    public static void setUserInfo(UserInfo userInfo) {
        userThreadLocal.set(userInfo);
    }

    public static UserInfo getUserInfo() {
        UserInfo userInfo = userThreadLocal.get();
        Assert.notNull(userInfo, "无法获取登录用户信息！");
        return userInfo;
    }

    public static boolean hasUserInfo() {
        UserInfo userInfo = userThreadLocal.get();
        return userInfo != null;
    }

    public static String getUserId() {
        return getUserInfo().getUserId();
    }

    public static void remove() {
        userThreadLocal.remove();
    }

    public static void setProjectThreadLocal(String projectId) {
        UserInfo userInfo = userThreadLocal.get();
        userInfo.setProjectId(projectId);
        userThreadLocal.set(userInfo);
    }

    public static String getProjectId() {
        String projectId = userThreadLocal.get().getProjectId();
        Assert.notNull(projectId, "无法获取项目信息！");
        return projectId;
    }
}
