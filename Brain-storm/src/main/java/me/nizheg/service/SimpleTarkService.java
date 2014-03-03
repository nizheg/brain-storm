package me.nizheg.service;

import java.util.Arrays;

public class SimpleTarkService {

	private String symbolFilter;

	public String getSymbolFilter() {
		return symbolFilter;
	}

	public void setSymbolFilter(String symbolFilter) {
		this.symbolFilter = symbolFilter;
	}

	public MultisetDiff calculateMultisetDiff(String firstMultiset, String secondMultiset) {
		if (firstMultiset == null || secondMultiset == null) {
			throw new NullPointerException("Nulls are not allowed for difference calculating");
		}

		firstMultiset = firstMultiset.toLowerCase().replaceAll(symbolFilter, "");
		secondMultiset = secondMultiset.toLowerCase().replaceAll(symbolFilter, "");

		if (firstMultiset.isEmpty()) {
			return new MultisetDiff("", secondMultiset);
		}
		if (secondMultiset.isEmpty()) {
			return new MultisetDiff(firstMultiset, "");
		}

		char[] firstMultisetElems = firstMultiset.toCharArray();
		char[] secondMultisetElems = secondMultiset.toCharArray();
		Arrays.sort(firstMultisetElems);
		Arrays.sort(secondMultisetElems);

		StringBuilder firstComplementBuilder = new StringBuilder();
		StringBuilder secondComplementBuilder = new StringBuilder();
		int firstIndex = 0;
		int secondIndex = 0;

		while (firstIndex < firstMultisetElems.length || secondIndex < secondMultisetElems.length) {
			char char1 = getCharSafely(firstMultisetElems, firstIndex);
			char char2 = getCharSafely(secondMultisetElems, secondIndex);
			if (char1 == char2) {
				firstIndex++;
				secondIndex++;
			} else if (char2 > char1) {
				firstComplementBuilder.append(char1);
				firstIndex++;
			} else {
				secondComplementBuilder.append(char2);
				secondIndex++;
			}
		}
		return new MultisetDiff(firstComplementBuilder.toString(), secondComplementBuilder.toString());
	}

	private char getCharSafely(char[] elements, int index) {
		if (index < elements.length) {
			return elements[index];
		}
		return Character.MAX_VALUE;
	}

	public static class MultisetDiff {
		private final String firstElemComplement;
		private final String secondElemComplement;

		public MultisetDiff(String firstElemComplement, String secondElemComplement) {
			super();
			this.firstElemComplement = firstElemComplement;
			this.secondElemComplement = secondElemComplement;
		}

		/**
		 * @return elements that present in first multiset but absent in second
		 */
		public String getFirstElemComplement() {
			return firstElemComplement;
		}

		/**
		 * @return elements that present in second multiset but absent in first
		 */
		public String getSecondElemComplement() {
			return secondElemComplement;
		}

		@Override
		public String toString() {
			return "[" + firstElemComplement + "],[" + secondElemComplement + "]";
		}

	}

}
