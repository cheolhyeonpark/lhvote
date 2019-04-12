package lhvote.controller.qnaBoard;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lhvote.controller.Controller;
import lhvote.impl.DefaultQnaBoardService;
import lhvote.model.QnaBoard;
import lhvote.service.QnaBoardService;

public class QnaBoardListController implements Controller {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		QnaBoardService qnaBoardService = new DefaultQnaBoardService(connection);
		List<QnaBoard> list = qnaBoardService.selectList(page);
		PrintWriter out = response.getWriter();
		out.write(convertListToJson(list));
	}

	private String convertListToJson(List<QnaBoard> list) {
		String json = "{\"list\":[";
		for (QnaBoard qnaBoard : list) {
			json += "{" + "\"qnaIsAnswered" + "\" : \"" + qnaBoard.isAnswerdQna() + "\"," + "\"qnaNo" + "\" : \""
					+ qnaBoard.getQnaNo() + "\"," + "\"qnaTitle" + "\" : \"" + qnaBoard.getQnaTitle() + "\","
					+ "\"qnaDate" + "\" : \"" + qnaBoard.getQnaDate() + "\"," + "\"qnaIsOpened" + "\" : \""
					+ qnaBoard.isOpenedQna() + "\"," + "\"qnaPassword" + "\" : \"" + qnaBoard.getQnaPassword() + "\","
					+ "\"qnaQuestion" + "\" : \"" + qnaBoard.getQnaQuestion().replaceAll("\n", "") + "\"," + "\"qnaAnswer" + "\" : \""
					+ qnaBoard.getQnaAnswer() + "\"},";
		}
		return json.substring(0,json.length()-1) + "]}";
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response, Connection connection)
			throws ServletException, IOException {
	}

}
