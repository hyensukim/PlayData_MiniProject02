package GB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class guestBoardController
 */
@WebServlet("/GBControl")
public class guestBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	guestBoardDAO boardDAO;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		boardDAO = new guestBoardDAO();
	} // init()

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // utf-8 인코딩
		String action = request.getParameter("action");
		String view = "";

		if (request.getParameter("action") == null) {
			getServletContext().getRequestDispatcher("/GBControl?action=list").forward(request, response);
//			action = "list";
		} else {
			switch (action) {
			case "list":
				view = list(request, response);
				break;
			case "insert":
				view = addGuestBoard(request, response);
				break;
			case "delete":
				view = deleteGuestBoard(request, response);
				break;
			default:
                // 알 수 없는 액션 요청 처리
                response.sendRedirect(request.getContextPath() + "/GBControl?action=list");
                break;
			}
			getServletContext().getRequestDispatcher("/GB/" + view).forward(request, response);
		}

	} // service()

	private String list(HttpServletRequest request, HttpServletResponse response) {
		List<guestBoard> list;
		try {
			list = boardDAO.getAll();
			request.setAttribute("guestBoards", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// request.setAttribute("guestBoards", boardDAO.getAll());
		
		return "/guestBoardList.jsp";
	} // list()

	public String addGuestBoard(HttpServletRequest request, HttpServletResponse response) {
		guestBoard GB = new guestBoard();
		try {
			BeanUtils.populate(GB, request.getParameterMap());

			boardDAO.insert(GB);
		} catch (Exception e) {
			e.printStackTrace();
			return list(request, response);	//void일 때 주석처리
		}
//		boardDAO.insert(GB);

//		try {
//			boardDAO.insert(GB);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		return list(request);
		
//	    return "redirect:/GBControl?action=list";

		return "/GBControl?action=list";
		
//		return "/guestBoardList.jsp";
		
//		 try {
//		        response.sendRedirect(request.getContextPath() + "/GBControl?action=list");
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }

	} // deleteGuestBoard()

//	public String getGuestBoard(HttpServletRequest request) {
//		int aid = Integer.parseInt(request.getParameter("aid"));
//		try {
//			guestBoard GB = boardDAO.getAll(aid);
//			request.setAttribute("guestBoard", GB);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

	// 방명록 삭제
	public String deleteGuestBoard(HttpServletRequest request, HttpServletResponse response) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			boardDAO.delete(aid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/GBControl?action=list";
	}
}
