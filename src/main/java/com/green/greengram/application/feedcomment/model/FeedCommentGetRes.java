package com.green.greengram.application.feedcomment.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FeedCommentGetRes {
    private boolean moreComment;
    private List<FeedCommentDto> commentList = new ArrayList<>();
}
