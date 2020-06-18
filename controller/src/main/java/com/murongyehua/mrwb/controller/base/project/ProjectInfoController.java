package com.murongyehua.mrwb.controller.base.project;

import com.murongyehua.mrwb.api.param.ProjectParam;
import com.murongyehua.mrwb.api.req.ProjectAddReq;
import com.murongyehua.mrwb.base.service.project.ProjectInfoService;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liul
 * @version 1.0 2020/6/18
 */
@RestController
@RequestMapping("/base/project")
public class ProjectInfoController {

    @Autowired
    private ProjectInfoService projectInfoService;

    @PostMapping("/add.smg")
    public ResultContext addProject(ProjectAddReq req) {
        return projectInfoService.addProject(req);
    }

    @PostMapping("/query.pub")
    public PageView queryProject(ProjectParam param) {
        return projectInfoService.queryProject(param);
    }
}
