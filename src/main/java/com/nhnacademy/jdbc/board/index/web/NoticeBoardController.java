package com.nhnacademy.jdbc.board.index.web;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.comment.service.CommentService;
import com.nhnacademy.jdbc.board.post.domain.PostTitle;
import com.nhnacademy.jdbc.board.post.service.PostTitleService;
import com.nhnacademy.jdbc.board.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping
@Slf4j
public class NoticeBoardController extends BaseController {

    private final PostTitleService postTitleService;
    private final CommentService commentService;
    private final UserService userService;



    public NoticeBoardController(PostTitleService postTitleService, CommentService commentService, UserService userService) {
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
        PostTitle postTitle = PostTitle.registerPostTitle((String) session.getAttribute("id"), title, new Timestamp(new Date().getTime()), contents);
        postTitleService.uploadPost(postTitle);

        return "redirect:/ContentTitle/"+ 0;
    }

    // 게시물 모두 조회 (전체 게시판)
    @GetMapping(value = {"/ContentTitle/{pageNum}", "/ContentTitle"})
    public String getTitles(ModelMap modelMap,
                            @ModelAttribute("user") String userId,
                            @PathVariable("pageNum") int pageNum) {

        modelMap.put("noticeBoard", postTitleService.selectPostTitles(pageNum));
        modelMap.put("currentPage", pageNum);
        modelMap.put("maxPostCount", postTitleService.getMaxPostCount());
        modelMap.put("maxPostPage", postTitleService.getMaxPostCount()/20);
        modelMap.put("admin", userService.isAdmin(userId));
        return "index/noticeBoard";

    }

    // 삭제된 게시물 조회(관리자 전용)
    @GetMapping("/deleted/post")
    public String getDeletedPost(ModelMap modelMap){
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
        return "redirect:/ContentTitle/"+ 0;
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
    public String recoverPost(@PathVariable("noticeId") int noticeId){
        postTitleService.recoverPost(noticeId);
        return "redirect:/deleted/post";
    }

    // 댓글 등록
    @PostMapping("/postComment/{noticeId}")
    public String insertComment(@PathVariable("noticeId") int noticeId,
                                @RequestParam("content") String content,
                                @ModelAttribute("user") String userId
                               ) {
        log.debug("userId는 무엇인가??? ================ {}", userId);
        Comment comment = new Comment(noticeId, userId, content, new Timestamp(new Date().getTime()), 1);
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
        return "redirect:/ContentTitle";
    }

}
