package com.nhnacademy.jdbc.board.index.web;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.comment.service.CommentService;
import java.sql.Timestamp;
import java.util.Date;
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
public class CommentController extends BaseController{

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}


	// 댓글 등록
	@PostMapping("/postComment/{noticeId}")
	public String insertComment(@PathVariable("noticeId") int noticeId,
		@RequestParam("content") String content,
		@ModelAttribute("user") String userId
	) {
		log.debug("userId는 무엇인가??? ================ {}", userId);
		Comment comment = new Comment(noticeId, userId, content,
			new Timestamp(new Date().getTime()), 1);
		commentService.insertComment(comment);
		return "redirect:/Contents/postView/" + noticeId;
	}


	// 댓글 수정 form으로 이동
	@GetMapping("/modifyComment/{noticeId}/{commentId}")
	public String modifyForm(@PathVariable("commentId") int commentId,
		@PathVariable("noticeId") int noticeId,
		ModelMap modelMap) {
		Comment comment = commentService.selectComment(commentId).get();
		modelMap.put("comment", comment);
		modelMap.put("noticeId", noticeId);
		return "index/commentModifyForm";
	}

	//댓글 수정
	@PostMapping("/modifyComment/{noticeId}/{commentId}")
	public String modifyComment(@PathVariable("noticeId") int noticeId,
		@PathVariable("commentId") int commentId,
		@RequestParam("content") String content) {
		commentService.modifyComment(content, commentId);
		return "redirect:/Contents/postView/" + noticeId;
	}


	// 댓글 삭제
	@GetMapping("/deleteComment/{noticeId}/{commentId}")
	public String deleteComment(@PathVariable("commentId") int commentId,
		@PathVariable("noticeId") int noticeId) {
		commentService.deleteComment(commentId);
		return "redirect:/Contents/postView/" + noticeId;
	}

}
