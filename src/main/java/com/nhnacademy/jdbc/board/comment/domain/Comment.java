package com.nhnacademy.jdbc.board.comment.domain;

import javax.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@ToString
public class Comment {
    private int commentNo; //기본키
    private int noticeBoardNo;
    private String writer;

    @NotBlank
    @Length(min = 1, max = 100)
    private String commentContent;
    private Date createDate;
    private int commentSeparateCode;

    public Comment(int noticeBoardNo, String writer, String commentContent, Date createDate, int commentSeparateCode) {
        this.noticeBoardNo = noticeBoardNo;
        this.writer = writer;
        this.commentContent = commentContent;
        this.createDate = createDate;
        this.commentSeparateCode = commentSeparateCode;
    }

    public Comment(int commentNo, int noticeBoardNo, String writer, String commentContent, Date createDate, int commentSeparateCode) {
        this.commentNo = commentNo;
        this.noticeBoardNo = noticeBoardNo;
        this.writer = writer;
        this.commentContent = commentContent;
        this.createDate = createDate;
        this.commentSeparateCode = commentSeparateCode;
    }


}
