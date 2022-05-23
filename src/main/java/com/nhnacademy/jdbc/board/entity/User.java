package com.nhnacademy.jdbc.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {

	@NotBlank
	@Length(min = 1, max = 10)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private String id;


	@NotBlank
	@Length(min = 1, max = 10)
	private String password;

	@NotBlank
	@Length(min = 1, max = 10)
	@Column(name = "user_name")
	private String name;

	@NotNull
	@Column(name = "user_separate_code")
	private int userSeparateCode;

}