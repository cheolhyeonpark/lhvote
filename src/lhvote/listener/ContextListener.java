package lhvote.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lhvote.pool.ConnectionPool;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("connectionPool", getConnectionPool(servletContext));
	}
	
	private ConnectionPool getConnectionPool(ServletContext servletContext) {
		String driverClassName = servletContext.getInitParameter("driverClassName");
		String url = servletContext.getInitParameter("url");
		String userName = servletContext.getInitParameter("userName");
		String password = servletContext.getInitParameter("password");
		return new ConnectionPool(driverClassName, url, userName, password);
	}
}
