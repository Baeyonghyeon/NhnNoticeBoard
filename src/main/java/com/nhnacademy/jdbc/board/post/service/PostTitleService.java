package com.nhnacademy.jdbc.board.post.service;

import com.nhnacademy.jdbc.board.post.domain.PostTitle;

import java.util.List;

public interface PostTitleService {
    List<PostTitle> selectPostTitles();
    void post(PostTitle postTitle);
}
