package com.green.greengram.application.feed.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@ToString
public class FeedGetReq {
    private int page;
    private int size;
    private Long profileUserId;
    private int startIdx;

    public FeedGetReq(int page, int size, @BindParam("profile_user_id") Long profileUserId) {
        this.page = page;
        this.size = size;
        this.profileUserId = profileUserId;
        this.startIdx = (page - 1) * size;
    }
}
