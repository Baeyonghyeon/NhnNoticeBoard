package com.nhnacademy.jdbc.board.comment.mapper;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentMapper {
    List<Comment> selectComments(@Param("postId") int PostId);

    void insertComment(Comment comment);

    void modifyComment(@Param("content") String content, @Param("commentId") int commentId);

    void deleteById(@Param("commentId") int commentId);

    Optional<Comment> selectComment(@Param("commentId")int commentId);

}
