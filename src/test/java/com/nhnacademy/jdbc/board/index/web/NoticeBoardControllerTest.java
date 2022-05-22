// package com.nhnacademy.jdbc.board.index.web;
//
// import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
// import static org.mockito.Mockito.mock;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
// import com.nhnacademy.jdbc.board.comment.service.CommentService;
// import com.nhnacademy.jdbc.board.post.domain.PostTitle;
// import com.nhnacademy.jdbc.board.post.service.PostTitleService;
// import com.nhnacademy.jdbc.board.user.domain.User;
// import com.nhnacademy.jdbc.board.user.service.UserService;
// import java.sql.Timestamp;
// import java.util.Date;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.mock.web.MockHttpSession;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
// class NoticeBoardControllerTest {
//
// 	private MockMvc mockMvc;
// 	private PostTitleService postTitleService;
// 	private CommentService commentService;
// 	private UserService userService;
// 	private User user;
// 	private MockHttpSession session;
//
//
// 	@BeforeEach
// 	void setUp() {
// 		postTitleService = mock(PostTitleService.class);
// 		commentService = mock(CommentService.class);
// 		userService = mock(UserService.class);
// 		mockMvc = MockMvcBuilders.standaloneSetup(
// 				new NoticeBoardController(postTitleService, commentService, userService))
// 			.build();
// 		session = new MockHttpSession();
// 		user = new User("admin", "1234", "관리자", 1);
// 		session.setAttribute("id", user);
// 	}
//
// 	@Test
// 	void goPostNotice() throws Exception {
// 		mockMvc.perform(get("/postNotice"))
// 			.andExpect(status().isOk())
// 			.andExpect(view().name("index/postNoticeForm"));
// 	}
//
//
// 	@Test
// 	void getTitles() throws Exception {
// 		mockMvc.perform(get("/ContentTitle/0"))
// 			.andExpect(status().isOk())
// 			.andExpect(view().name("index/noticeBoard"));
// 	}
//
// 	@Test
// 	void getContents() throws Exception {
// 		mockMvc.perform(get("/Contents/postView/0"))
// 			.andExpect(status().isOk())
// 			.andExpect(view().name("index/contents"));
// 	}
//
// 	@Test
// 	void getUpdatePost() throws Exception {
// 		MvcResult mvcResult = mockMvc.perform(get("/postModify/0"))
// 			.andExpect(status().isOk())
// 			.andExpect(model().attribute("post", postTitleService.selectPostTitle(0)))
// 			.andDo(print())
// 			.andExpect(view().name("index/contentsModifyForm"))
// 			.andReturn();
// 		assertThat(mvcResult.getModelAndView().getModelMap().getAttribute("post"))
// 			.isEqualTo(postTitleService.selectPostTitle(0));
// 	}
//
//
// 	@Test
// 	void recoverPost() throws Exception {
// 		mockMvc.perform(get("/deleteTitle/recover/0"))
// 			.andExpect(status().isOk())
// 			.andExpect(view().name("redirect:/deleted/post"));
// 	}
// }