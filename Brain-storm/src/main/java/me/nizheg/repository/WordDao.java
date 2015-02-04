package me.nizheg.repository;

import java.util.List;

import me.nizheg.domain.Word;

public interface WordDao {

	Word getWord(String value);

	Word getWord(Long id);

	Word saveWord(Word word);

	List<? extends Word> searchByMask(String mask);

	List<? extends Word> getAnagrams(String value, Integer length);

	List<? extends Word> getAccurateAnagrams(String value);

	List<? extends Word> getDismemberment(List<String[]> parts, int length);

}
