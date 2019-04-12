package lhvote.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lhvote.controller.application.ApplicationAddController;
import lhvote.controller.application.ApplicationListController;
import lhvote.controller.application.ApplicationModifyController;
import lhvote.controller.application.ApplicationRemoveController;
import lhvote.controller.application.ApplicationViewController;
import lhvote.controller.login.LoginCheckController;
import lhvote.controller.login.LoginController;
import lhvote.controller.qnaBoard.QnaBoardAddController;
import lhvote.controller.qnaBoard.QnaBoardEndPageController;
import lhvote.controller.qnaBoard.QnaBoardListController;
import lhvote.controller.qnaBoard.QnaBoardModifyController;
import lhvote.controller.qnaBoard.QnaBoardRemoveController;
import lhvote.pool.ConnectionPool;

@WebServlet({"/application/*","/qnaBoard/*","/member/*"})
@MultipartConfig
public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String APPLICATION_ADD_URL = "/application/add";
	private static final String APPLICATION_LIST_URL = "/application/list";
	private static final String APPLICATION_MODIFY_URL = "/application/modify";
	private static final String APPLICATION_REMOVE_URL = "/application/remove";
	private static final String APPLICATION_VIEW_URL = "/application/view";
	private static final String QNABOARD_ADD_URL = "/qnaBoard/add";
	private static final String QNABOARD_LIST_URL = "/qnaBoard/list";
	private static final String QNABOARD_MODIFY_URL = "/qnaBoard/modify";
	private static final String QNABOARD_REMOVE_URL = "/qnaBoard/remove";
	private static final String QNABOARD_END_PAGE_URL = "/qnaBoard/endpage";
	private static final String LOGIN_URL = "/member/login";
	private static final String LOGIN_CHECK_URL = "/member/loginCheck";

	HashMap<String, Controller> subControllers = new HashMap<String, Controller>();

	@Override
	public void init() throws ServletException {
		subControllers.put(APPLICATION_ADD_URL, new ApplicationAddController());
		subControllers.put(APPLICATION_LIST_URL, new ApplicationListController());
		subControllers.put(APPLICATION_MODIFY_URL, new ApplicationModifyController());
		subControllers.put(APPLICATION_REMOVE_URL, new ApplicationRemoveController());
		subControllers.put(APPLICATION_VIEW_URL, new ApplicationViewController());
		subControllers.put(QNABOARD_ADD_URL, new QnaBoardAddController());
		subControllers.put(QNABOARD_LIST_URL, new QnaBoardListController());
		subControllers.put(QNABOARD_MODIFY_URL, new QnaBoardModifyController());
		subControllers.put(QNABOARD_REMOVE_URL, new QnaBoardRemoveController());
		subControllers.put(QNABOARD_END_PAGE_URL, new QnaBoardEndPageController());
		subControllers.put(LOGIN_URL, new LoginController());
		subControllers.put(LOGIN_CHECK_URL, new LoginCheckController());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (isSubControllers(request)) {
			ConnectionPool connectionPool = (ConnectionPool) getServletContext().getAttribute("connectionPool");
			Connection connection = connectionPool.getConnection();
			callMethod(request, response, connection);
			connectionPool.releaseConnection(connection);
			return;
		}
		response.sendRedirect("/kvoting/error.html");
	}

	private boolean isSubControllers(HttpServletRequest request) {
		return subControllers.containsKey(getPath(request));
	}

	private void callMethod(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		if ("GET".equals(request.getMethod())) {
			subControllers.get(getPath(request)).doGet(request, response, connection);
			return;
		}
		subControllers.get(getPath(request)).doPost(request, response, connection);
	}

	private String getPath(HttpServletRequest request) {
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		return url.substring(contextPath.length());
	}
}
