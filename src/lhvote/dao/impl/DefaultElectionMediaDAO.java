package lhvote.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lhvote.dao.ElectionMediaDAO;
import lhvote.model.ElectionMedia;

public class DefaultElectionMediaDAO extends DatabaseAccessObject implements ElectionMediaDAO {

	public DefaultElectionMediaDAO(Connection connection) {
		super(connection);
	}

	@Override
	public int insert(int applyNo, ElectionMedia electionMedia) {
		int rowCount = 0;
		String sql = "INSERT INTO tbl_media(applyNo, mediaCode) VALUES(?, ?)";
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			preparedStatement.setInt(1, applyNo);
			preparedStatement.setInt(2, electionMedia.getCode());
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	@Override
	public List<ElectionMedia> selectList(int applyNo) {
		List<ElectionMedia> list = null;
		String sql = "SELECT mediaCode FROM tbl_media WHERE applyNo = ?";
		Connection connection = this.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			preparedStatement.setInt(1, applyNo);
			ResultSet resultSet = preparedStatement.executeQuery();
			list = new ArrayList<>();
			while (resultSet.next()) {
				int mediaCode = resultSet.getInt(1);
				list.add(ElectionMedia.valueOf(mediaCode));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int delete(int applyNo) {
		int rowCount = 0;
		String sql = "DELETE FROM tbl_media WHERE applyNo = ?";
		Connection connection = this.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			preparedStatement.setInt(1, applyNo);
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}
}
