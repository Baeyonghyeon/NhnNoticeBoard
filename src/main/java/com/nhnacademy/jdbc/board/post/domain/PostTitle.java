package com.nhnacademy.jdbc.board.post.domain;

import lombok.Data;
import java.util.Date;

@Data
//(default, 'admin', null, '게시판 샘플1',19981231235959, null, 0, '샘플 게시판 내용1');
public class PostTitle {
    private int id;
    private String writer;
    private String corrector;
    private String title;
    private Date createDate;
    private Date correctDate;
    private int hits;
    private String content;
    private boolean isInactive; //true 비활성화  , false 활성화;

    public PostTitle(String writer, String title, Date createDate, String content) {
        this.writer = writer;
        this.corrector = null;
        this.title = title;
        this.createDate = createDate;
        this.hits = 0;
        this.content = content;
        this.isInactive = false;
    }

    public PostTitle(int id, String writer, String corrector, String title, Date createDate, Date correctDate, int hits, String content ,boolean isInactive) {
        this.id = id;
        this.writer = writer;
        this.corrector = corrector;
        this.title = title;
        this.createDate = createDate;
        this.correctDate = correctDate;
        this.hits = hits;
        this.content = content;
        this.isInactive = isInactive;
    }
}