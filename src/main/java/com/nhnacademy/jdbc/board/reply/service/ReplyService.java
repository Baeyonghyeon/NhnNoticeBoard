package com.nhnacademy.jdbc.board.reply.service;

import com.nhnacademy.jdbc.board.reply.domain.Reply;
import java.util.List;

public interface ReplyService {
	List<Reply> selectRepliesOnPost(int postId);
	List<Reply> selectRepliesOnComment(int commentId);
	void insertReplyOnPost(Reply reply);
	void insertReplyOnComment(Reply reply);
}
