package com.nhnacademy.jdbc.board.user.mapper;

import com.nhnacademy.jdbc.board.student.domain.Student;
import com.nhnacademy.jdbc.board.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserMapper {
    Optional<User> selectUser(String id);
    List<User> allUser();
}
