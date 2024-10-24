package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import service.CommentService;
import service.CommentServiceImpl;


@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CommentController.class);
    // 동기 통신이 아닌 비동기 통신(데이터만 요청한 곳으로 보내는 방식)
    // RequestDispatcher / destPage가 필요 없음.
    private CommentService csv;

    public CommentController() {
        csv = new CommentServiceImpl();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실제 처리가 이루어지는 메서드
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// setContentType은 설정할 필요 없음. = 동기에서 설정
		String uri = request.getRequestURI(); // /cmt/insert
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>> cmt path > {}", path);
		
		switch(path) {
		case "post" :
			try {
				// js에서 보낸 데이터를 읽어들이는 작업
				// js('Object) -> controller(String) => vommentVO로 변환
				// {"bno": "5","writer": "111", "content" : "11111"}
				StringBuffer sb = new StringBuffer();
				String line = "";
				BufferedReader br = request.getReader(); // request의 body값을 전송
				while((line = br.readLine()) != null) {
					sb.append(line);
					log.info(">>>>> sb > {}", sb.toString());
					
					// CommentVO 객채로 생성
					JSONParser parser = new JSONParser();
					JSONObject jsonObj = (JSONObject)parser.parse(sb.toString());
				
					log.info(">>> jsonObj > {}", jsonObj);
					// key : value 형태로 jsonObj 구성
					int bno = Integer.parseInt(jsonObj.get("bno").toString());
					String writer = jsonObj.get("writer").toString();
					String content = jsonObj.get("content").toString();
					
					CommentVO cvo = new CommentVO(bno, content, writer);
					int isOk = csv.post(cvo);
					log.info(" >>>>> post > {}", (isOk > 0 ? "성공" : "실패"));
					
					// 결과 데이터를 전송 => 화면으로 전송(response 객체의 body에 기록)
					PrintWriter out = response.getWriter();
					out.print(isOk);
				}
			} catch(Exception e) {
				log.info("comment post error!!");
				e.printStackTrace();
			}
			break;
			
		case "list" :
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				List<CommentVO> list = csv.getList(bno);
				log.info(">>>>> list > {}", list);
				
				// list를 json 형태로 변환하여 보내기
				// [{...},{...},{...}] : [] JSONArray
				// {...} : JSONObject
				JSONArray jsonArray = new JSONArray();
				JSONObject[] jsonObjArr = new JSONObject[list.size()];
				
				for(int i = 0; i < list.size(); i++) {
					JSONObject json = new JSONObject();
					
//					jsonObjArr[i] = new JSONObject();
//					jsonObjArr[i].put("cno", list.get(i).getCno());
					
					json.put("cno", list.get(i).getCno());
					json.put("bno", list.get(i).getBno());
					json.put("writer", list.get(i).getWriter());
					json.put("content", list.get(i).getContent());
					json.put("regdate", list.get(i).getRegdate());
//					json.put("readCount", list.get(i).getReadCount());
					
					jsonObjArr[i] = json;
					jsonArray.add(jsonObjArr[i]);
					
				}
				// '[{...},{...},{...}]' => jsonArray -> String으로 변환하여 전송
				String jsonData = jsonArray.toJSONString();
				
				// print
				PrintWriter out = response.getWriter();
				out.print(jsonData);
				
			} catch (Exception e) {
				log.info("comment list error!!");
				e.printStackTrace();
			}
			break;
		case "modify" :
			try {
				StringBuffer sb = new StringBuffer();
				String line = "";
				BufferedReader br = request.getReader();
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
					
					JSONParser parser = new JSONParser();
					JSONObject jsonObj = (JSONObject)parser.parse(sb.toString());
					
					int cno = Integer.parseInt(jsonObj.get("cno").toString());
					String content = jsonObj.get("content").toString();
					
					CommentVO cvo = new CommentVO(cno, content);
					int isOk = csv.modify(cvo);
					log.info(">>> comment modify > {}", (isOk > 0 ? "성공" : "실패"));
					
					PrintWriter out = response.getWriter();
					out.print(isOk);
			} catch (Exception e) {
				log.info("comment modify error!!");
				e.printStackTrace();
			}
			break;
		case "remove" :
			try {
				int cno = Integer.parseInt(request.getParameter("cno"));
				int isOk = csv.remove(cno);
				log.info(">>> comment remove > {}", (isOk > 0 ? "성공" : "실패"));
				
				PrintWriter out = response.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				log.info("comment modify error!!");
				e.printStackTrace();
			}
			break;
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// service에서 처리
		service(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// service에서 처리
		doGet(request, response);
	}

}
