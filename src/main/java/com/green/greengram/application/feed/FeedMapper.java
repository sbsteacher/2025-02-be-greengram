package com.green.greengram.application.feed;

import com.green.greengram.application.feed.model.FeedPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {
    int save(FeedPostReq req);
}
