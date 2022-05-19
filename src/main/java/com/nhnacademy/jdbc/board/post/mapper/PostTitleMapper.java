package com.nhnacademy.jdbc.board.post.mapper;

import com.nhnacademy.jdbc.board.post.domain.PostTitle;

import java.util.List;

public interface PostTitleMapper {
    List<PostTitle> selectPostTitles();
    void insertPostTitle(PostTitle postTitle);
//    void updatePostTitle();
    void deleteById(Long id);
}
