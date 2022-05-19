package com.nhnacademy.jdbc.board.index.web;

import com.nhnacademy.jdbc.board.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping
@Slf4j
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/","/index"})
    public String index(Model model){
//        Optional<User> user = userService.getUser("admin");
//        if(user.isPresent()){
//            log.debug("user : {}",user.get());
//            model.addAttribute("id", user.get().getId());
//            model.addAttribute("password", user.get().getPassword());
//            model.addAttribute("name", user.get().getName());
//            model.addAttribute("userSeparateCode", user.get().getUserSeparateCode());
//        }
        return "index/loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap modelMap) {
        if (userService.matches(id, pwd)) {
            log.debug("넘어온 id값 : ? ", id);
            log.debug("넘어온 pwd값 : ? ", pwd);
            //성공시
            HttpSession session = request.getSession();
            session.setAttribute("id", id);

            modelMap.put("id", session.getAttribute("id"));
            return "index/index";
        } else {
            //실패시
            return "redirect:/login";
        }
    }
}
