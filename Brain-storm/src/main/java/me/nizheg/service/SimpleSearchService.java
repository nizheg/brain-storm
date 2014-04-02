package me.nizheg.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.nizheg.domain.Word;
import me.nizheg.repository.WordDao;
import me.nizheg.repository.WordDaoFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleSearchService {

	private final Log logger = LogFactory.getLog(getClass());
	private String symbolFilter;
	private WordDaoFactory wordDaoFactory;

	public String getSymbolFilter() {
		return symbolFilter;
	}

	public void setSymbolFilter(String symbolFilter) {
		this.symbolFilter = symbolFilter;
	}

	public void setWordDaoFactory(WordDaoFactory wordDaoFactory) {
		this.wordDaoFactory = wordDaoFactory;
	}

	public Set<String> getSupportableWordTypes() {
		return wordDaoFactory.getSupportableNames();
	}

	public Set<String> search(String mask, Collection<String> wordTypes) {
		mask = prepareSequence(mask);
		if (mask.isEmpty() || wordTypes == null || wordTypes.isEmpty()) {
			return Collections.emptySet();
		}
		Set<String> result = new HashSet<String>();
		for (String wordType : wordTypes) {
			WordDao dao;
			if ((dao = wordDaoFactory.getDao(wordType)) != null) {
				List<? extends Word> foundWords = dao.searchByMask(mask);
				for (Word word : foundWords) {
					result.add(word.getValue());
				}
			}
		}
		return result;
	}

	private String prepareSequence(String sequence) {
		if (sequence == null) {
			return "";
		}
		sequence = sequence.toLowerCase().replaceAll(symbolFilter, "");
		sequence = sequence.replace("*", "_");
		return sequence;
	}

}
