package me.nizheg.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.nizheg.domain.Word;
import me.nizheg.repository.WordDao;

public class SimpleAnagramService {

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

	public void setSymbolFilter(String symbolFilter) {
		this.symbolFilter = symbolFilter;
	}

	public List<String> getAnagrams(String sequence) {
		sequence = sequence.toLowerCase().replaceAll(symbolFilter, "");
		if (sequence.isEmpty()) {
			return Collections.emptyList();
		}
		List<? extends Word> anagramWords = wordDao.getAnagrams(sequence);
		List<String> anagrams = new ArrayList<String>(anagramWords.size());
		for (Word word : anagramWords) {
			anagrams.add(word.getValue());
		}
		return anagrams;
	}

}
