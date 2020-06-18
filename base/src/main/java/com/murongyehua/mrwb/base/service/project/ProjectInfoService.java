package com.murongyehua.mrwb.base.service.project;

import com.murongyehua.mrwb.api.param.ProjectParam;
import com.murongyehua.mrwb.api.req.ProjectAddReq;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;

/**
 * @author liul
 * @version 1.0 2020/6/18
 */
public interface ProjectInfoService {

    ResultContext addProject(ProjectAddReq req);

    PageView queryProject(ProjectParam param);

}
