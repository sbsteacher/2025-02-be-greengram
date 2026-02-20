package com.green.greengram.application.feed.model;

import lombok.Getter;
import lombok.ToString;

//page=1&size=20
@Getter
@ToString
public class FeedGetReq {
    private int page;
    private int size;
    private int startIdx;

    public FeedGetReq(int page, int size) {
        this.page = page;
        this.size = size;
        this.startIdx = (page - 1) * size;
    }
}
