package com.sm.jeesns.service.system;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.model.system.Tag;

public interface ITagService {
    ResultModel save(Tag tag);

    ResultModel listByPage(Page page, int funcType);

    ResultModel update(Tag tag);

    ResultModel delete(Integer id);

    Tag findById(Integer id);

}
