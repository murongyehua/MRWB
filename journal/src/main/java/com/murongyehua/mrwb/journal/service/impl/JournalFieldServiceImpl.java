package com.murongyehua.mrwb.journal.service.impl;

import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.user.UserContext;
import com.murongyehua.mrwb.journal.dao.mapper.JournalFieldMapper;
import com.murongyehua.mrwb.journal.dao.po.JournalFieldPO;
import com.murongyehua.mrwb.journal.service.JournalFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liul
 * @version 1.0 2020/6/28
 */
@Service
public class JournalFieldServiceImpl implements JournalFieldService {

    @Autowired
    private JournalFieldMapper fieldMapper;

    @Override
    public ResultContext getFieldByProjectId() {
        List<JournalFieldPO> list = fieldMapper.selectByProject(UserContext.getProjectId());
        ResultContext resultContext = ResultContext.success("操作成功");
        resultContext.setData(list);
        return resultContext;
    }
}
