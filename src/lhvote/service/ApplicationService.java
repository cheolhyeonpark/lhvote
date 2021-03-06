package lhvote.service;

import java.util.List;

import lhvote.model.Application;

public interface ApplicationService {

	int insert(Application application);
	Application select(int applyNo);
	List<Application> selectList(String manName, String aptMobile);
	int update(Application application);
	int delete(int applyNo);
}
