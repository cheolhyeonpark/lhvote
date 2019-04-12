package lhvote.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lhvote.dao.QnaBoardDAO;
import lhvote.model.QnaBoard;

public class DefaultQnaBoardDAO extends DatabaseAccessObject implements QnaBoardDAO {

	public DefaultQnaBoardDAO(Connection connection) {
		super(connection);
	}

	@Override
	public int insert(QnaBoard qnaBoard) {
		int rowCount = 0;
		String sql = "INSERT INTO tbl_qnaBoard(qnaTitle, qnaPassword, qnaQuestion, qnaDate, qnaIsAnswerd, qnaIsOpened) VALUES(?, ?, ?, NOW(), ?, ?)";
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			preparedStatement.setString(1, qnaBoard.getQnaTitle());
			preparedStatement.setString(2, qnaBoard.getQnaPassword());
			preparedStatement.setString(3, qnaBoard.getQnaQuestion());
			preparedStatement.setBoolean(4, qnaBoard.isAnswerdQna());
			preparedStatement.setBoolean(5, qnaBoard.isOpenedQna());
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	@Override
	public QnaBoard select(int qnaNo) {
		return null;
	}

	@Override
	public List<QnaBoard> selectList(int startIndex, int pageSize) {
		List<QnaBoard> list = null;
		String sql = "SELECT * FROM tbl_qnaBoard ORDER BY qnaNo DESC LIMIT ?, ?";
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			preparedStatement.setInt(1, startIndex);
			preparedStatement.setInt(2, pageSize);
			ResultSet resultSet = preparedStatement.executeQuery();
			list = new ArrayList<QnaBoard>();
			while (resultSet.next()) {
				QnaBoard qnaBoard = new QnaBoard();
				qnaBoard.setAnswerdQna(resultSet.getBoolean("qnaIsAnswerd"));
				qnaBoard.setQnaPassword(resultSet.getString("qnaPassword"));
				qnaBoard.setQnaQuestion(resultSet.getString("qnaQuestion"));
				qnaBoard.setOpenedQna(resultSet.getBoolean("qnaIsOpened"));
				qnaBoard.setQnaAnswer(resultSet.getString("qnaAnswer"));
				qnaBoard.setQnaTitle(resultSet.getString("qnaTitle"));
				qnaBoard.setQnaDate(resultSet.getDate("qnaDate"));
				qnaBoard.setQnaNo(resultSet.getInt("qnaNo"));
				list.add(qnaBoard);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int update(QnaBoard qnaBoard) {
		int rowCount = 0;
		String sql = "UPDATE tbl_qnaBoard SET qnaAnswer=?, qnaIsAnswerd=? WHERE qnaNo=?";
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			preparedStatement.setString(1, qnaBoard.getQnaAnswer());
			preparedStatement.setBoolean(2, qnaBoard.isAnswerdQna());
			preparedStatement.setInt(3, qnaBoard.getQnaNo());
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	@Override
	public int delete(int qnaNo) {
		return 0;
	}

	@Override
	public int count() {
		int rowCount = 0;
		String sql = "SELECT count(*) FROM tbl_qnaBoard";
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				rowCount = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}
}
