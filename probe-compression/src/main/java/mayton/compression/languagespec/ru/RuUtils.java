package mayton.compression.languagespec.ru;

import java.util.HashSet;
import java.util.Set;

public class RuUtils {

    static Set<Character> set = new HashSet<>();

    static String DIGITS = "0123456789";

    static String RU_LETTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
                               "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    static {
        for(int i = 0 ; i < RU_LETTERS.length() ; i++) {
            set.add(RU_LETTERS.charAt(i));
        }
    }

    public static boolean isCyrillic(int c) {
        //return Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.CYRILLIC);
        return set.contains((char) c);
    }

    public static boolean isCyrillic(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!isCyrillic(s.charAt(i))) return false;
        }
        return true;
    }

    public static boolean isCyrillicOrHyphensInTheMiddle(String s) {
        int cyrillicCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-') continue;
            if (!isCyrillic(c)) {
                return false;
            } else {
                cyrillicCount++;
            }
        }
        if (cyrillicCount == 0) return false;
        return true;
    }



}
