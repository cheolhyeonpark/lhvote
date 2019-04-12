package lhvote.controller.application;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lhvote.controller.Controller;
import lhvote.impl.DefaultApplicationService;
import lhvote.model.Application;
import lhvote.service.ApplicationService;

public class ApplicationRemoveController implements Controller {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		int applyNo = Integer.parseInt(request.getParameter("applyNo"));
		ApplicationService applicationService = new DefaultApplicationService(connection);
		Application application = applicationService.select(applyNo);
		if (application == null || !request.getSession().getAttribute("manName").equals(application.getManName())) {
			response.sendRedirect("/kvoting/unauthorized.html");			
			return;
		}
		if (applicationService.delete(applyNo) > 0) {
			response.sendRedirect("/kvoting/");
			return;
		}
		response.sendRedirect("/kvoting/error.html");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {

	}
}
