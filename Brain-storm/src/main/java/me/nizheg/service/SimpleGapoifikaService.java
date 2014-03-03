package me.nizheg.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.nizheg.domain.Word;
import me.nizheg.repository.WordDao;

public class SimpleGapoifikaService {

	private WordDao wordDao;
	private String symbolFilter;

	public WordDao getWordDao() {
		return wordDao;
	}

	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}

	public String getSymbolFilter() {
		return symbolFilter;
	}

	public void setSymbolFilter(String wordFilter) {
		this.symbolFilter = wordFilter;
	}

	public List<String> calculate(String in) {
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
		List<String> result = searchByMask(getAccurateMask(parts));
		if (!result.isEmpty()) {
			return result;
		}
		return searchByMask(getMaskWithoutWhitespaces(parts));
	}

	private String getMaskWithoutWhitespaces(List<String> parts) {
		StringBuilder maskBuilder = new StringBuilder();
		for (String part : parts) {
			maskBuilder.append(part + "%");
		}
		String mask = maskBuilder.toString().trim();
		return mask;
	}

	private List<String> searchByMask(String mask) {
		List<? extends Word> words = wordDao.searchByMask(mask);
		List<String> result = new ArrayList<String>();
		for (Word word : words) {
			result.add(word.getValue());
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
