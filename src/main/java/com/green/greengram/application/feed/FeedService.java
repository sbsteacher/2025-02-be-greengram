package com.green.greengram.application.feed;

import com.green.greengram.application.feed.model.*;
import com.green.greengram.application.feedcomment.FeedCommentService;
import com.green.greengram.application.feedcomment.model.FeedCommentGetReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.configuration.util.ImgUploadManager;
import com.green.greengram.configuration.util.MyFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper feedMapper;
    private final ImgUploadManager imgUploadManager;
    private final MyFileUtil myFileUtil;

    public FeedPostRes postFeed(FeedPostReq req, List<MultipartFile> pics) {
        feedMapper.save(req);

        //save이후에 방금 insert한 feed테이블의 id값이 필요해요.
        long feedId = req.getFeedId();
        log.info("feedId: {}", feedId);

        //saveFeedPics메소드 호출하고 싶다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        List<String> picSavedNames = imgUploadManager.saveFeedPics(feedId, pics);
        feedMapper.savePics(feedId, picSavedNames);

        return new FeedPostRes(feedId, picSavedNames);
    }

    public List<FeedGetRes> getFeedList(FeedGetReq req) {
        List<FeedGetRes> list = feedMapper.findAll(req);
        //작업!!! 피드 당 사진 정보를 가져오는 작업을 해야한다.
        for(FeedGetRes res : list) {
            //사진 가져오는 select
            List<String> pics = feedMapper.findPicsById(res.getId());
            res.setPics(pics);
        }
        return list;
    }

    public int deleteFeed(FeedDeleteReq req) {

        //feed_pic, feed_like, feed_comment에 feedId가 사용된 모든 row를 삭제
        feedMapper.deleteRef(req);

        //feed 테이블의 row는 가장 마지막에 삭제처리
        feedMapper.delete(req);

        // 폴더 째 삭제
        String delDirectoryPath = String.format("%s/feed/%d", myFileUtil.fileUploadPath, req.getFeedId());
        myFileUtil.deleteDirectory(delDirectoryPath);
        return 1;
    }
}
