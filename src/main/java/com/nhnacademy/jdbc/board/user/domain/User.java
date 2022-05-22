package com.nhnacademy.jdbc.board.user.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@ToString
public class User {

	@NotBlank
	@Length(min = 1, max = 10)
	private String id;


	@NotBlank
	@Length(min = 1, max = 10)
	private String password;

	@NotBlank
	@Length(min = 1, max = 10)
	private String name;

	@NotNull
	private int userSeparateCode;


	public User(String id, String password, String name, int userSeparateCode) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.userSeparateCode = userSeparateCode;
	}
}