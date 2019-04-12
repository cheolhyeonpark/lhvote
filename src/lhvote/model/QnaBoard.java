package lhvote.model;

import java.sql.Date;

public class QnaBoard {

	int qnaNo;
	String qnaTitle;
	String qnaPassword;
	String qnaQuestion;
	String qnaAnswer;
	Date qnaDate;
	boolean answerdQna;
	boolean openedQna;

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaPassword() {
		return qnaPassword;
	}

	public void setQnaPassword(String qnaPassword) {
		this.qnaPassword = qnaPassword;
	}

	public String getQnaQuestion() {
		return qnaQuestion;
	}

	public void setQnaQuestion(String qnaQuestion) {
		this.qnaQuestion = qnaQuestion;
	}

	public String getQnaAnswer() {
		return qnaAnswer;
	}

	public void setQnaAnswer(String qnaAnswer) {
		this.qnaAnswer = qnaAnswer;
	}

	public Date getQnaDate() {
		return qnaDate;
	}

	public void setQnaDate(Date qnaDate) {
		this.qnaDate = qnaDate;
	}

	public boolean isAnswerdQna() {
		return answerdQna;
	}

	public void setAnswerdQna(boolean answerdQna) {
		this.answerdQna = answerdQna;
	}

	public boolean isOpenedQna() {
		return openedQna;
	}

	public void setOpenedQna(boolean openedQna) {
		this.openedQna = openedQna;
	}
}
