package com.sm.jeesns.system.service;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.system.model.Tag;

public interface ITagService {
    ResultModel save(Tag tag);

    ResultModel listByPage(Page page, int funcType);

    ResultModel update(Tag tag);

    ResultModel delete(Integer id);

    Tag findById(Integer id);

}
