package com.murongyehua.mrwb.controller.base.userright;

import com.murongyehua.mrwb.base.service.userright.UserRightService;
import com.murongyehua.mrwb.commom.ResultContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liul
 * @version 1.0 2020/6/24
 */
@RestController
@RequestMapping("/base/userRight")
public class UserRightController {

    @Autowired
    private UserRightService userRightService;

    @PostMapping("/getProjects.do")
    public ResultContext getProjects() {
        return userRightService.getProjects();
    }

}
