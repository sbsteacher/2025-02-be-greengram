package com.green.greengram.application.feedcomment.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class FeedCommentGetReq {
    private int page;
    private long feedId;
    private int startIdx;
    private int size;

    public FeedCommentGetReq(int page, long feedId, int size) {
        this.page = page;
        this.feedId = feedId;
        this.size = size;
        this.startIdx = (page - 1) * size;
    }
}
