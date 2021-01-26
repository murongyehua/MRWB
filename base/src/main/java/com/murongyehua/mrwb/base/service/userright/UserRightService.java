package com.murongyehua.mrwb.base.service.userright;

import com.murongyehua.mrwb.api.req.UserRightReq;
import com.murongyehua.mrwb.commom.ResultContext;

/**
 * @author liul
 * @version 1.0 2020/6/24
 */
public interface UserRightService {

    ResultContext getProjects();

    ResultContext getUsers();

    ResultContext addUserRight(UserRightReq req);
}
