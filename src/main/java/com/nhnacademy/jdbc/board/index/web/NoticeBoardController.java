package com.nhnacademy.jdbc.board.index.web;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.comment.service.CommentService;
import com.nhnacademy.jdbc.board.post.domain.PostTitle;
import com.nhnacademy.jdbc.board.post.service.PostTitleService;
import com.nhnacademy.jdbc.board.user.service.UserService;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@Slf4j
public class NoticeBoardController extends BaseController {

	private final PostTitleService postTitleService;
	private final CommentService commentService;
	private final UserService userService;


	public NoticeBoardController(PostTitleService postTitleService, CommentService commentService,
		UserService userService) {
		this.postTitleService = postTitleService;
		this.commentService = commentService;
		this.userService = userService;
	}

	// 게시물 입력하는 Form으로 이동
	@GetMapping("/postNotice")
	public String goPostNotice() {
		return "index/postNoticeForm";
	}


	// 게시물 DB에 등록
	@PostMapping("/postNotice")
	public String postNotice(@RequestParam("title") String title,
		@RequestParam("contents") String contents,
		HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		PostTitle postTitle = PostTitle.registerPostTitle((String) session.getAttribute("id"),
			title, new Timestamp(new Date().getTime()), contents);
		postTitleService.uploadPost(postTitle);

		return "redirect:/ContentTitle/" + 0;
	}

	// 게시물 모두 조회 (전체 게시판)
	@GetMapping(value = {"/ContentTitle/{pageNum}", "/ContentTitle"})
	public String getTitles(ModelMap modelMap,
		@ModelAttribute("user") String userId,
		@PathVariable("pageNum") int pageNum) {

		modelMap.put("noticeBoard", postTitleService.selectPostTitles(pageNum));
		modelMap.put("currentPage", pageNum);
		modelMap.put("maxPostCount", postTitleService.getMaxPostCount());
		modelMap.put("maxPostPage", postTitleService.getMaxPostCount() / 20);
		modelMap.put("admin", userService.isAdmin(userId));
		return "index/noticeBoard";

	}

	// 삭제된 게시물 조회(관리자 전용)
	@GetMapping("/deleted/post")
	public String getDeletedPost(ModelMap modelMap) {
		modelMap.put("noticeBoard", postTitleService.selectDeletedPostTitles());
		return "index/deletedPost";
	}

	// 게시물 하나 들어가서 조회
	@GetMapping("/Contents/postView/{noticeId}")
	public String getContents(@PathVariable("noticeId") int noticeId,
		@ModelAttribute("user") String userId,
		ModelMap modelMap) {
		modelMap.put("contents", postTitleService.selectPostTitle(noticeId).get());
		modelMap.put("comments", commentService.selectComments(noticeId));
		modelMap.put("writer", userId);
		return "index/contents";
	}

	//  게시물 수정 Form으로 이동
	@GetMapping("/updateTitle/{noticeId}")
	public String getUpdatePost(@PathVariable("noticeId") int noticeId, ModelMap modelMap) {
		PostTitle post = postTitleService.selectPostTitle(noticeId).get();
		modelMap.put("contents", post);
		return "index/contentsModifyForm";
	}

	//  게시물 수정 값 받아서 DB에 업데이트
	@PostMapping("/updateTitle/{noticeId}")
	public String postUpdatePost(@PathVariable("noticeId") int noticeId,
		@RequestParam("title") String title,
		@RequestParam("content") String content,
		HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String id = (String) session.getAttribute("id");
//      Optional<PostTitle> postTitle = postTitleService.selectPostTitle(noticeId);

		postTitleService.modifyPost(id, title, content, noticeId);
		return "redirect:/ContentTitle/" + 0;
	}


	// 게시물 삭제
	@GetMapping("/deleteTitle/{noticeId}")
	public String deletePost(@PathVariable("noticeId") int noticeId, ModelMap modelMap) {
		postTitleService.deletePost(noticeId);
//        modelMap.put("noticeBoard", postTitleService.selectPostTitles(no));
		return "redirect:/ContentTitle/" + 0;
	}

	//게시물 복구
	@GetMapping("/deleteTitle/recover/{noticeId}")
	public String recoverPost(@PathVariable("noticeId") int noticeId) {
		postTitleService.recoverPost(noticeId);
		return "redirect:/deleted/post";
	}


}
