package me.nizheg.service;

import java.util.Collections;
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

    public SimpleBrailleService(){
        russianAlphabet.put(Byte.valueOf("100000", 2), 'а');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'б');
        russianAlphabet.put(Byte.valueOf("011101", 2), 'в');
        russianAlphabet.put(Byte.valueOf("111100", 2), 'г');
        russianAlphabet.put(Byte.valueOf("110100", 2), 'д');
        russianAlphabet.put(Byte.valueOf("100100", 2), 'е');
        russianAlphabet.put(Byte.valueOf("100001", 2), 'ё');
        russianAlphabet.put(Byte.valueOf("011100", 2), 'ж');
        russianAlphabet.put(Byte.valueOf("100111", 2), 'з');
        russianAlphabet.put(Byte.valueOf("011000", 2), 'и');
        russianAlphabet.put(Byte.valueOf("111011", 2), 'й');
        russianAlphabet.put(Byte.valueOf("100010", 2), 'к');
        russianAlphabet.put(Byte.valueOf("101010", 2), 'л');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'м');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'н');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'о');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'п');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'р');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'с');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'т');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'у');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'ф');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'х');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'ц');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'ч');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'ш');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'щ');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'ъ');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'ы');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'ь');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'э');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'ю');
        russianAlphabet.put(Byte.valueOf("101000", 2), 'я');
    }

    public List<Character> getAllPossibleVariants(boolean[] values) {
        return Collections.singletonList(getRussianLetter(values));
    }

    public Character getRussianLetter(boolean[] values) {
        Byte byteRepresentation = getByte(values);
        return russianAlphabet.get(byteRepresentation);
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
