package com.nhnacademy.jdbc.board.comment.service;

import com.nhnacademy.jdbc.board.comment.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> selectComments(int postId);
    void insertComment(Comment comment);
    void modifyComment(String content, int id);
    void deleteComment(int id);
    Optional<Comment> selectComment (int commentId);
}
