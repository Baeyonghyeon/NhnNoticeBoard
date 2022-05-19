package com.nhnacademy.jdbc.board.interceptor;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
	//login로 들어왔을 때는 session이  null이라서 제외시킨다.
	public String loginInessential = "/login";


	//로그인 체크는 실행 전 단계라서 preHandle만 오버라이딩.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler) throws Exception {

		String requestURI = request.getRequestURI();

		log.info("인증 체크 인터셉터 실행 {}", requestURI);

		HttpSession session = request.getSession(false);

		if (Objects.isNull(session) || Objects.isNull(session.getAttribute("id"))) {
			log.info("로그인 세션 정보 없음");

			//로그인페이지로 redirect
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
}
