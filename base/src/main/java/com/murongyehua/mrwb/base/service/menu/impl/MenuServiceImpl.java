package com.murongyehua.mrwb.base.service.menu.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.murongyehua.mrwb.api.param.MenuParam;
import com.murongyehua.mrwb.api.req.MenuAddReq;
import com.murongyehua.mrwb.api.req.MenuGetReq;
import com.murongyehua.mrwb.api.req.ModalAddReq;
import com.murongyehua.mrwb.api.resp.MenuListResp;
import com.murongyehua.mrwb.api.resp.ModalListResp;
import com.murongyehua.mrwb.base.dao.mapper.BaseMenuMapper;
import com.murongyehua.mrwb.base.dao.mapper.BaseModalMenuMapper;
import com.murongyehua.mrwb.base.dao.mapper.BaseUserRightMapper;
import com.murongyehua.mrwb.base.dao.po.BaseMenuPO;
import com.murongyehua.mrwb.base.dao.po.BaseModalInfoPO;
import com.murongyehua.mrwb.base.dao.po.BaseModalMenuPO;
import com.murongyehua.mrwb.base.dao.po.BaseUserRightPO;
import com.murongyehua.mrwb.base.service.menu.MenuService;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.enums.ENCommonState;
import com.murongyehua.mrwb.commom.enums.ENUserType;
import com.murongyehua.mrwb.commom.user.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liul
 * @version 1.0 2020/6/22
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private BaseMenuMapper menuMapper;

    @Autowired
    private BaseUserRightMapper rightMapper;

    @Autowired
    private BaseModalMenuMapper modalMenuMapper;

    private final static String MANAGER_MENU_PARAM = "systemManage";

    @Override
    public ResultContext getMenu(MenuGetReq req, HttpServletRequest request) {
        String userType = UserContext.getUserInfo().getUserType();
        ResultContext resultContext = ResultContext.success("操作成功");
        if (ENUserType.MANAGER.getValue().equals(userType)) {
            BaseMenuPO param = new BaseMenuPO();
            param.setRouteIndex(MANAGER_MENU_PARAM);
            resultContext.setData(menuMapper.selectBySelective(param));
            return resultContext;
        }else {
            // 通过项目和用户获取模块
            BaseUserRightPO userRightPO = new BaseUserRightPO();
            userRightPO.setUserId(UserContext.getUserId());
            userRightPO.setProjectId(req.getProject());
            List<BaseUserRightPO> list = rightMapper.selectBySelective(userRightPO);
            if (CollectionUtil.isEmpty(list)) {
                return ResultContext.businessFail("权限不足");
            }
            List<String> modalIds = list.stream().map(BaseUserRightPO::getModalId).collect(Collectors.toList());
            // 根据模块获取菜单
            List<BaseModalMenuPO> modalMenuPOS = modalMenuMapper.selectByModalIds(modalIds);
            if (CollectionUtil.isEmpty(modalMenuPOS)) {
                return ResultContext.businessFail("系统异常, 请联系管理员");
            }
            request.getSession().setAttribute(UserContext.PROJECT_SESSION, req.getProject());
            UserContext.setProjectThreadLocal(req.getProject());
            resultContext.setData(menuMapper.selectByIds(modalMenuPOS.stream().map(BaseModalMenuPO::getMenuId).collect(Collectors.toList())));
            return resultContext;
        }
    }

    @Override
    public PageView queryList(MenuParam param) {
        BaseMenuPO menuPO = new BaseMenuPO();
        BeanUtil.copyProperties(param, menuPO);
        PageHelper.startPage(param.getPageNum(), param.getPageSize(), param.getOrderBy());
        PageInfo pageInfo = new PageInfo(this.translate(menuMapper.selectBySelective(menuPO)));
        PageView pageView = new PageView();
        BeanUtil.copyProperties(ResultContext.success("操作成功"), pageView);
        pageView.setRows(pageInfo.getList());
        pageView.setTotal(pageInfo.getTotal());
        return pageView;
    }

    public ResultContext addMenu (MenuAddReq req){
        if (isMenuExist(req.getTitle())) {
            return ResultContext.businessFail("菜单已存在");
        }
        BaseMenuPO menuPO = new BaseMenuPO();
        menuPO.setTitle(req.getTitle());
        menuPO.setId(IdUtil.simpleUUID());
        menuPO.setIcon(req.getIcon());
        menuPO.setRouteIndex(req.getRouteIndex());
        int count = menuMapper.insert(menuPO);
        if (count != 1) {
            return ResultContext.businessFail("新增失败");
        }
        return ResultContext.success("新增成功");
    }

    private List<MenuListResp> translate(List<BaseMenuPO> list) {
        List<MenuListResp> result = new ArrayList<>();
        int num = 1;
        for (BaseMenuPO menuPO : list) {
            MenuListResp menuListResp = new MenuListResp();
            BeanUtil.copyProperties(menuPO, menuListResp);
            menuListResp.setIndex(num);
            result.add(menuListResp);
            num++;
        }
        return result;
    }

    private boolean isMenuExist(String title) {
        BaseMenuPO baseMenuPO = new BaseMenuPO();
        baseMenuPO.setTitle(title);
        List<BaseMenuPO> list = menuMapper.selectBySelective(baseMenuPO);
        if (CollectionUtil.isNotEmpty(list)) {
            return true;
        }
        return false;
    }

}
