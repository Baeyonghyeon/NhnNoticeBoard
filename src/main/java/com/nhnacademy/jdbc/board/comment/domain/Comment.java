package com.nhnacademy.jdbc.board.comment.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private int commentNo; //기본키
    private int noticeBoardNo;
    private String writer;
    private String commentContent;
    private Date createDate;
    private int commentSeparateCode;

    //INSERT INTO comment VALUES(default, #{noticeBoardNo}, #{writer}, #{commentContent}, #{createDate})
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
