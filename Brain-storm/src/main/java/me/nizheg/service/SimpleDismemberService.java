package me.nizheg.service;

import me.nizheg.domain.Word;
import me.nizheg.repository.WordDao;
import me.nizheg.repository.WordDaoFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleDismemberService {

	private final Log logger = LogFactory.getLog(getClass());
	private String symbolFilter;
	private String linePattern;
	private WordDaoFactory wordDaoFactory;

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

    public WordDaoFactory getWordDaoFactory() {
        return wordDaoFactory;
    }

    public void setWordDaoFactory(WordDaoFactory wordDaoFactory) {
        this.wordDaoFactory = wordDaoFactory;
    }

    public Set<String> calculate(String dismember, Collection<String> wordTypes) {
		BufferedReader reader = new BufferedReader(new StringReader(dismember));
		List<Part> parts = new ArrayList<Part>();
		String line;
        int totalCount = 0;
		try {
			while ((line = reader.readLine()) != null) {
				line = line.trim().toLowerCase();
				Pattern pattern = Pattern.compile(linePattern);
				Matcher matcher = pattern.matcher(line);
				if (matcher.matches()) {
					String word = matcher.group(1);
					Integer count = Integer.valueOf(matcher.group(2));
					parts.add(new Part(word, count));
                    totalCount += count;
                }
			}
		} catch (NumberFormatException ex) {
			logger.error("Error during parsing dismember " + dismember, ex);
		} catch (IOException ex) {
			logger.error("Error during parsing dismember " + dismember, ex);
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
			return Collections.emptySet();
		}

        Set<String> result = new TreeSet<String>();
        for (String wordType : wordTypes) {
            WordDao dao;
            if ((dao = wordDaoFactory.getDao(wordType)) != null) {
                List<? extends Word> foundWords = dao.getDismemberment(partsOfParts, totalCount);
                result.addAll(convertToString(foundWords));
            }
        }
		return result;
	}

	private List<String> convertToString(List<? extends Word> words) {
		List<String> result = new ArrayList<String>(words.size());
		for (Word word : words) {
            result.add(word.getValue());
		}
		return result;
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
