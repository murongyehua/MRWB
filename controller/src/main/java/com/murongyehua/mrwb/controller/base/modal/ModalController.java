package com.murongyehua.mrwb.controller.base.modal;

import com.murongyehua.mrwb.api.param.ModalParam;
import com.murongyehua.mrwb.api.req.ModalInProjectReq;
import com.murongyehua.mrwb.base.service.modal.ModalService;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liul
 * @version 1.0 2020/6/28
 */
@RestController
@RequestMapping("/base/modal")
public class ModalController {

    @Autowired
    private ModalService modalService;

    @PostMapping("/query.smg")
    public PageView query(ModalParam param) {
        return modalService.queryList(param);
    }

    @PostMapping("/getModalsByProject.smg")
    public ResultContext getModalsByProject(ModalInProjectReq req) {
        return modalService.getModalsByPorject(req);
    }

}
