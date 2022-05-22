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
    public List<PostTitle> selectPostTitles() {
        return postTitleMapper.selectPostTitles();
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
    public void deletePost(int id) {
        postTitleMapper.deleteById(id);
    }

    @Override
    public void uploadReply(PostTitle postTitle, int originNo, int parentNo) {
        postTitleMapper.updatePostReply(originNo);
        postTitleMapper.insertPostReply(postTitle, parentNo);
    }
}
