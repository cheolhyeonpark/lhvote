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

public class QnaBoardAddController implements Controller {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		response.sendRedirect("/kvoting/qnaBoardList.html");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		if (!isRightSecureCode(request)) {
			PrintWriter out = response.getWriter();
			out.write("incorrectSecureCode");
			out.flush();
			out.close();
			return;
		}
		QnaBoardService qnaBoardService = new DefaultQnaBoardService(connection);
		QnaBoard qnaBoard = getQnaBoardFrom(request);
		if (qnaBoardService.insert(qnaBoard) > 0) {
			return;
		}
		PrintWriter out = response.getWriter();
		out.write("error");
		out.flush();
		out.close();
	}
	
	private boolean isRightSecureCode(HttpServletRequest request) {
		return request.getParameter("secureCode").equals(request.getSession().getAttribute("captcha"));
	}

	private QnaBoard getQnaBoardFrom(HttpServletRequest request) {
		QnaBoard qnaBoard = new QnaBoard();
		qnaBoard.setQnaTitle(request.getParameter("qnaTitle"));
		qnaBoard.setQnaQuestion(request.getParameter("qnaQuestion").replaceAll("\n", ""));
		qnaBoard.setAnswerdQna(false);
		qnaBoard.setOpenedQna(true);
		if (request.getParameter("qnaPassword") != null) {
			qnaBoard.setQnaPassword(request.getParameter("qnaPassword"));
			qnaBoard.setOpenedQna(false);
		}
		return qnaBoard;
	}
}
