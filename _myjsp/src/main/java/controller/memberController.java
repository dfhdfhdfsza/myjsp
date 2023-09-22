package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.memberVO;
import service.memberService;
import service.memberServiceImpl;


@WebServlet("/mem/*")
public class memberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log=LoggerFactory.getLogger(memberController.class);
    
    private RequestDispatcher rdp;
    private String destPage;
    private int isOk;
    
    private memberService msv;
   
    public memberController() 
    {
        msv=new memberServiceImpl();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri=request.getRequestURI();
		log.info("uri>>"+uri);
		String path=uri.substring(uri.lastIndexOf("/")+1);
		log.info("path>>"+path);
		
		switch (path) 
		{
		case "register":
			destPage="/member/register.jsp";
			break;
		case "join":
			try {
				log.info("join check 1");
				String id=request.getParameter("id");
				String pw= request.getParameter("pw");
				memberVO mvo=new memberVO(id, pw);
				isOk=msv.join(mvo);
				log.info((isOk>0) ? "OK":"FAIl");
				destPage="/index.jsp";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "login":
			try {
				log.info("login check 1");
				String id=request.getParameter("id");
				String pw= request.getParameter("pw");
				memberVO mvo=new memberVO(id, pw);
				memberVO loginmvo=msv.login(mvo);
				if(loginmvo!=null)
				{
					HttpSession ses=request.getSession();
					ses.setAttribute("ses",loginmvo);
					ses.setMaxInactiveInterval(10*60);
				}
				else
				{
					request.setAttribute("msg_login",0);
				}
				destPage="/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "logout":
			try {
				HttpSession ses=request.getSession();
				memberVO mvo=(memberVO)ses.getAttribute("ses");
				String id=mvo.getId();
				isOk=msv.logout(id);
				ses.invalidate();
				destPage="/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "list":
			try {
				log.info("list check 1");
				List<memberVO>list=msv.list();
				request.setAttribute("list",list);
				destPage="/member/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "modify":
				destPage="/member/modify.jsp";
			break;
		case "update":
			try {
				String id=request.getParameter("id");
				String pw= request.getParameter("pw");
				memberVO mvo=new memberVO(id, pw);
				isOk=msv.update(mvo);
				destPage="/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "remove":
			try {
				String id=request.getParameter("id");
				String pw= request.getParameter("pw");
				memberVO mvo=new memberVO(id, pw);
				isOk=msv.remove(mvo);
				HttpSession ses=request.getSession();
				ses.invalidate();
				destPage="/index.jsp";
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
