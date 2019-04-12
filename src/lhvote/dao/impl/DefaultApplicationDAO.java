package lhvote.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lhvote.dao.ApplicationDAO;
import lhvote.helper.ApplicationSetter;
import lhvote.model.Application;

public class DefaultApplicationDAO extends DatabaseAccessObject implements ApplicationDAO {

	public DefaultApplicationDAO(Connection connection) {
		super(connection);
	}

	@Override
	public int insert(Application application) {
		int applyNo = 0;
		String sql = getInsertSql(application);
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			setPreparedStatement(preparedStatement, application);
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				applyNo = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applyNo;
	}

	private String getInsertSql(Application application) {
		String sql = getColumn(application);
		return "INSERT INTO tbl_apply(" + sql.substring(0, sql.indexOf("SET "));
	}

	private String getColumn(Application application) {
		String left = "";
		String right = "VALUES(";
		String update = "SET ";
		for (Field field : application.getClass().getDeclaredFields()) {
			if (field.getName().equals("applyNo") || field.getName().equals("electionMedias")) {
				continue;
			}
			left += field.getName() + ", ";
			right += "?, ";
			update += field.getName() + " = ?, ";
		}
		return left.substring(0, left.length() - 2) + ") " + right.substring(0, right.length() - 2) + ")" + update;
	}

	private void setPreparedStatement(PreparedStatement preparedStatement, Application application) {
		int index = 1;
		for (Field field : application.getClass().getDeclaredFields()) {
			if (field.getName().equals("applyNo") || field.getName().equals("electionMedias")) {
				continue;
			}
			try {
				setPreparedStatement(preparedStatement, application, field, index);
			} catch (Exception e) {
				e.printStackTrace();
			}
			index++;
		}
	}

	private void setPreparedStatement(PreparedStatement preparedStatement, Application application, Field field,
			int index) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException {
		Method getter = application.getClass().getDeclaredMethod(
				"get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
		Class<?> returnType = getter.getReturnType();
		if (returnType.equals(int.class)) {
			preparedStatement.setInt(index, (int) getter.invoke(application));
			return;
		}
		if (returnType.equals(Date.class)) {
			preparedStatement.setString(index,
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getter.invoke(application)));
			return;
		}
		if (returnType.equals(String.class)) {
			preparedStatement.setString(index, (String) getter.invoke(application));
			return;
		}
	}

	@Override
	public Application select(int applyNo) {
		Application application = null;
		String sql = "SELECT * FROM tbl_apply WHERE applyNo = ?";
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			preparedStatement.setInt(1, applyNo);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				application = new ApplicationSetter(resultSet).getApplication();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return application;
	}

	@Override
	public List<Application> selectList(String manName, String aptMobile) {
		List<Application> list = null;
		String sql = "SELECT * FROM tbl_apply WHERE manName=? AND aptMobile=? ORDER BY applyNo DESC";
		Connection connection = this.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			preparedStatement.setString(1, manName);
			preparedStatement.setString(2, aptMobile);
			ResultSet resultSet = preparedStatement.executeQuery();
			list = new ArrayList<>();
			while (resultSet.next()) {
				ApplicationSetter applicationSetter = new ApplicationSetter(resultSet);
				Application application = applicationSetter.getApplication();
				list.add(application);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int update(Application application) {
		int rowCount = 0;
		String sql = getUpdateSql(application);
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			setPreparedStatement(preparedStatement, application);
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	private String getUpdateSql(Application application) {
		String sql = getColumn(application);
		return "UPDATE tbl_apply " + sql.substring(sql.indexOf("SET "), sql.length() - 2) + " WHERE applyNo="
				+ application.getApplyNo();
	}

	@Override
	public int delete(int applyNo) {
		int rowCount = 0;
		String sql = "DELETE FROM tbl_apply WHERE applyNo = ?";
		Connection connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
			preparedStatement.setInt(1, applyNo);
			rowCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}
}
