package com.sm.jeesns.picture.service;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.member.model.Member;
import com.sm.jeesns.picture.model.Picture;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/7.
 */
public interface IPictureService {

    List<Picture> find(Integer foreignId);

    Picture findById(Integer pictureId, int loginMemberId);

    ResultModel<Picture> listByPage(Page page, int loginMemberId);

    ResultModel<Picture> listByAlbum(Page page, Integer pictureAlbumId, int loginMemberId);

    int deleteByForeignId(HttpServletRequest request, Integer foreignId);

    ResultModel delete(HttpServletRequest request, Integer pictureId);

    int save(Picture picture);

    int update(Integer foreignId, String ids, String description);

    ResultModel favor(Member loginMember, int pictureId);
}