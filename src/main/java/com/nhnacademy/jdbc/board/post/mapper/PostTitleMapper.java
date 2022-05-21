package com.nhnacademy.jdbc.board.post.mapper;

import com.nhnacademy.jdbc.board.post.domain.PostTitle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostTitleMapper {
    List<PostTitle> selectPostTitles();
    Optional<PostTitle> selectPostTitle(int id);
    void insertPostTitle(PostTitle postTitle);
    void updatePostTitle(@Param("corrector") String corrector , @Param("title") String title, @Param("contents") String contents, @Param("id") int id);
    void deleteById(int id);
}
