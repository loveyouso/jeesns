package com.sm.jeesns.dao.picture;

import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.picture.model.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/1.
 */
public interface IPictureDao extends IBaseDao<Picture> {

    List<Picture> find(@Param("foreignId") Integer foreignId);

    Picture findById(@Param("pictureId") Integer pictureId, @Param("loginMemberId") Integer loginMemberId);

    List<Picture> listByPage(@Param("page") Page page, @Param("loginMemberId") Integer loginMemberId);

    List<Picture> listByAlbum(@Param("page") Page page, @Param("albumId") Integer albumId, @Param("loginMemberId") Integer loginMemberId);

    int deleteByForeignId(@Param("id") Integer foreignId);

    int update(@Param("foreignId") Integer foreignId, @Param("ids") String[] ids, @Param("description") String description);

    int favor(@Param("id") Integer id, @Param("num") Integer num);
}