package lhvote.service;

import java.util.List;

import lhvote.model.QnaBoard;

public interface QnaBoardService {

	int insert(QnaBoard qnaBoard);
	QnaBoard select(int qnaNo);
	List<QnaBoard> selectList(int page);
	int getEndPage();
	int update(QnaBoard qnaBoard);
	int delete(int qnaNo);
}
