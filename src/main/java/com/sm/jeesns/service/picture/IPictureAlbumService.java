package com.sm.jeesns.service.picture;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.model.picture.PictureAlbum;

/**
 * Created by zchuanzhao on 2017/11/03.
 */
public interface IPictureAlbumService {

    ResultModel<PictureAlbum> listByMember(Integer memberId);

    ResultModel<PictureAlbum> listByPage(Page page);

    ResultModel delete(Integer id);

    ResultModel save(PictureAlbum pictureAlbum);

    ResultModel update(PictureAlbum pictureAlbum);

    PictureAlbum findWeiboAlbum(Integer memberId);

    PictureAlbum findById(Integer id);
}