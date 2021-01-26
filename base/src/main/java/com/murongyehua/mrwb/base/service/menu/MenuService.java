package com.murongyehua.mrwb.base.service.menu;

import com.murongyehua.mrwb.api.param.MenuParam;
import com.murongyehua.mrwb.api.req.MenuAddReq;
import com.murongyehua.mrwb.api.req.MenuGetReq;
import com.murongyehua.mrwb.api.req.ModalAddReq;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liul
 * @version 1.0 2020/6/22
 */
public interface MenuService {

    ResultContext getMenu(MenuGetReq req, HttpServletRequest request);

    PageView queryList(MenuParam param);

    ResultContext addMenu(MenuAddReq req);

}
