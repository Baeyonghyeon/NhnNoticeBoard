package com.nhnacademy.jdbc.board.user.service;

public interface UserService {

	boolean matches(String id, String pwd);

	boolean isAdmin(String id);
}
