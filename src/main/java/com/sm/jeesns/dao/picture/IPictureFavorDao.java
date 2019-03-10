package com.sm.jeesns.dao.picture;

import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.picture.model.PictureFavor;
import org.apache.ibatis.annotations.Param;

public interface IPictureFavorDao extends IBaseDao<PictureFavor> {

    PictureFavor find(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);

    Integer save(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);

    Integer delete(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);
}