package lhvote.controller.application;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lhvote.controller.Controller;
import lhvote.helper.ApplicationSetter;
import lhvote.impl.DefaultApplicationService;
import lhvote.model.Application;
import lhvote.model.ElectionMedia;
import lhvote.service.ApplicationService;

public class ApplicationModifyController implements Controller {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		ApplicationService applicationService = new DefaultApplicationService(connection);
		int applyNo = Integer.parseInt(request.getParameter("applyNo"));
		Application application = applicationService.select(applyNo);
		if (application != null && request.getSession().getAttribute("manName").equals(application.getManName())) {
			request.setAttribute("apply", application);
			request.setAttribute("elecMedias", ElectionMedia.getCodes(application.getElectionMedias()));
			request.getRequestDispatcher("/applicationModifyForm.jsp").forward(request, response);	
			return;
		}
		response.sendRedirect("/kvoting/unauthorized.html");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		ApplicationService applicationService = new DefaultApplicationService(connection);
		Application application = new ApplicationSetter(request).getApplication();
		application.setElectionMedias(ElectionMedia.getElectionMedias(request.getParameterValues("elecMedia")));
		if (applicationService.update(application) > 0) {
			response.sendRedirect("/kvoting/");
			return;
		} 
		response.sendRedirect("/kvoting/error.html");
	}

}
