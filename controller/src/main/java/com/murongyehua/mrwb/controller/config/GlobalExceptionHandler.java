package com.murongyehua.mrwb.controller.config;

import cn.hutool.core.map.MapUtil;
import com.murongyehua.mrwb.commom.enums.ENMsgCode;
import com.murongyehua.mrwb.commom.exception.MRBaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) {
        //SkywalkingErrorTracer.error(ex);
        log.error("系统异常", ex);
        String errInfo = "系统异常";
        String errCode = ENMsgCode.SYSTEM_EXCEPTION.getValue();
        if (ex instanceof IllegalArgumentException || ex instanceof MRBaseException) {
            errInfo = ex.getMessage();
            errCode = ENMsgCode.BUSINESS_ERROR.getValue();
        }
        Map<String, Object> attributes = MapUtil.newHashMap(5);
        attributes.put("info", errInfo);
        attributes.put("code", errCode);
        attributes.put("success", false);
        attributes.put("data", null);
        attributes.put("value", null);

        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setAttributesMap(attributes);
        ModelAndView modelView = new ModelAndView();
        modelView.setView(view);
        return modelView;
    }
}
