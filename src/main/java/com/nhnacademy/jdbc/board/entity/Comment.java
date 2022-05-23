package com.nhnacademy.jdbc.board.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Comments")
public class Comment {

	@Id
	@Column(name = "comment_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentNo; //기본키

	@Column(name = "notice_board_no")
	private int noticeBoardNo;

	@Column(name = "writer")
	private String writer;

	@Column(name = "comment_content")
	private String commentContent;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "comment_separate_code")
	private int commentSeparateCode;

}
