package com.sm.jeesns.picture.dao;

import com.sm.jeesns.common.dao.IBaseDao;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.picture.model.PictureAlbum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPictureAlbumDao extends IBaseDao<PictureAlbum> {

    List<PictureAlbum> listByMember(@Param("memberId") Integer memberId);

    List<PictureAlbum> listByPage(@Param("page") Page page);

    PictureAlbum findWeiboAlbum(@Param("memberId") Integer memberId);
}