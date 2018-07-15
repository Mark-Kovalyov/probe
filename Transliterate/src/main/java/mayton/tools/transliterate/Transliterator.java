package mayton.tools.transliterate;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Character.UnicodeBlock.CYRILLIC;
import static java.lang.String.format;

public class Transliterator {

    static String transliterate(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        for(int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);
            int code = (int)c;
            if (code >= 0x20 && code <= 0x7F) {
                sb.append(c);
            } else if (Character.UnicodeBlock.of(code).equals(CYRILLIC)) {
                sb.append(transliterateCyrillic(code));
            } else {
                sb.append(format("$%X",code));
            }
        }
        return sb.toString();
    }

    static Map<Character,String> map = new HashMap<>();

    static char[] abcCyr =   {' ','а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м',
                       'н','о','п','р','с','т','у','ф','х', 'ц','ч', 'ш','щ','ъ','ы',
                       'ь','э', 'ю','я','А','Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й',
                       'К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х', 'Ц', 'Ч','Ш',
                       'Щ','Ъ','Ы','Ь','Э','Ю','Я'};

    static String[] abcLat = {" ","a","b","v","g","d","e","e","zh","z","i","y","k","l","m",
                       "n","o","p","r","s","t","u","f","h","ts","ch","sh","sch", "","i",
                       "","e","ju","ja","A","B","V","G","D","E","E","Zh","Z","I","Y",
                       "K","L","M","N","O","P","R","S","T","U","F","H","Ts","Ch","Sh",
                       "Sch", "","I", "","E","Ju","Ja"};

    static {
        for(int i = 0 ; i < abcCyr.length ; i++) {
            map.put(abcCyr[i],
                    abcLat[i]);
        }
    }

    private static String transliterateCyrillic(int code) {
        if (map.containsKey((char) code)) {
            return map.get((char) code);
        } else {
            return "";
        }
    }


}
