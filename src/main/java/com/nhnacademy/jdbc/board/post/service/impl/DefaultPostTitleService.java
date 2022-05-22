package com.nhnacademy.jdbc.board.post.service.impl;

import com.nhnacademy.jdbc.board.post.domain.PostTitle;
import com.nhnacademy.jdbc.board.post.mapper.PostTitleMapper;
import com.nhnacademy.jdbc.board.post.service.PostTitleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultPostTitleService implements PostTitleService {
    private final PostTitleMapper postTitleMapper;

    public DefaultPostTitleService(PostTitleMapper postTitleMapper) {
        this.postTitleMapper = postTitleMapper;
    }

    @Override
    public List<PostTitle> selectPostTitles(int num) {
        return postTitleMapper.selectPostTitles(num * 20);
    }

    @Override
    public List<PostTitle> selectDeletedPostTitles() {
        return postTitleMapper.selectDeletedTitles();
    }

    @Override
    public Optional<PostTitle> selectPostTitle(int id) {
        return postTitleMapper.selectPostTitle(id);
    }

    @Override
    public void uploadPost(PostTitle postTitle) {
        postTitleMapper.insertPostTitle(postTitle);
    }

    @Override
    public void modifyPost(String corrector, String title, String contents, int id) {
        postTitleMapper.updatePostTitle(corrector, title, contents, id);
    }

    @Override
    public void recoverPost(int id) {
        postTitleMapper.selectRecoverTitle(id);
    }

    @Override
    public int getMaxPostCount() {
        return postTitleMapper.selectMaxPostCount();
    }

    @Override
    public void deletePost(int id) {
        postTitleMapper.deleteById(id);
    }
}
