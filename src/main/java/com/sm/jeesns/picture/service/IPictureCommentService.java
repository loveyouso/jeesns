package com.sm.jeesns.picture.service;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.member.model.Member;
import com.sm.jeesns.picture.model.PictureComment;


/**
 * Created by zchuanzhao on 2017/11/14.
 */
public interface IPictureCommentService {

    PictureComment findById(int id);

    ResultModel save(Member loginMember, String content, Integer pictureId);

    ResultModel delete(Member loginMember, int id);

    ResultModel listByPicture(Page page, int pictureId);

    void deleteByPicture(Integer pictureId);
}
