package lhvote.dao;

import java.util.List;

import lhvote.model.ElectionMedia;

public interface ElectionMediaDAO {

	int insert(int applyNo, ElectionMedia electionMedia);
	List<ElectionMedia> selectList(int applyNo);
	int delete(int applyNo);
}
