package lhvote.controller.qnaBoard;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lhvote.controller.Controller;
import lhvote.impl.DefaultQnaBoardService;
import lhvote.model.QnaBoard;
import lhvote.service.QnaBoardService;

public class QnaBoardModifyController implements Controller {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		request.getSession().setAttribute("admin", "admin");
		PrintWriter out = response.getWriter();
		out.write("ok");
		out.flush();
		out.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		QnaBoard qnaBoard = new QnaBoard();
		qnaBoard.setQnaNo(Integer.parseInt(request.getParameter("qnaNo")));
		qnaBoard.setQnaAnswer(request.getParameter("qnaAnswer").replace("\n", ""));
		qnaBoard.setAnswerdQna(true);
		QnaBoardService qnaBoardService = new DefaultQnaBoardService(connection);
		if (qnaBoardService.update(qnaBoard) > 0) {
			return;
		}
		PrintWriter out = response.getWriter();
		out.write("error");
		out.flush();
		out.close();
	}

}
