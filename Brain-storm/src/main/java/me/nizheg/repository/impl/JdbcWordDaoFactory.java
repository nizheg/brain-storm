package me.nizheg.repository.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import me.nizheg.repository.WordDao;
import me.nizheg.repository.WordDaoFactory;

public class JdbcWordDaoFactory implements WordDaoFactory {

	private Map<String, ? extends WordDao> dao = Collections.emptyMap();

	public void setDao(Map<String, ? extends WordDao> dao) {
		if (dao != null) {
			this.dao = dao;
		}
	}

	@Override
	public WordDao getDao(String name) {
		return dao.get(name);
	}

	@Override
	public Set<String> getSupportableNames() {
		return Collections.unmodifiableSet(dao.keySet());
	}

}
