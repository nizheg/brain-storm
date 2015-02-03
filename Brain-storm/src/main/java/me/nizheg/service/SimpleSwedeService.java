package me.nizheg.service;

import me.nizheg.repository.WordDaoFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Original Author: Nikolay Zhegalin
 * @version 03.02.2015
 * @see © 2015 TER - All Rights Reserved
 * See LICENSE file for further details
 */
public class SimpleSwedeService {

    private String symbolFilter;
    private WordDaoFactory wordDaoFactory;
    private SimpleSearchService simpleSearchService;

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

    public SimpleSearchService getSimpleSearchService() {
        return simpleSearchService;
    }

    public void setSimpleSearchService(SimpleSearchService simpleSearchService) {
        this.simpleSearchService = simpleSearchService;
    }

    public Set<String> search(String word, boolean isBack, Collection<String> wordTypes) {
        word = prepareSequence(word);
        if (word.isEmpty() || wordTypes == null || wordTypes.isEmpty()) {
            return Collections.emptySet();
        }

        Set<String> result = new TreeSet<String>();
        if (isBack) {
            for (int i = 0; i < word.length(); i++) {
                String mask = word.substring(0, i) + "**" + word.substring(i + 1, word.length());
                result.addAll(simpleSearchService.search(mask, wordTypes));
            }
        } else {
            for (int i = 0; i < word.length() - 1; i++) {
                String mask = word.substring(0, i) + "*" + word.substring(i + 2, word.length());
                result.addAll(simpleSearchService.search(mask, wordTypes));
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
