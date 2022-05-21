package com.nhnacademy.jdbc.board.comment.service.impl;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.comment.mapper.CommentMapper;
import com.nhnacademy.jdbc.board.comment.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultCommentService implements CommentService {
    private final CommentMapper commentMapper;

    public DefaultCommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> selectComments(int postId) {
        return commentMapper.selectComments(postId);
    }

    @Override
    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }
    @Override
    public void modifyComment(String content, int id) {
        commentMapper.modifyComment(content, id);
    }

    @Override
    public void deleteComment(int id) {
        commentMapper.deleteById(id);
    }

    @Override
    public Optional<Comment> selectComment(int commentId) {
        return commentMapper.selectComment(commentId);
    }
}
