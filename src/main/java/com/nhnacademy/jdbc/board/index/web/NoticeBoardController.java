package com.nhnacademy.jdbc.board.index.web;

import com.nhnacademy.jdbc.board.post.domain.PostTitle;
import com.nhnacademy.jdbc.board.post.service.PostTitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
    public String getContents(ModelMap modelMap){
        List<PostTitle> list = postTitleService.selectPostTitles();
        modelMap.put("noticeBoard", postTitleService.selectPostTitles());

        return "index/noticeBoard";
    }
}
