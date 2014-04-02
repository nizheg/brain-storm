package me.nizheg.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import me.nizheg.domain.Word;
import me.nizheg.repository.WordDao;
import me.nizheg.repository.WordDaoFactory;

public class SimpleGapoifikaService {

	private WordDaoFactory wordDaoFactory;
	private String symbolFilter;

	public void setWordDaoFactory(WordDaoFactory wordDaoFactory) {
		this.wordDaoFactory = wordDaoFactory;
	}

	public Set<String> getSupportableWordTypes() {
		return wordDaoFactory.getSupportableNames();
	}

	public String getSymbolFilter() {
		return symbolFilter;
	}

	public void setSymbolFilter(String wordFilter) {
		this.symbolFilter = wordFilter;
	}

	public List<String> calculate(String in, Collection<String> wordTypes, boolean isAccurate) {
		if (symbolFilter != null) {
			in = in.replaceAll(symbolFilter, "");
		}
		if (in.isEmpty()) {
			return Collections.emptyList();
		}
		char[] wordChars = in.toCharArray();
		List<String> parts = new ArrayList<String>();
		StringBuilder partBuilder = new StringBuilder();
		for (char ch : wordChars) {
			if (Character.isUpperCase(ch) && partBuilder.length() > 0) {
				parts.add(partBuilder.toString());
				partBuilder = new StringBuilder();
			}
			partBuilder.append(Character.toLowerCase(ch));
		}
		parts.add(partBuilder.toString());
		List<String> result = searchByMask(getAccurateMask(parts), wordTypes);
		if (isAccurate || !result.isEmpty()) {
			return result;
		}
		return searchByMask(getMaskWithoutWhitespaces(parts), wordTypes);
	}

	private String getMaskWithoutWhitespaces(List<String> parts) {
		StringBuilder maskBuilder = new StringBuilder("%");
		for (String part : parts) {
			maskBuilder.append(part + "%");
		}
		String mask = maskBuilder.toString().trim();
		return mask;
	}

	private List<String> searchByMask(String mask, Collection<String> wordTypes) {
		List<String> result = new ArrayList<String>();
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

	private String getAccurateMask(List<String> parts) {
		StringBuilder maskBuilder = new StringBuilder();
		for (String part : parts) {
			maskBuilder.append(part + "%" + " ");
		}
		String mask = maskBuilder.toString().trim();
		return mask;
	}

}
