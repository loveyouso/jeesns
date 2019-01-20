package com.sm.jeesns.picture.dao;

import com.sm.jeesns.common.dao.IBaseDao;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.picture.model.PictureComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPictureCommentDao extends IBaseDao<PictureComment> {

    List<PictureComment> listByPicture(@Param("page") Page page, @Param("pictureId") Integer pictureId);

    int deleteByPicture(@Param("pictureId") Integer pictureId);
}