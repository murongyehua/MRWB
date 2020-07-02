package com.murongyehua.mrwb.controller.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.murongyehua.mrwb.commom.enums.ENUserType;
import com.murongyehua.mrwb.commom.user.UserContext;
import com.murongyehua.mrwb.commom.user.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 用户上下文加载拦截器
 *
 * @author xuesl
 * @date 2018/6/20
 */
@Slf4j
public class UserContextInterceptor extends HandlerInterceptorAdapter {

    private static final String NOT_LOG_IN_MESSAGE = "未登录";

    private static final String NOT_RIGHT_MESSAGE = "无权限";

    private static final String INTERCEPTOR_OPER = ".pub";

    private static final String SYSTEM_MANAGER_OPER = ".smg";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //复杂请求的OPTIONS预校验直接通过
        if(request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return true;
        }
        String servletPath = request.getServletPath();

        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(UserContext.USER_SESSION);
        if (userInfo == null) {
            // 未登录时只有.pub放行，其他全部拦截
            if (!servletPath.contains(INTERCEPTOR_OPER)) {
                response.setContentType("application/json");
                response.getWriter().write(getErrorReturn(NOT_LOG_IN_MESSAGE).toJSONString());
                return false;
            }
        } else {
            // .smg只有系统管理员放行
            if (servletPath.contains(SYSTEM_MANAGER_OPER) && !ENUserType.MANAGER.getValue().equals(userInfo.getUserType())) {
                response.setContentType("application/json");
                response.getWriter().write(getErrorReturn(NOT_RIGHT_MESSAGE).toJSONString());
                return false;
            }
            UserContext.setUserInfo(userInfo);
        }
        return true;
    }

    private JSONObject getErrorReturn(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "2");
        jsonObject.put("success", false);
        jsonObject.put("info", message);
        return jsonObject;
    }
}
