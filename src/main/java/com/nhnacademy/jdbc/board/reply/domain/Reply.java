package com.nhnacademy.jdbc.board.reply.domain;


import java.sql.Date;
import lombok.Data;

@Data
public class Reply {
	private final int replyNo;
	private final int noticeBoardNo;
	private final int commentNo;
	private final int parentNo;
	private final int depth;
	private final String title;
	private final String content;
	private final String writer;
	private final Date createDate;
	private final int commonCode;


	/**
	 insert into reply values(
	 default, 1, null, 0, 0, '답글 샘플 제목 1', '답글 샘플 내용 1', 'user1', 19981231235959, 0);
	 */


	public Reply(int replyNo, int noticeBoardNo, int commentNo, int parentNo, int depth,
		String title, String content, String writer, Date createDate, int commonCode) {
		this.replyNo = replyNo;
		this.noticeBoardNo = noticeBoardNo;
		this.commentNo = commentNo;
		this.parentNo = parentNo;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.createDate = createDate;
		this.commonCode = commonCode;
	}
}
