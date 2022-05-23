package com.nhnacademy.jdbc.board.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
@Table(name = "Posts")
@NoArgsConstructor
public class PostTitle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private int id; //게시물 번호

	private String writer;

	private String corrector;

	private String title;

	private int hits;

	private String content;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "correct_date")
	private Date correctDate;


	@Column(name = "comment_count")
	private int commentCount;

	@Column(name = "is_inactive")
	private boolean isInactive; //true 비활성화  , false 활성화;

	@Column(name = "reply_ref")
	private int replyRef; //답글 그룹

	@Column(name = "reply_ord")
	private int replyOrd; //답글 순서

	@Column(name = "reply_depth")
	private int replyDepth; //답글 깊이


}