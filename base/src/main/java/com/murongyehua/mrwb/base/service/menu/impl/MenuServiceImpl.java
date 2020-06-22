package com.murongyehua.mrwb.base.service.menu.impl;

import com.murongyehua.mrwb.api.req.MenuGetReq;
import com.murongyehua.mrwb.base.dao.mapper.BaseMenuMapper;
import com.murongyehua.mrwb.base.dao.po.BaseMenuPO;
import com.murongyehua.mrwb.base.service.menu.MenuService;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.enums.ENUserType;
import com.murongyehua.mrwb.commom.user.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liul
 * @version 1.0 2020/6/22
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private BaseMenuMapper menuMapper;

    private final static String MANAGER_MENU_PARAM = "systemManage";

    @Override
    public ResultContext getMenu(MenuGetReq req) {
        String userType = UserContext.getUserInfo().getUserType();
        if (ENUserType.MANAGER.getValue().equals(userType)) {
            BaseMenuPO param = new BaseMenuPO();
            param.setRouteIndex(MANAGER_MENU_PARAM);
            ResultContext resultContext = ResultContext.success("操作成功");
            resultContext.setData(menuMapper.selectBySelective(param));
            return resultContext;
        }
        return ResultContext.businessFail("暂不支持的用户类型");
    }

}
