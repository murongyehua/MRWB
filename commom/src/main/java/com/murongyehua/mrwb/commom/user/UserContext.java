package com.murongyehua.mrwb.commom.user;

import cn.hutool.core.lang.Assert;

/**
 * @author liul
 * @version 1.0 2020/6/16
 */
public class UserContext {

    private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public static final String USER_SESSION = "loginUser";

    public static void setUserInfo(UserInfo userInfo) {
        threadLocal.set(userInfo);
    }

    public static UserInfo getUserInfo() {
        UserInfo userInfo = threadLocal.get();
        Assert.notNull(userInfo, "无法获取登录用户信息！");
        return userInfo;
    }

    public static boolean hasUserInfo() {
        UserInfo userInfo = threadLocal.get();
        return userInfo != null;
    }

    public static String getUserId() {
        return getUserInfo().getUserId();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
