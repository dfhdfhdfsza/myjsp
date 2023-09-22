package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.boardVO;
import domain.pagingVO;
import handler.PagingHandler;
import service.boardService;
import service.boardServiceImpl;


@WebServlet("/brd/*")
public class boardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//로그기록 객체 생성
	private static final Logger log=LoggerFactory.getLogger(boardController.class);
	
	private RequestDispatcher rdp; //jsp에서 받은 요청을 처리,결과를 다른 jsp로 보내는 역할을 하는 객체
	private String destPage; //목적지 주소 기록 변수
	private int isOk; //DB의 구문 체크 값 저장
	
    private boardService bsv;   

    public boardController() 
    {
       bsv= new boardServiceImpl();
        
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri=request.getRequestURI();
		log.info(uri);
		String path=uri.substring(uri.lastIndexOf("/")+1);
		log.info(path);
		switch (path) 
		{
		case "register":
			destPage="/board/register.jsp";
			break;
		case "insert":
			try {
				log.info("edit check 1");
				String title=request.getParameter("title");
				String writer=request.getParameter("writer");
				String content=request.getParameter("content");
				boardVO bvo=new boardVO(title, writer, content);
				log.info("bvo>>"+bvo);
				isOk=bsv.insert(bvo);
				log.info((isOk>0)? "OK":"FAIL");
				destPage="/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "list":
			try {
				log.info("list check 1");
				List<boardVO>list=bsv.list();
				request.setAttribute("list",list);
				destPage="/board/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "detail":
			try {
				log.info("detail check 1");
				int bno=Integer.parseInt(request.getParameter("bno"));
				boardVO bvo=bsv.detail(bno);
				request.setAttribute("bvo", bvo);
				destPage="/board/detail.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "modify":
				try {
					int bno=Integer.parseInt(request.getParameter("bno"));
					boardVO bvo=bsv.detail(bno);
					request.setAttribute("bvo",bvo);
					destPage="/board/modify.jsp";
				} catch (Exception e) {
					e.printStackTrace();
				}
			break;
		case "edit":
			try {
				log.info("edit check 1");
				int bno=Integer.parseInt(request.getParameter("bno"));
				String title=request.getParameter("title");
				String content=request.getParameter("content");
				boardVO bvo=new boardVO(bno, title, content);
				isOk=bsv.edit(bvo);
				log.info((isOk>0)? "OK":"FAIL");
				destPage="/brd/list";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "remove":
			try {
				log.info("remove check 1");
				int bno=Integer.parseInt(request.getParameter("bno"));
				isOk=bsv.remove(bno);
				destPage="/brd/list";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "pageList":
			try {
				pagingVO pgvo=new pagingVO();
				if(request.getParameter("pageNo")!=null)
				{
					int pageNo=Integer.parseInt(request.getParameter("pageNo"));
					int qty=Integer.parseInt(request.getParameter("qty"));
					pgvo=new pagingVO(pageNo, qty);
				}
				String type=request.getParameter("type");
				String keyword=request.getParameter("keyword");
				pgvo.setType(type);
				pgvo.setKeyword(keyword);
				// total count 구해오기
				int totalcount=bsv.totalcount(pgvo);
				log.info("totalcount>>"+totalcount);
				//list 가져오기
				List<boardVO>list=bsv.getPageList(pgvo);
				request.setAttribute("list",list);
				//setattribute
				PagingHandler ph= new PagingHandler(pgvo, totalcount);
				request.setAttribute("ph",ph);
				destPage="/board/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		}//switch end
		
		rdp=request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
		
	}//service end

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		service(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		service(request, response);
	}

}
