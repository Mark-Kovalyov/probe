package mayton.compression.languagespec.ru;

import mayton.compression.languagespec.SyllableSplitter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RuSyllableSplitter implements SyllableSplitter {

    private static final Set<Character> VOWELS = "аеиоуыэюя".chars().mapToObj(c -> (char) c).collect(Collectors.toSet());

    private static boolean isVowel(char c) {
        return VOWELS.contains(Character.toLowerCase(c));
    }

    private static boolean containsVowel(String arg) {
        for (int i = 0; i < arg.length(); i++) {
            if (isVowel(arg.charAt(i))) return true;
        }
        return false;
    }

    @NotNull
    public List<String> split(@NotNull String arg) {
        char pc = (char) -1;
        // TODO: Get rid of stringbuilder
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arg.length(); i++) {
            char c = arg.charAt(i);
            if (pc != (char) -1 && isVowel(pc) && !isVowel(c)) {
                res.add(sb.toString());
                sb = new StringBuilder();
            }
            sb.append(Character.toLowerCase(c));
            pc = c;
        }
        if (sb.length() > 0) {
            res.add(sb.toString());
        }
        int li = res.size() - 1;
        // TODO: Refactor this bullshit
        if (li > 0 && !containsVowel(res.get(li))) {
            res.set(li - 1, res.get(li - 1) + res.get(li));
            res.remove(li);
        }
        return res;
    }
}
