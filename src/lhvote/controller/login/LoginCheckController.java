package lhvote.controller.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lhvote.controller.Controller;

public class LoginCheckController implements Controller {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		System.out.println("===");
		if (request.getSession().getAttribute("admin") == null) {
			return;
		}
		PrintWriter out = response.getWriter();
		out.write("ok");
		out.flush();
		out.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		
	}

}
