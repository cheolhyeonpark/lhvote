package lhvote.helper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import lhvote.model.Application;

public class ApplicationSetter {

	private static final String SAVE_DIR = "uploadFiles";
	private String savePath;
	private Application application;
	private HttpServletRequest request;
	private ResultSet resultSet;

	public ApplicationSetter() {
		this.application = new Application();
		this.request = null;
		this.savePath = null;
		this.resultSet = null;
	}

	public ApplicationSetter(HttpServletRequest request) {
		this.application = new Application();
		this.request = request;
		this.savePath = getSavePath();
		this.resultSet = null;
	}

	public ApplicationSetter(ResultSet resultSet) {
		this.application = new Application();
		this.request = null;
		this.savePath = null;
		this.resultSet = resultSet;
	}

	private String getSavePath() {
		return request.getServletContext().getRealPath("") + SAVE_DIR;
	}

	public Application getApplication() {
		findSetter();
		return application;
	}

	private void findSetter() {
		Method[] methods = application.getClass().getDeclaredMethods();
		for (Method method : methods) {
			findField(method);
		}
	}

	private void findField(Method method) {
		if (isSetterMethod(method)) {
			String fieldName = getFieldName(method);
			if (fieldName.equals("electionMedias")) {
				return;
			}
			if (hasRequest()) {
				setByRequest(method, fieldName);
				return;
			}
			if (hasResultSet()) {
				setByResultSet(method, fieldName);
			}
		}
	}

	private boolean isSetterMethod(Method method) {
		return method.getName().startsWith("set");
	}

	private String getFieldName(Method method) {
		return method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
	}

	private boolean hasRequest() {
		return request != null;
	}

	private void setByRequest(Method setter, String fieldName) {
		try {
			if (hasParameter(fieldName)) {
				setApplication(setter, request.getParameter(fieldName));
				return;
			}
			if (hasPart(fieldName)) {
				Part part = request.getPart(fieldName);
				String fileName = getFileName(part);
				uploadFile(fileName, part);
				setApplication(setter, fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean hasPart(String fieldName) throws IOException, ServletException {
		for (Part part : request.getParts()) {
			if (part.getName().equals(fieldName)) {
				return request.getPart(fieldName).getSize() > 0;
			}
		}
		return false;
	}

	private boolean hasParameter(String fieldName) {
		return request.getParameterMap().containsKey(fieldName) && request.getParameter(fieldName).length() > 0;
	}

	private void uploadFile(String fileName, Part part) {
		try {
			createFileSaveDir();
			part.write(savePath + File.separator + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createFileSaveDir() {
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
	}

	private String getFileName(Part part) {
		return request.getParameter("aptName") + part.getName() + extractFileName(part);
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	private boolean hasResultSet() {
		return resultSet != null;
	}

	private void setByResultSet(Method setter, String fieldName) {
		try {
			setApplication(setter, resultSet.getString(fieldName));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setApplication(Method setter, String parameter) {
		try {
			Class<?>[] parameters = setter.getParameterTypes();
			if (parameters[0].equals(int.class)) {
				setter.invoke(application, Integer.parseInt(parameter));
				return;
			}
			if (parameters[0].equals(Date.class)) {
				setter.invoke(application, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parameter));
				return;
			}
			if (parameters[0].equals(String.class)) {
				setter.invoke(application, parameter);
				return;
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | ParseException e) {
			e.printStackTrace();
		}
	}
}
