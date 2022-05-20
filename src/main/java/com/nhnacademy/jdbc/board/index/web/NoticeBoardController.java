package com.nhnacademy.jdbc.board.index.web;

import com.nhnacademy.jdbc.board.post.domain.PostTitle;
import com.nhnacademy.jdbc.board.post.service.PostTitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
@Slf4j
public class NoticeBoardController {

    private final PostTitleService postTitleService;

    public NoticeBoardController(PostTitleService postTitleService) {
        this.postTitleService = postTitleService;
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
        PostTitle postTitle = new PostTitle((String) session.getAttribute("id"), title, new Timestamp(new Date().getTime()), contents);
        postTitleService.uploadPost(postTitle);

        return "index/home";
    }

    // 게시물 모두 조회 (전체 게시판)
    @GetMapping("/ContentTitle")
    public String getTitles(ModelMap modelMap) {
        List<PostTitle> list = postTitleService.selectPostTitles();
        modelMap.put("noticeBoard", postTitleService.selectPostTitles());

        return "index/noticeBoard";
    }

    // 게시물 하나 들어가서 조회
    @GetMapping("/Contents/postView/{noticeId}")
    public String getContents(@PathVariable("noticeId") int noticeId,
                              HttpServletRequest request,
                              ModelMap modelMap) {
        HttpSession session = request.getSession(false);
        modelMap.put("contents", postTitleService.selectPostTitle(noticeId).get());
        modelMap.put("writer", session.getAttribute("id"));
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
//        Optional<PostTitle> postTitle = postTitleService.selectPostTitle(noticeId);

        postTitleService.modifyPost(id, title, content, noticeId);
        return "redirect:/ContentTitle";
    }


    // 게시물 삭제
    @GetMapping("/deleteTitle/{noticeId}")
    public String deletePost(@PathVariable("noticeId") int noticeId, ModelMap modelMap) {
            postTitleService.deletePost(noticeId);
            modelMap.put("noticeBoard", postTitleService.selectPostTitles());
            return "index/noticeBoard";
    }
}
