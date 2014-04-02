package me.nizheg.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.nizheg.domain.Word;
import me.nizheg.repository.WordDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleDismemberService {

	private final Log logger = LogFactory.getLog(getClass());
	private String symbolFilter;
	private String linePattern;
	private WordDao wordDao;

	public String getSymbolFilter() {
		return symbolFilter;
	}

	public void setSymbolFilter(String symbolFilter) {
		this.symbolFilter = symbolFilter;
	}

	public String getLinePattern() {
		return this.linePattern;
	}

	public void setLinePattern(String linePattern) {
		this.linePattern = linePattern;
	}

	public WordDao getWordDao() {
		return this.wordDao;
	}

	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}

	public List<String> calculateMovie(String dismember) {
		BufferedReader reader = new BufferedReader(new StringReader(dismember));
		List<Part> parts = new ArrayList<Part>();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				Pattern pattern = Pattern.compile(linePattern);
				Matcher matcher = pattern.matcher(line);
				if (matcher.matches()) {
					String word = matcher.group(1);
					Integer count = Integer.valueOf(matcher.group(2));
					parts.add(new Part(word, count));
				}
			}
		} catch (NumberFormatException ex) {
			logger.error("Error during parsing dismbmeber " + dismember, ex);
		} catch (IOException ex) {
			logger.error("Error during parsing dismbmeber " + dismember, ex);
		}
		List<String[]> partsOfParts = new ArrayList<String[]>();
		for (Part part : parts) {
			List<String> subParts = new ArrayList<String>();
			String str = part.getStr();
			int count = part.getCount().intValue();
			for (int i = 0; i < str.length() - count + 1; i++) {
				subParts.add(str.substring(i, i + count));
			}
			partsOfParts.add(subParts.toArray(new String[subParts.size()]));
		}
		if (partsOfParts.isEmpty()) {
			return Collections.emptyList();
		}
		List<? extends Word> words = wordDao.getDismemberment(partsOfParts, true);
		if (words.isEmpty()) {
			words = wordDao.getDismemberment(partsOfParts, false);
		}
		return convertToString(words);
	}

	private List<String> convertToString(List<? extends Word> anagramWords) {
		List<String> anagrams = new ArrayList<String>(anagramWords.size());
		for (Word word : anagramWords) {
			anagrams.add(word.getValue());
		}
		return anagrams;
	}

	private static class Part {
		private final String str;
		private final Integer count;

		public Part(String str, Integer count) {
			this.count = count;
			this.str = str;
		}

		public String getStr() {
			return this.str;
		}

		public Integer getCount() {
			return this.count;
		}

	}

}
