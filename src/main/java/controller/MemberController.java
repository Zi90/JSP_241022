package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//로그객체
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    //동기방식 : requestDispatcher : request에 대한 응답 데이터를 jsp(html) 화면으로 전송하는 역할
	private RequestDispatcher rdp;
	//목적지 주소
	private String destPage;
	private int isOk;
	
	//service
	private MemberService msv;
	
    public MemberController() {
        msv = new MemberServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 처리
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청주소 추출
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>>> member path > {}", path);
		
		switch(path) {
		case "join":
			destPage="/member/join.jsp";
			break;
		case "register":
			//jsp에서 온 파라미터 받기
			//member 객체 생성 후 service에게 등록 요청
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				
				MemberVO mvo = new MemberVO(id, pwd, email, phone);
				isOk = msv.join(mvo);
				log.info(">>>> join > {} ", (isOk>0 ? "OK":"FAIL" ));
				destPage = "/index.jsp";

			} catch (Exception e) {
				log.info("join error!!");
				e.printStackTrace();
			}
			break;	
		
		case "login":
			try {
				// 로그인 : id, pwd 파라미터로 받아서 DB에 해당 id가 있는지 확인, pwd가 일치하는지 확인
				// 정보를 가져와서 session 객체에 저장
				// session : 모든 jsp에 공유되는 객체 / 브라우저가 종료되면 삭제
				// ${변수명}
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				
				MemberVO mvo = new MemberVO(id, pwd);
				MemberVO loginMvo = msv.login(mvo);
				//로그인 정보가 잘못되면 loginMvo는 null로 출력
				log.info(">>>> loginMvo > {}", loginMvo);
				
				//session에 저장
				if(loginMvo != null) {
					// 로그인이 성공했다면...
					// 세션에 저장
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginMvo);
					//로그인 유지시간 초단위로 설정 10분
					ses.setMaxInactiveInterval(10*60);
				}else {
					// 로그인이 실패했다면... index.jsp로 메시지 전송
					request.setAttribute("msg_login", -1);
				}
				destPage = "/index.jsp";
				
			} catch (Exception e) {
				log.info("login error");
				e.printStackTrace();
			}
			break;
			
		case "logout":
			try {
				//session에 값이 있다면 해당 세션을 끊어라.
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				log.info(">>>> ses 에서 추출한 mvo > {}", mvo);
				
				// lastlogin update
				isOk = msv.lastLogin(mvo.getId());
				log.info(">>>> lastLogin update >> {}", (isOk > 0 ? "OK" : "FAIL"));
				ses.invalidate(); // 세션 무효화(세션 끊기)
				destPage="/index.jsp";
				
			} catch (Exception e) {
				log.info("logout error");
				e.printStackTrace();
			}
			break;
		}
		
		// rdp 전송
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}