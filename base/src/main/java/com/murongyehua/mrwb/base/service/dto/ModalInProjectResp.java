package com.murongyehua.mrwb.base.service.dto;

import com.murongyehua.mrwb.base.dao.po.BaseModalInfoPO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author liul
 * @version 1.0 2020/7/31
 */
@Getter
@Setter
public class ModalInProjectResp {

    List<BaseModalInfoPO> modalInfos;

    List<String> distributedModalIds;

}
