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
import java.util.Objects;

@Controller
@RequestMapping
@Slf4j
public class NoticeBoardController {

    private final PostTitleService postTitleService;

    public NoticeBoardController(PostTitleService postTitleService) {
        this.postTitleService = postTitleService;
    }


    @GetMapping("/postNotice")
    public String goPostNotice(){
        return "index/postNoticeForm";
    }

    @PostMapping("/postNotice")
    public String postNotice(@RequestParam("title") String title,
                             @RequestParam("contents") String contents,
                             HttpServletRequest request){
        HttpSession session = request.getSession(false);
        PostTitle postTitle = new PostTitle((String) session.getAttribute("id"), title, new Timestamp(new Date().getTime()), contents);
        postTitleService.post(postTitle);

        return "index/home";
    }

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

//     게시물 수정
//    @GetMapping("/updateTitle/{noticeId}")
//    public String updatePost(@PathVariable("noticeId") int noticeId, ModelMap modelMap) {
//        postTitleService.selectPostTitle(noticeId).get();
//
//    }

    // 게시물 삭제
    @GetMapping("/deleteTitle/{noticeId}")
    public String deletePost(@PathVariable("noticeId") int noticeId,
                             HttpServletRequest request,
                             ModelMap modelMap) {
        HttpSession session = request.getSession(false);

        PostTitle post = postTitleService.selectPostTitle(noticeId).get();
        String postId = post.getWriter();
        if (Objects.equals(session.getAttribute("id"), postId)) {
            postTitleService.deletePost(noticeId);
            return "index/noticeBoard";
        } else {
            return "index/exceptionPermission";
        }
    }
}
