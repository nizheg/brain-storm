package me.nizheg.service;

import me.nizheg.repository.WordDaoFactory;

import java.util.*;

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

    public Set<String> search(String word, int from, int to, Collection<String> wordTypes) {
        word = prepareSequence(word);
        if (word.isEmpty() || wordTypes == null || wordTypes.isEmpty() || from < 1 || to < 1) {
            return Collections.emptySet();
        }

        Set<String> result = new TreeSet<String>();
        char[] replacementSequence = new char[to];
        Arrays.fill(replacementSequence, '*');
        String replacement = new String(replacementSequence);
        for (int i = 0; i <= word.length() - from; i++) {
            String mask = word.substring(0, i) + replacement + word.substring(i + from, word.length());
            result.addAll(simpleSearchService.search(mask, wordTypes));
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
