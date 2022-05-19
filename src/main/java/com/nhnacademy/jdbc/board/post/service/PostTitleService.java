package com.nhnacademy.jdbc.board.post.service;

import com.nhnacademy.jdbc.board.post.domain.PostTitle;

import java.util.List;
import java.util.Optional;

public interface PostTitleService {
    List<PostTitle> selectPostTitles();
    Optional<PostTitle> selectPostTitle(int id);
    void post(PostTitle postTitle);
    void updatePost(PostTitle postTitle);
    void deletePost(int id);
}