package com.nhnacademy.jdbc.board.post.service;

import com.nhnacademy.jdbc.board.post.domain.PostTitle;

import java.util.List;
import java.util.Optional;

public interface PostTitleService {
    List<PostTitle> selectPostTitles(int num);
    List<PostTitle> selectDeletedPostTitles();
    Optional<PostTitle> selectPostTitle(int id);
    void uploadPost(PostTitle postTitle);
    void deletePost(int id);
    void modifyPost(String corrector, String title, String content, int noticeId);
    void recoverPost(int id);
    int getMaxPostCount();
}