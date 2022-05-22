package com.nhnacademy.jdbc.board.index.web;

import com.nhnacademy.jdbc.board.post.domain.PostTitle;
import com.nhnacademy.jdbc.board.post.service.PostTitleService;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping
@Controller
public class ReplyController extends BaseController{

	private final PostTitleService postTitleService;


	public ReplyController(PostTitleService postTitleService) {
		this.postTitleService = postTitleService;

	}

	// 답글 등록 Form으로 이동
	@GetMapping("/updateReply/{noticeId}")
	public String goReplyNotice(
		@PathVariable("noticeId") int noticeId,
		ModelMap modelMap) {

		modelMap.put("noticeId", noticeId);
		return "index/postReplyForm";
	}

	// 답글 DB에 등록
	@PostMapping("/updateReply/{noticeId}")
	public String ReplyNotice(
		@PathVariable("noticeId") int noticeId,
		@RequestParam("title") String title,
		@RequestParam("contents") String contents, HttpServletRequest request) {

		PostTitle originPost = postTitleService.selectPostTitle(noticeId).get();
		HttpSession session = request.getSession(false);
		PostTitle postTitle = PostTitle.registerReply((String) session.getAttribute("id"),
			title, new Timestamp(new Date().getTime()), contents, originPost.getReplyRef());
		postTitleService.uploadReply(postTitle, originPost.getReplyRef(), noticeId);
		return "redirect:/ContentTitle/" + 0;
	}


}
