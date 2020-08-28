package mayton.compression.languagespec.ru;

public class RuUtils {

    public static boolean isCyrillic(int c) {
        return Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.CYRILLIC);
    }

    public static boolean isCyrillic(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!isCyrillic(s.charAt(i))) return false;
        }
        return true;
    }

    public static boolean isCyrillicOrSpacerOrPunkt(int c) {
        return isCyrillic(c) || c == ' ';
    }

}
