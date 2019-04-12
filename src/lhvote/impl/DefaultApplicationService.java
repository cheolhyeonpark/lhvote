package lhvote.impl;

import java.sql.Connection;
import java.util.List;

import lhvote.dao.ApplicationDAO;
import lhvote.dao.ElectionMediaDAO;
import lhvote.dao.impl.DefaultApplicationDAO;
import lhvote.dao.impl.DefaultElectionMediaDAO;
import lhvote.model.Application;
import lhvote.model.ElectionMedia;
import lhvote.service.ApplicationService;

public class DefaultApplicationService implements ApplicationService {

	ApplicationDAO applicationDAO;
	ElectionMediaDAO electionMediaDAO;
	
	public DefaultApplicationService(Connection connection) {
		this.applicationDAO = new DefaultApplicationDAO(connection);
		this.electionMediaDAO = new DefaultElectionMediaDAO(connection);
	}
	
	@Override
	public int insert(Application application) {
		int applyNo = applicationDAO.insert(application);
		if (applyNo > 0) {
			int rowCount = 0;
			ElectionMedia[] electionMedias = application.getElectionMedias();
			for (ElectionMedia electionMedia : electionMedias) {
				rowCount += electionMediaDAO.insert(applyNo, electionMedia);
			}
			return rowCount >= electionMedias.length ? rowCount : 0;
		}
		return 0;
	}

	@Override
	public Application select(int applyNo) {
		Application application = applicationDAO.select(applyNo);
		application.setElectionMedias(getElectionMedias(applyNo));
		return application;
	}

	private ElectionMedia[] getElectionMedias(int applyNo) {
		List<ElectionMedia> list = electionMediaDAO.selectList(applyNo);
		ElectionMedia[] electionMedias = new ElectionMedia[list.size()];
		for (int i = 0; i < electionMedias.length; i++) {
			electionMedias[i] = list.get(i);
		}
		return electionMedias;
	}

	@Override
	public List<Application> selectList(String manName, String aptMobile) {
		List<Application> list = applicationDAO.selectList(manName, aptMobile);
		for (Application application : list) {
			application.setElectionMedias(getElectionMedias(application.getApplyNo()));
		}
		return list;
	}

	@Override
	public int update(Application application) {
		if (applicationDAO.update(application) > 0) {
			int rowCount = electionMediaDAO.delete(application.getApplyNo());
			ElectionMedia[] electionMedias = application.getElectionMedias();
			for (ElectionMedia electionMedia : electionMedias) {
				rowCount += electionMediaDAO.insert(application.getApplyNo(), electionMedia);
			}
			return rowCount > electionMedias.length ? rowCount : 0;
		}
		return 0;
	}

	@Override
	public int delete(int applyNo) {
		int rowCount = applicationDAO.delete(applyNo);
		rowCount += electionMediaDAO.delete(applyNo);
		return rowCount >= 2 ? rowCount : 0;
	}

	
}
