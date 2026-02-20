package com.green.greengram.application.feed.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FeedPostReq {
    private String location;
    private String contents;
}
