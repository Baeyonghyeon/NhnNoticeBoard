package com.nhnacademy.jdbc.board.post.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.Value;

import java.sql.Date;
import java.sql.Timestamp;

@Value
//(default, 'admin', null, '게시판 샘플1',19981231235959, null, 0, '샘플 게시판 내용1');
public class PostTitle {
    private String writer;
    private String corrector;
    private String title;
    private Date createDate;
    private Date correctDate;
    private int hits;
    private String content;
}