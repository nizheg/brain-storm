package me.nizheg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Original Author: Nikolay Zhegalin
 * @version 11.02.2015
 * @see © 2015 TER - All Rights Reserved
 * See LICENSE file for further details
 */
public class SimpleBrailleService {

    Map<Byte, Character> russianAlphabet = new HashMap<Byte, Character>();
    Map<Byte, Character> englishAlphabet = new HashMap<Byte, Character>();
    Map<Byte, Character> numbers = new HashMap<Byte, Character>();
    Map<Byte, Character> symbols = new HashMap<Byte, Character>();

    public SimpleBrailleService() {
        russianAlphabet.put(Byte.valueOf("100000", 2), 'а');
        russianAlphabet.put(Byte.valueOf("110000", 2), 'б');
        russianAlphabet.put(Byte.valueOf("010111", 2), 'в');
        russianAlphabet.put(Byte.valueOf("110110", 2), 'г');
        russianAlphabet.put(Byte.valueOf("100110", 2), 'д');
        russianAlphabet.put(Byte.valueOf("100010", 2), 'е');
        russianAlphabet.put(Byte.valueOf("100001", 2), 'ё');
        russianAlphabet.put(Byte.valueOf("010110", 2), 'ж');
        russianAlphabet.put(Byte.valueOf("101011", 2), 'з');
        russianAlphabet.put(Byte.valueOf("010100", 2), 'и');
        russianAlphabet.put(Byte.valueOf("111101", 2), 'й');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'к');
        russianAlphabet.put(Byte.valueOf("111000", 2), 'л');
        russianAlphabet.put(Byte.valueOf("101100", 2), 'м');
        russianAlphabet.put(Byte.valueOf("101110", 2), 'н');
        russianAlphabet.put(Byte.valueOf("101010", 2), 'о');
        russianAlphabet.put(Byte.valueOf("111100", 2), 'п');
        russianAlphabet.put(Byte.valueOf("111010", 2), 'р');
        russianAlphabet.put(Byte.valueOf("011100", 2), 'с');
        russianAlphabet.put(Byte.valueOf("011110", 2), 'т');
        russianAlphabet.put(Byte.valueOf("101001", 2), 'у');
        russianAlphabet.put(Byte.valueOf("110100", 2), 'ф');
        russianAlphabet.put(Byte.valueOf("110010", 2), 'х');
        russianAlphabet.put(Byte.valueOf("100100", 2), 'ц');
        russianAlphabet.put(Byte.valueOf("111110", 2), 'ч');
        russianAlphabet.put(Byte.valueOf("100011", 2), 'ш');
        russianAlphabet.put(Byte.valueOf("101101", 2), 'щ');
        russianAlphabet.put(Byte.valueOf("111011", 2), 'ъ');
        russianAlphabet.put(Byte.valueOf("011101", 2), 'ы');
        russianAlphabet.put(Byte.valueOf("011111", 2), 'ь');
        russianAlphabet.put(Byte.valueOf("010101", 2), 'э');
        russianAlphabet.put(Byte.valueOf("110011", 2), 'ю');
        russianAlphabet.put(Byte.valueOf("110101", 2), 'я');

