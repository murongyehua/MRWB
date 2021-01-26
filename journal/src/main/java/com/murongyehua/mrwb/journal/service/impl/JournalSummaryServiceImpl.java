package com.murongyehua.mrwb.journal.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.murongyehua.mrwb.api.param.journal.JournalSummaryParam;
import com.murongyehua.mrwb.api.req.journal.JournalSummaryReq;
import com.murongyehua.mrwb.api.resp.journal.JournalFieldContentResp;
import com.murongyehua.mrwb.api.resp.journal.JournalSummaryQueryResp;
import com.murongyehua.mrwb.base.dao.mapper.BaseUserInfoMapper;
import com.murongyehua.mrwb.commom.MRConstant;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.enums.ENJournalState;
import com.murongyehua.mrwb.commom.enums.ENMsgCode;
import com.murongyehua.mrwb.commom.exception.MRBaseException;
import com.murongyehua.mrwb.commom.user.UserContext;
import com.murongyehua.mrwb.journal.dao.mapper.JournalContentMapper;
import com.murongyehua.mrwb.journal.dao.mapper.JournalFieldMapper;
import com.murongyehua.mrwb.journal.dao.mapper.JournalSummaryMapper;
import com.murongyehua.mrwb.journal.dao.mapper.JournalTagMapper;
import com.murongyehua.mrwb.journal.dao.po.JournalContentPO;
import com.murongyehua.mrwb.journal.dao.po.JournalFieldPO;
import com.murongyehua.mrwb.journal.dao.po.JournalSummaryPO;
import com.murongyehua.mrwb.journal.service.JournalSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liul
 * @version 1.0 2020/6/30
 */
@Service
public class JournalSummaryServiceImpl implements JournalSummaryService {

    @Autowired
    private JournalSummaryMapper summaryMapper;

    @Autowired
    private JournalFieldMapper fieldMapper;

    @Autowired
    private JournalContentMapper contentMapper;

    @Autowired
    private BaseUserInfoMapper userInfoMapper;

    @Autowired
    private JournalTagMapper tagMapper;

    @Value("${export.path}")
    private String exportPath;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultContext addSummary(JournalSummaryReq addReq, boolean isCheckDup) {
        if (isCheckDup) {
            // 判重
            JournalSummaryPO tempPo = new JournalSummaryPO();
            tempPo.setTitle(addReq.getTitle());
            tempPo.setState(ENJournalState.ACTIVE.getValue());
            List<JournalSummaryPO> tempList = summaryMapper.selectBySelective(tempPo);
            if (CollectionUtil.isNotEmpty(tempList)) {
                throw new MRBaseException("标题重复");
            }
        }
        // 添加汇总表
        JournalSummaryPO journalSummaryPO = this.req2JournalSummaryPo(addReq);
        int summaryCount = summaryMapper.insert(journalSummaryPO);
        if (summaryCount != 1) {
            throw new MRBaseException("操作失败");
        }
        // 添加字段内容表
            // 获取项目字段
            List<JournalFieldPO> fieldPOS = fieldMapper.selectByProject(UserContext.getProjectId());
            if (CollectionUtil.isEmpty(fieldPOS)) {
                throw new MRBaseException("记录字段未配置");
            }
            List<JournalContentPO> contentPOS = new ArrayList<>();
            for (JournalFieldPO fieldPO : fieldPOS) {
                contentPOS.add(this.getJournalContent(fieldPO, addReq.getFieldContents(), journalSummaryPO.getId()));
            }
            int contentCount = contentMapper.insertBatch(contentPOS);
            if (contentCount != contentPOS.size()) {
                throw new MRBaseException("字段内容保存异常");
            }
        ResultContext resultContext = ResultContext.success("操作成功");
        resultContext.setData(journalSummaryPO.getId());
        return resultContext;
    }

