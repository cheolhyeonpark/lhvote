package lhvote.controller.qnaBoard;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lhvote.controller.Controller;
import lhvote.impl.DefaultQnaBoardService;
import lhvote.service.QnaBoardService;

public class QnaBoardEndPageController implements Controller {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		QnaBoardService qnaBoardService = new DefaultQnaBoardService(connection);
		int endPage = qnaBoardService.getEndPage();
		PrintWriter out = response.getWriter();
		out.write(""+endPage);
		out.flush();
		out.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
	}
}
