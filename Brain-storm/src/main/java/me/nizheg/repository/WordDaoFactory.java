package me.nizheg.repository;

import java.util.Set;

public interface WordDaoFactory {

	WordDao getDao(String name);

	Set<String> getSupportableNames();
}
