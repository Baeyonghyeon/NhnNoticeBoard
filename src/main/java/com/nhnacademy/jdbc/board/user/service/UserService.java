package com.nhnacademy.jdbc.board.user.service;

import com.nhnacademy.jdbc.board.student.domain.Student;
import com.nhnacademy.jdbc.board.user.domain.User;

import java.util.Optional;

public interface UserService {
    boolean matches(String id, String pwd);
    boolean isAdmin(String id);
}
