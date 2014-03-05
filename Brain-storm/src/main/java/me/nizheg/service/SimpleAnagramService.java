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
		Integer length = null;
		if (sequence.contains("*")) {
			length = sequence.length();
			sequence = sequence.replace("*", "");
		}

		return getAnagrams(sequence, length);
	}

	public List<String> getAccurateAnagrams(String sequence) {
		sequence = prepareSequence(sequence);
		if (sequence.isEmpty()) {
			return Collections.emptyList();
		}
		return convertToString(wordDao.getAccurateAnagrams(sequence));
	}

	public List<String> getAnagrams(String sequence, Integer length) {
		sequence = prepareSequence(sequence);
		if (sequence.isEmpty()) {
			return Collections.emptyList();
		}
		return convertToString(wordDao.getAnagrams(sequence, length));
	}

	private String prepareSequence(String sequence) {
		sequence = sequence.toLowerCase().replaceAll(symbolFilter, "");
		return sequence;
	}

	private List<String> convertToString(List<? extends Word> anagramWords) {
		List<String> anagrams = new ArrayList<String>(anagramWords.size());
		for (Word word : anagramWords) {
			anagrams.add(word.getValue());
		}
		return anagrams;
	}

}
