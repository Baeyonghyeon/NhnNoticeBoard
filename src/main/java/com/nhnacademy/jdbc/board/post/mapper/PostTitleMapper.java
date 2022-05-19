package com.nhnacademy.jdbc.board.post.mapper;

import com.nhnacademy.jdbc.board.post.domain.PostTitle;

import java.util.List;
import java.util.Optional;

public interface PostTitleMapper {
    List<PostTitle> selectPostTitles();
    Optional<PostTitle> selectPostTitle(int id);
    void insertPostTitle(PostTitle postTitle);
//    void updatePostTitle();
    void deleteById(Long id);
}
