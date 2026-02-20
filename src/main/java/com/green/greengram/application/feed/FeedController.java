package com.green.greengram.application.feed;


import com.green.greengram.application.feed.model.FeedPostReq;
import com.green.greengram.configuration.model.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    @PostMapping
    public ResultResponse<?> postFeed(@RequestPart FeedPostReq req
                                    , @RequestPart(name = "pic") List<MultipartFile> pics) {
        log.info("req: {}", req);
        log.info("pics.size(): {}", pics.size());
        return null;
    }
}
