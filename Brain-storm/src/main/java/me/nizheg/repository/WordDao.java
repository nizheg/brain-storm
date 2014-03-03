package me.nizheg.repository;

import java.util.List;

import me.nizheg.domain.Word;

public interface WordDao {

	Word getWord(String value);

	Word getWord(Long id);

	void saveWord(Word word);

	List<? extends Word> searchByMask(String mask);

	List<? extends Word> getAnagrams(String value);

	List<? extends Word> getAccurateAnagrams(String value);

}
