package com.sm.jeesns.common.service;

import com.sm.jeesns.common.model.Ads;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;

/**
 * Created by zchuanzhao on 2017/9/07.
 */
public interface IAdsService {
    /**
     * 保存广告信息
     * @param ads
     * @return
     */
    boolean save(Ads ads);
    /**
     * 分页查询广告信息
     * @param page
     * @return
     */
    ResultModel listByPage(Page page);

    boolean update(Ads ads);

    boolean delete(Integer id);

    Ads findById(Integer id);

    boolean enable(Integer id);
}
