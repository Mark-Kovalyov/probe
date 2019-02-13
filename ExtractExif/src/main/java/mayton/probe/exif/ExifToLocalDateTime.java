package mayton.probe.exif;

import java.time.LocalDateTime;
import java.util.function.Function;

public class ExifToLocalDateTime implements Function<String, LocalDateTime> {
    @Override
    public LocalDateTime apply(String s) {
        return LocalDateTime.now();
    }
}
