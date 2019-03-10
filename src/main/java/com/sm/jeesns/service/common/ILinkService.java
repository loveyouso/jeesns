package com.sm.jeesns.service.common;

import com.sm.jeesns.model.common.Link;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;

/**
 * Created by zchuanzhao on 2017-10-13.
 */
public interface ILinkService {
   
    ResultModel save(Link link);
   
    ResultModel listByPage(Page page);

    ResultModel allList();

    ResultModel recommentList();

    ResultModel update(Link link);

    ResultModel delete(Integer id);

    Link findById(Integer id);

    ResultModel enable(Integer id);
}
