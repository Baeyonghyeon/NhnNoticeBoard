package com.nhnacademy.jdbc.board.index.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UserControllerTest {

	private MockMvc mockMvc;
	private UserService userService;
	private User user;

	@BeforeEach
	void setUp() {
		userService = mock(UserService.class);
		user = mock(User.class);
		mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService))
			.build();
	}

	@Test
	void loginisSuc성공cessful() {
		when(userService.matches(anyString(), anyString()))
			.thenReturn(true);

		MvcResult mvcResult = null;
		try {
			mvcResult = mockMvc.perform(post("/login")
					.param("id", "admin")
					.param("pwd", "1234"))
				.andExpect(status().isOk())
				.andExpect(view().name("loginSuccess"))
				.andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpSession session = mvcResult.getRequest().getSession(false);
		assertThat(session).isNotNull();

		String attributeId = (String) mvcResult.getModelAndView()
			.getModelMap()
			.get("id");
		assertThat(attributeId).isEqualTo(session.getId());
	}


	@DisplayName("로그인 ")
	@Test
	void loginIsNotSuccessful() throws Exception {
		when(userService.matches(anyString(), anyString()))
			.thenReturn(false);

		MvcResult mvcResult = mockMvc.perform(post("/login")
				.param("id", "admin")
				.param("pwd", "1234"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/"))
			.andReturn();

		HttpSession httpSession = mvcResult.getRequest().getSession(false);
		assertThat(httpSession.getAttribute("login")).isNull();
	}
}