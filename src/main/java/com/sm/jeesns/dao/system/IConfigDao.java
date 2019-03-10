package com.sm.jeesns.dao.system;

import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.system.model.Config;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息DAO接口
 * Created by zchuanzhao on 2016/11/26.
 */

public interface IConfigDao extends IBaseDao<Config> {

    boolean update(@Param("key") String key, @Param("value") String value);

    String getValue(@Param("key") String key);
}