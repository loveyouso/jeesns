package com.sm.jeesns.picture.dao;

import com.sm.jeesns.common.dao.IBaseDao;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.picture.model.PictureAlbumComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPictureAlbumCommentDao extends IBaseDao<PictureAlbumComment> {
    List<PictureAlbumComment> listByPictureAlbum(@Param("page") Page page, @Param("pictureAlbumId") Integer pictureAlbumId);

    int deleteByPictureAlbum(@Param("pictureAlbumId") Integer pictureAlbumId);
}