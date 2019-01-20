package com.sm.jeesns.picture.dao;

import com.sm.jeesns.common.dao.IBaseDao;
import com.sm.jeesns.picture.model.PictureAlbumFavor;
import org.apache.ibatis.annotations.Param;

public interface IPictureAlbumFavorDao extends IBaseDao<PictureAlbumFavor> {
    PictureAlbumFavor find(@Param("pictureAlbumId") Integer pictureAlbumId, @Param("memberId") Integer memberId);

    Integer save(@Param("pictureAlbumId") Integer pictureAlbumId, @Param("memberId") Integer memberId);

    Integer delete(@Param("pictureAlbumId") Integer pictureAlbumId, @Param("memberId") Integer memberId);
}