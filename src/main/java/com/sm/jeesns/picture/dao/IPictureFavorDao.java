package com.sm.jeesns.picture.dao;

import com.sm.jeesns.common.dao.IBaseDao;
import com.sm.jeesns.picture.model.PictureFavor;
import org.apache.ibatis.annotations.Param;

public interface IPictureFavorDao extends IBaseDao<PictureFavor> {

    PictureFavor find(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);

    Integer save(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);

    Integer delete(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);
}