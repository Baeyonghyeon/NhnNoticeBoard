package com.nhnacademy.jdbc.board.index.web;

import com.nhnacademy.jdbc.board.user.service.UserService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping
@Slf4j
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping(value = {"/", "/login"})
	public String login(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("id"))) {
			// 로그인 안되있으면 loginForm
			return "index/loginForm";
		} else {
			return "index/home";
		}
	}


	@PostMapping("/login")
	public String doLogin(@RequestParam("id") String id,
		@RequestParam("pwd") String pwd,
		HttpServletRequest request,
		ModelMap modelMap) {
		if (userService.matches(id, pwd)) {
			log.debug("넘어온 id값 : ? ", id);
			log.debug("넘어온 pwd값 : ? ", pwd);
			//성공시
			HttpSession session = request.getSession();
			session.setAttribute("id", id);

			modelMap.put("id", session.getAttribute("id"));
			return "index/loginSuccess";
		} else {
			//실패시
			return "redirect:/login";
		}
	}
}
