package com.murongyehua.mrwb.base.service.modal;

import com.murongyehua.mrwb.api.param.ModalParam;
import com.murongyehua.mrwb.commom.PageView;

/**
 * @author liul
 * @version 1.0 2020/6/28
 */
public interface ModalService {

    PageView queryList(ModalParam param);

}
