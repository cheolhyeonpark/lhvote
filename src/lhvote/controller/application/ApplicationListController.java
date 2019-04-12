package lhvote.controller.application;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lhvote.controller.Controller;
import lhvote.impl.DefaultApplicationService;
import lhvote.model.Application;
import lhvote.service.ApplicationService;

public class ApplicationListController implements Controller {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		response.sendRedirect("/kvoting/applicationRequestForm.html");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		ApplicationService applicationService = new DefaultApplicationService(connection);
		List<Application> list = applicationService.selectList(request.getParameter("manName"),
				request.getParameter("aptMobile"));
		if (list.size() > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("manName", request.getParameter("manName"));
			request.setAttribute("list", list);
			request.getRequestDispatcher("/applicationList.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("/kvoting/error.html");
	}

}
