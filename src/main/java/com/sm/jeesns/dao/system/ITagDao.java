package com.sm.jeesns.dao.system;

import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.system.model.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITagDao extends IBaseDao<Tag> {

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    List<Tag> listByPage(@Param("page") Page page, @Param("funcType") int funcType);

}
