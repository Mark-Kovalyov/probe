package mayton.probe.exif;

import java.util.function.Predicate;

public class ExtensionApplicable implements Predicate<String> {
    @Override
    public boolean test(String s) {
        String lower = s.toLowerCase();
        return lower.endsWith("jpg") || lower.endsWith("jpeg");
    }
}
