package com.sm.jeesns.dao.picture;

import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.model.picture.PictureComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPictureCommentDao extends IBaseDao<PictureComment> {

    List<PictureComment> listByPicture(@Param("page") Page page, @Param("pictureId") Integer pictureId);

    int deleteByPicture(@Param("pictureId") Integer pictureId);
}