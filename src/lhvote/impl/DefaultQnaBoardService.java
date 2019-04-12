package lhvote.impl;

import java.sql.Connection;
import java.util.List;

import lhvote.dao.QnaBoardDAO;
import lhvote.dao.impl.DefaultQnaBoardDAO;
import lhvote.model.QnaBoard;
import lhvote.service.QnaBoardService;

public class DefaultQnaBoardService implements QnaBoardService {

	private static final int PAGE_SIZE = 10;

	QnaBoardDAO qnaBoardDAO;

	public DefaultQnaBoardService(Connection connection) {
		this.qnaBoardDAO = new DefaultQnaBoardDAO(connection);
	}

	@Override
	public int insert(QnaBoard qnaBoard) {
		return qnaBoardDAO.insert(qnaBoard);
	}

	@Override
	public QnaBoard select(int qnaNo) {
		return null;
	}

	@Override
	public List<QnaBoard> selectList(int page) {
		int startIndex = (page - 1) * PAGE_SIZE;
		return qnaBoardDAO.selectList(startIndex, PAGE_SIZE);
	}

	@Override
	public int update(QnaBoard qnaBoard) {
		return qnaBoardDAO.update(qnaBoard);
	}

	@Override
	public int delete(int qnaNo) {
		return 0;
	}

	@Override
	public int getEndPage() {
		return qnaBoardDAO.count() % PAGE_SIZE == 0 ? qnaBoardDAO.count() / PAGE_SIZE
				: qnaBoardDAO.count() / PAGE_SIZE + 1;
	}
}
