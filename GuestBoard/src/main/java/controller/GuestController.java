package controller;

import java.io.IOException;
import java.sql.SQLException;
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

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new GuestDAO();
		ctx = this.getServletContext(); // 실행정보에 대한 logging을 위해 사용.
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String gId = request.getParameter("gId");
		String view = null;

		if (action == null || action.isEmpty()) {
			action = "list";
		}
		
		try {
			if (action.equals("list")) {
				view = getGuests(request);
				if(gId != null) {
					view = getGuest(request, Integer.parseInt(gId));
				}
			} else if(action.equals("add")){
				view = addGuest(request);
			} else if(action.equals("delete")) {
				view = deleteGuest(request,Integer.parseInt(gId));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (view.startsWith("redirect:/")) {
			String uri = view.substring("redirect:/".length());
			response.sendRedirect(uri);
		} else {
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

	private String addGuest(HttpServletRequest request) {
		Guest g = new Guest();
		try {
			BeanUtils.populate(g, request.getParameterMap());
			dao.add(g);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("방명록 등록에 실패하였습니다.");
			request.setAttribute("err", "방명록 등록 실패");
			return getGuests(request);
		}
		return "redirect:/guest?action=list";
	}

	private String getGuests(HttpServletRequest request) {
		try {
			List<Guest> list = dao.getGuests();
			request.setAttribute("guestList", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("방명록 목록 조회 중 오류 발생");
			request.setAttribute("err", "방명록 목록 조회 실패");
		}

		return "view/guestList.jsp";
	}
	
	private String getGuest(HttpServletRequest request, int gId) {
		try {
			Guest guset = dao.getGuest(gId);
			request.setAttribute("guest", guset);
		}catch(SQLException e) {
			e.printStackTrace();
			ctx.log("방명록 글 상세 조회 오류 발생");
			request.setAttribute("err", "방명록 상세 조회 실패");
		}
		return "view/guestView.jsp";
	}

	private String deleteGuest(HttpServletRequest request, int gId) {
		try {
			Guest g = dao.getGuest(gId);
			String pass = g.getPass();
			String inputPass = request.getParameter("delPass");
			
			if(!inputPass.isBlank() && inputPass != null) {
				if(pass.equals(inputPass)) {
					dao.delete(gId);
				}else {
					throw new SQLException("비밀번호가 일치하지 않습니다.");
				}
			}else {
				throw new SQLException("잘못된 입력값입니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("방명록 삭제 중 오류 발생");
			request.setAttribute("err", "방명록 삭제 실패");
			request.setAttribute("errMsg",e.getMessage());
			return getGuests(request);
		}
		return "redirect:/guest?action=list";
	}
}
