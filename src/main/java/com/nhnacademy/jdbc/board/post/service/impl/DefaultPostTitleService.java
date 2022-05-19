package com.nhnacademy.jdbc.board.post.service.impl;

import com.nhnacademy.jdbc.board.post.domain.PostTitle;
import com.nhnacademy.jdbc.board.post.mapper.PostTitleMapper;
import com.nhnacademy.jdbc.board.post.service.PostTitleService;

import java.util.List;

public class DefaultPostTitleService implements PostTitleService {
    private final PostTitleMapper postTitleMapper;

    public DefaultPostTitleService(PostTitleMapper postTitleMapper) {
        this.postTitleMapper = postTitleMapper;
    }


    @Override
    public List<PostTitle> selectPostTitles() {
        return null;
    }

    @Override
    public void Post(PostTitle postTitle) {
        postTitleMapper.insertPostTitle(postTitle);
    }


}
