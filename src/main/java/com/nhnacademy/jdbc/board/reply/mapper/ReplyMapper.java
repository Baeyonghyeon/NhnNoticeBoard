package com.nhnacademy.jdbc.board.reply.mapper;


import com.nhnacademy.jdbc.board.reply.domain.Reply;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReplyMapper {
	List<Reply> selectRepliesOnPost(@Param("postId") int postId);
	List<Reply> selectRepliesOnComment(@Param("commentId") int commentId);
	void insertReplyOnPost(Reply reply);
	void insertReplyOnComment(Reply reply);
}
