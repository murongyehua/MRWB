package com.murongyehua.mrwb.base.service.modal;

import com.murongyehua.mrwb.api.param.ModalParam;
import com.murongyehua.mrwb.api.req.ModalAddReq;
import com.murongyehua.mrwb.api.req.ModalInProjectReq;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;

/**
 * @author liul
 * @version 1.0 2020/6/28
 */
public interface ModalService {

    PageView queryList(ModalParam param);

    ResultContext getModalsByPorject(ModalInProjectReq req);

    ResultContext addModal(ModalAddReq req);

}
