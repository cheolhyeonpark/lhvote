package lhvote.dao;

import java.util.List;

import lhvote.model.QnaBoard;

public interface QnaBoardDAO {
	
	int insert(QnaBoard qnaBoard);
	QnaBoard select(int qnaNo);
	List<QnaBoard> selectList(int startIndex, int pageSize);
	int count();
	int update(QnaBoard qnaBoard);
	int delete(int qnaNo);
}
