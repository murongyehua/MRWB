package com.murongyehua.mrwb.base.service.menu;

import com.murongyehua.mrwb.api.param.MenuParam;
import com.murongyehua.mrwb.api.req.MenuGetReq;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;

/**
 * @author liul
 * @version 1.0 2020/6/22
 */
public interface MenuService {

    ResultContext getMenu(MenuGetReq req);

    PageView queryList(MenuParam param);
}
