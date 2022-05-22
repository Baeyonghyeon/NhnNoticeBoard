package com.nhnacademy.jdbc.board.user.mapper;

import com.nhnacademy.jdbc.board.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> selectUser(String id);
    List<User> allUser();
}