    @Override
    public PageView queryList(JournalSummaryParam param) {
        param.setState(ENJournalState.ACTIVE.getValue());
        param.setProjectId(UserContext.getProjectId());
        PageHelper.startPage(param.getPageNum(), param.getPageSize(), param.getOrderBy());
        PageInfo pageInfo = new PageInfo(summaryMapper.query(param));
        PageView pageView = new PageView();
        pageView.setCode(ENMsgCode.SUCCESS.getValue());
        pageView.setRows(this.getRespList(pageInfo.getList(), false));
        pageView.setTotal(pageInfo.getTotal());
        return pageView;
    }

    @Override
    public ResultContext getById(String summaryId) {
        JournalSummaryPO summary = summaryMapper.selectByPrimaryKey(summaryId);
        if (summary == null) {
            return ResultContext.businessFail("记录不存在");
        }
        ResultContext resultContext = ResultContext.success("操作成功");
        resultContext.setData(this.getRespList(Collections.singletonList(summary), true).get(0));
        return resultContext;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultContext editSummary(JournalSummaryReq editReq) {
        ResultContext resultContext = this.addSummary(editReq, false);
        if (ENMsgCode.SUCCESS.getValue().equals(resultContext.getCode())) {
            // 把原本的记录改为历史版本
            JournalSummaryPO summary = new JournalSummaryPO();
            summary.setId(editReq.getId());
            summary.setState(ENJournalState.HISTORY.getValue());
            summary.setLastModifyTime(new Date());
            summary.setHistoryForId(resultContext.getData().toString());
            summary.setLastModifyUser(UserContext.getUserId());
            int edit = summaryMapper.updateByPrimaryKeySelective(summary);
            if (edit != 1) {
                throw new MRBaseException("修改失败");
            }
        }
        return ResultContext.success("操作成功");
    }

    @Override
    public PageView queryHistory(String summaryId) {
        JournalSummaryPO summaryPO = new JournalSummaryPO();
        summaryPO.setHistoryForId(summaryId);
        summaryPO.setState(ENJournalState.HISTORY.getValue());
        PageHelper.startPage(1, 100, "last_modify_time desc");
        List<JournalSummaryPO> summarys = summaryMapper.selectBySelective(summaryPO);
        PageView pageView = new PageView();
        pageView.setCode(ENMsgCode.SUCCESS.getValue());
        pageView.setRows(this.getRespList(summarys, false));
        pageView.setTotal((long) summarys.size());
        return pageView;
    }

    @Override
    @Transactional
    public ResultContext delete(JournalSummaryReq deleteReq) {
        JournalSummaryReq req = new JournalSummaryReq();
        req.setIds(deleteReq.getIds());
        req.setState(ENJournalState.WAIT_DELETE.getValue());
        int deleteNum = summaryMapper.updateStateByIds(req);
        if (deleteNum != deleteReq.getIds().size()) {
            throw new MRBaseException("删除异常");
        }
        return ResultContext.success("操作成功");
    }

    @Override
    public String export(JournalSummaryParam param) {
        List<JournalFieldPO> list = fieldMapper.selectByProject(UserContext.getProjectId());
        String filePath = exportPath + File.separator + "导出" + System.currentTimeMillis() + ".xlsx";
        ExcelWriter writer = ExcelUtil.getWriter(FileUtil.file(filePath));
        PageHelper.startPage(param.getPageNum(), param.getPageSize(), param.getOrderBy());
        List<JournalSummaryQueryResp> resps = this.getRespList(summaryMapper.query(param), false);
        Map<String, String> fieldMap = this.field2Map(list);
        List<Map<String, String>> rows = this.getExcelMapList(fieldMap, resps);
        writer.write(rows);
        writer.close();
        return filePath;
    }

    private List<Map<String, String>> getExcelMapList(Map<String, String> fieldMap, List<JournalSummaryQueryResp> resps) {
        List<Map<String, String>> resultList = new LinkedList<>();
        resps.forEach(resp -> {
            Map<String, String> map = new HashMap<>();
            map.put("标题", resp.getTitle());
            Map<String, String> contentMap = resp.getContents().stream().sorted(Comparator.comparingInt(JournalFieldContentResp::getSortNum))
                    .collect(Collectors.toMap(x -> {return x.getFieldId();}, JournalFieldContentResp::getContent));
            for (String fieldId : contentMap.keySet()) {
                map.put(fieldMap.get(fieldId), contentMap.get(fieldId));
            }
            map.put("分类", resp.getTagText());
            map.put("处理时间", resp.getDealDate());
            map.put("处理人", resp.getDealUserText());
            resultList.add(map);
        });
        return resultList;
    }

    private Map<String, String> field2Map(List<JournalFieldPO> fields) {
        Map<String, String> map = new HashMap<>(fields.size());
        fields.forEach(x -> {
            map.put(x.getId(), x.getFieldName());
        });
        return map;
    }


    private List<JournalSummaryQueryResp> getRespList(List<JournalSummaryPO> list, boolean isEdit) {
        List<JournalSummaryQueryResp> summaryQueryResps = new ArrayList<>();
        list.forEach(x -> {
            JournalSummaryQueryResp summaryQueryResp = new JournalSummaryQueryResp();
            BeanUtil.copyProperties(x, summaryQueryResp);
            List<JournalContentPO> contents = contentMapper.selectBySummaryId(x.getId());
            List<JournalFieldContentResp> contentResps = new ArrayList<>();
            contents.forEach(y -> {
                JournalFieldContentResp fieldContentResp = new JournalFieldContentResp();
                if (!isEdit) {
                    fieldContentResp.setContent(y.getContent().replace(MRConstant.SPLIT_REG, StrUtil.COMMA));
                }else {
                    fieldContentResp.setContent(y.getContent());
                }
                fieldContentResp.setSortNum(Integer.parseInt(fieldMapper.selectByPrimaryKey(y.getFieldId()).getSortnum()));
                fieldContentResp.setFieldId(y.getFieldId());
                contentResps.add(fieldContentResp);
            });
            summaryQueryResp.setCreateUserText(userInfoMapper.selectByPrimaryKey(x.getCreateUser()).getNickname());
            summaryQueryResp.setLastModifyUserText(userInfoMapper.selectByPrimaryKey(x.getLastModifyUser()).getNickname());
            summaryQueryResp.setDealUserText(userInfoMapper.selectByPrimaryKey(x.getDealUser()).getNickname());
            summaryQueryResp.setTagText(tagMapper.selectByPrimaryKey(x.getTagId()).getTagname());
            summaryQueryResp.setCreateTimeText(DateUtil.format(x.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
            summaryQueryResp.setLastModifyTimeText(DateUtil.format(x.getLastModifyTime(), DatePattern.NORM_DATETIME_PATTERN));
            summaryQueryResp.setContents(contentResps);
            summaryQueryResps.add(summaryQueryResp);
        });
        return summaryQueryResps;
    }

    private JournalSummaryPO req2JournalSummaryPo(JournalSummaryReq req) {
        JournalSummaryPO journalSummaryPO = new JournalSummaryPO();
        BeanUtil.copyProperties(req, journalSummaryPO);
        journalSummaryPO.setId(IdUtil.simpleUUID());
        journalSummaryPO.setCreateUser(UserContext.getUserId());
        journalSummaryPO.setCreateTime(new Date());
        journalSummaryPO.setLastModifyTime(new Date());
        journalSummaryPO.setProjectId(UserContext.getProjectId());
        journalSummaryPO.setState(ENJournalState.ACTIVE.getValue());
        journalSummaryPO.setLastModifyUser(UserContext.getUserId());
        return journalSummaryPO;
    }

    private JournalContentPO getJournalContent(JournalFieldPO fieldPO, List<String> contents, String summaryId) {
            JournalContentPO contentPO = new JournalContentPO();
            contentPO.setId(IdUtil.simpleUUID());
            contentPO.setSummaryId(summaryId);
            contentPO.setFieldId(fieldPO.getId());
            contentPO.setContent(contents.get(Integer.parseInt(fieldPO.getSortnum())));
            return contentPO;
    }
}
