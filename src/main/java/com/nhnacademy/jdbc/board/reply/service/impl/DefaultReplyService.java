package com.nhnacademy.jdbc.board.reply.service.impl;

import com.nhnacademy.jdbc.board.reply.domain.Reply;
import com.nhnacademy.jdbc.board.reply.mapper.ReplyMapper;
import com.nhnacademy.jdbc.board.reply.service.ReplyService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DefaultReplyService implements ReplyService {
	private final ReplyMapper replyMapper;

	public DefaultReplyService(ReplyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}

	@Override
	public List<Reply> selectRepliesOnPost(int postId) {
		return replyMapper.selectRepliesOnPost(postId);
	}

	@Override
	public List<Reply> selectRepliesOnComment(int commentId) {
		return replyMapper.selectRepliesOnComment(commentId);
	}

	@Override
	public void insertReplyOnPost(Reply reply) {
		replyMapper.insertReplyOnPost(reply);
	}

	@Override
	public void insertReplyOnComment(Reply reply) {
		replyMapper.insertReplyOnComment(reply);
	}
}
