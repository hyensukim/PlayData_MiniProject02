package GB;

import java.io.IOException;

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
		} else {
			switch (action) {
			case "list":
				view = list(request, response);
				break;
			case "insert":
				view = insert(request, response);
				break;
			}
			getServletContext().getRequestDispatcher("/GB/" + view).forward(request, response);
		}

	} // service()

	private String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("guestBoards", boardDAO.getAll());
		return "guestBoardList.jsp";
	} // list()

	public String insert(HttpServletRequest request, HttpServletResponse response) {
		guestBoard GB = new guestBoard();
		try {
			BeanUtils.populate(GB, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
//		boardDAO.insert(GB);
		try {
			boardDAO.insert(GB);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list(request, response);
	} // insert()
}
