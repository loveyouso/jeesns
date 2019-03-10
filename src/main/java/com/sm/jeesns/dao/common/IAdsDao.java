package com.sm.jeesns.dao.common;

import com.sm.jeesns.common.model.Ads;
import com.sm.jeesns.core.model.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/9/07.
 */
public interface IAdsDao extends IBaseDao<Ads>{

    /**
     * 分页查询广告信息
     * @param page
     * @return
     */
    List<Ads> listByPage(@Param("page") Page page);

    int enable(@Param("id") Integer id);
}
