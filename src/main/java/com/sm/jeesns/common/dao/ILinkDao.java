package com.sm.jeesns.common.dao;

import com.sm.jeesns.common.model.Link;
import com.sm.jeesns.core.model.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2017-10-13.
 */
public interface ILinkDao extends IBaseDao<Link>{

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<Link> listByPage(@Param("page") Page page);

    List<Link> recommentList();

    int enable(@Param("id") Integer id);
}