        englishAlphabet.put(Byte.valueOf("100000", 2), 'a');
        englishAlphabet.put(Byte.valueOf("110000", 2), 'b');
        englishAlphabet.put(Byte.valueOf("100100", 2), 'c');
        englishAlphabet.put(Byte.valueOf("100110", 2), 'd');
        englishAlphabet.put(Byte.valueOf("100010", 2), 'e');
        englishAlphabet.put(Byte.valueOf("110100", 2), 'f');
        englishAlphabet.put(Byte.valueOf("110110", 2), 'g');
        englishAlphabet.put(Byte.valueOf("110010", 2), 'h');
        englishAlphabet.put(Byte.valueOf("010100", 2), 'i');
        englishAlphabet.put(Byte.valueOf("010110", 2), 'j');
        englishAlphabet.put(Byte.valueOf("101000", 2), 'k');
        englishAlphabet.put(Byte.valueOf("111000", 2), 'l');
        englishAlphabet.put(Byte.valueOf("101100", 2), 'm');
        englishAlphabet.put(Byte.valueOf("101110", 2), 'n');
        englishAlphabet.put(Byte.valueOf("101010", 2), 'o');
        englishAlphabet.put(Byte.valueOf("111100", 2), 'p');
        englishAlphabet.put(Byte.valueOf("111110", 2), 'q');
        englishAlphabet.put(Byte.valueOf("111010", 2), 'r');
        englishAlphabet.put(Byte.valueOf("011100", 2), 's');
        englishAlphabet.put(Byte.valueOf("011110", 2), 't');
        englishAlphabet.put(Byte.valueOf("101001", 2), 'u');
        englishAlphabet.put(Byte.valueOf("111001", 2), 'v');
        englishAlphabet.put(Byte.valueOf("010111", 2), 'w');
        englishAlphabet.put(Byte.valueOf("101101", 2), 'x');
        englishAlphabet.put(Byte.valueOf("101111", 2), 'y');
        englishAlphabet.put(Byte.valueOf("101011", 2), 'z');

        numbers.put(Byte.valueOf("010110", 2), '0');
        numbers.put(Byte.valueOf("100000", 2), '1');
        numbers.put(Byte.valueOf("110000", 2), '2');
        numbers.put(Byte.valueOf("100100", 2), '3');
        numbers.put(Byte.valueOf("100110", 2), '4');
        numbers.put(Byte.valueOf("100010", 2), '5');
        numbers.put(Byte.valueOf("110100", 2), '6');
        numbers.put(Byte.valueOf("110110", 2), '7');
        numbers.put(Byte.valueOf("110010", 2), '8');
        numbers.put(Byte.valueOf("010100", 2), '9');

        symbols.put(Byte.valueOf("010011", 2), '.');
        symbols.put(Byte.valueOf("010000", 2), ',');
        symbols.put(Byte.valueOf("010001", 2), '?');
        symbols.put(Byte.valueOf("011000", 2), ';');
        symbols.put(Byte.valueOf("011010", 2), '!');
        symbols.put(Byte.valueOf("011001", 2), '«');
        symbols.put(Byte.valueOf("001011", 2), '»');
        symbols.put(Byte.valueOf("011011", 2), '(');
        symbols.put(Byte.valueOf("100011", 2), ')');
        symbols.put(Byte.valueOf("001001", 2), '-');

    }

    public List<Character> getAllPossibleVariants(boolean[] values) {
        List<Character> result = new ArrayList<Character>();
        Character character = getRussianLetter(values);
        if (character != null) {
            result.add(character);
        }
        character = getEnglishLetter(values);
        if (character != null) {
            result.add(character);
        }
        character = getNumber(values);
        if (character != null) {
            result.add(character);
        }
        character = getSymbol(values);
        if (character != null) {
            result.add(character);
        }
        return result;
    }

    public Character getRussianLetter(boolean[] values) {
        Byte byteRepresentation = getByte(values);
        return russianAlphabet.get(byteRepresentation);
    }

    public Character getEnglishLetter(boolean[] values) {
        Byte byteRepresentation = getByte(values);
        return englishAlphabet.get(byteRepresentation);
    }

    public Character getNumber(boolean[] values) {
        Byte byteRepresentation = getByte(values);
        return numbers.get(byteRepresentation);
    }

    public Character getSymbol(boolean[] values) {
        Byte byteRepresentation = getByte(values);
        return symbols.get(byteRepresentation);
    }

    private Byte getByte(boolean[] values) {
        if (values.length != 6) {
            throw new IllegalArgumentException("Array should have length 6");
        }
        char[] chars = new char[6];
        for (int i = 0; i < 6; i++) {
            chars[i] = values[i] ? '1' : '0';
        }
        return Byte.valueOf(new String(chars), 2);
    }

    public boolean[] invert(boolean[] values) {
        boolean[] newArray = new boolean[values.length];
        for (int i = 0; i < values.length; ++i) {
            newArray[i] = !values[i];
        }
        return newArray;
    }
}
