package com.sm.jeesns.service.picture;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.model.member.Member;
import com.sm.jeesns.model.picture.PictureAlbumComment;


/**
 *
 * @author zchuanzhao
 * @date 2017/11/17
 */
public interface IPictureAlbumCommentService {

    PictureAlbumComment findById(int id);

    ResultModel save(Member loginMember, String content, Integer pictureAlbumId);

    ResultModel delete(Member loginMember, int id);

    ResultModel listByPictureAlbum(Page page, int pictureAlbumId);

    void deleteByPictureAlbum(Integer pictureAlbumId);
}
