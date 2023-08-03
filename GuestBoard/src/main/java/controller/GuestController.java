package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import models.Guest;
import models.GuestDAO;

/**
 * Servlet implementation class GuestController
 */
@WebServlet("/guest")
public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GuestDAO dao;
	private ServletContext ctx;
	
	private static final String INDEX_PAGE = "/view/guestList.jsp";
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new GuestDAO();
		ctx = this.getServletContext(); // 실행정보에 대한 logging을 위해 사용.
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action == null) {
			action = "guestList";
		}
	}
	
	private String addGuest(HttpServletRequest request) {
		Guest g = new Guest();
		try {
			BeanUtils.populate(g, request.getParameterMap());
			dao.add(g);
		} catch (Exception e) {
			ctx.log("방명록 등록에 실패하였습니다.");
			request.setAttribute("err","방명록 등록 실패");
			return getGuests(request);
		}
		return "redirect:/guest?action=list";
	}
	
	private String getGuests(HttpServletRequest request) {
		try {
			List<Guest> list = dao.getGuests();
			request.setAttribute("guestList", list);
		}catch(Exception e) {
			e.printStackTrace();
			ctx.log("방명록 목록 조회 중 오류 발생");
			request.setAttribute("err", "방명록 목록 조회 실패");
		}
		
		return "redirect:/guest?action=list";
	}
	
	private String deleteGuest() {
		return null;
	}
}
