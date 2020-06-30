package com.murongyehua.mrwb.journal.service;

import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;

/**
 * @author liul
 * @version 1.0 2020/6/30
 */
public interface JournalTagService {

    ResultContext addTag(String name);

    PageView queryTag();
}
