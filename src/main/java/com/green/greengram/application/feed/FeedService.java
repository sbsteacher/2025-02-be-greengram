package com.green.greengram.application.feed;

import com.green.greengram.application.feed.model.FeedPostReq;
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

    public void postFeed(FeedPostReq req, List<MultipartFile> pics) {
        feedMapper.save(req);

        //save이후에 방금 insert한 feed테이블의 id값이 필요해요.
        long feedId = req.getFeedId();
        log.info("feedId: {}", feedId);

    }
}
