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

public class ApplicationAddController implements Controller {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		response.sendRedirect("/kvoting/applicationApplyPolicy.html");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		ApplicationService applicationService = new DefaultApplicationService(connection);
		Application application = new ApplicationSetter(request).getApplication();
		application.setElectionMedias(ElectionMedia.getElectionMedias(request.getParameterValues("elecMedia")));
		if (applicationService.insert(application) > 0) {
			response.sendRedirect("/kvoting/");
			return;
		}
		response.sendRedirect("/kvoting/error.html");
	}
}
