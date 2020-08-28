package mayton.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamTools {

    static class CharacterIterator implements Iterator<Character> {

        private static final int NEXT_VALUE_TRUE = 0;
        private static final int NEXT_VALUE_FALSE = 1;
        private static final int NEXT_VALUE_UNKNOWN = 2;

        private static final char UNKNOWN_CHAR = (char)-1;

        private char lastChar;
        private int nextValueStatus;
        private InputStreamReader inputStreamReader;

        public CharacterIterator(InputStreamReader inputStreamReader) {
            this.inputStreamReader = inputStreamReader;
            this.nextValueStatus = NEXT_VALUE_UNKNOWN;
            this.lastChar = UNKNOWN_CHAR;
        }

        private Character safeReadChar() {
            if (lastChar != UNKNOWN_CHAR) {
                char temp = lastChar;
                lastChar = UNKNOWN_CHAR;
                return temp;
            } else {
                try {
                    return (char) inputStreamReader.read();
                } catch (IOException e) {
                    return UNKNOWN_CHAR;
                }
            }
        }

        @Override
        public boolean hasNext() {
            if (nextValueStatus == NEXT_VALUE_FALSE) {
                return false;
            } else if (nextValueStatus == NEXT_VALUE_UNKNOWN) {
                lastChar = safeReadChar();
                if (lastChar != UNKNOWN_CHAR) {
                    nextValueStatus = NEXT_VALUE_UNKNOWN;
                    return true;
                } else {
                    nextValueStatus = NEXT_VALUE_FALSE;
                    return false;
                }
            } else {
                return true;
            }
        }

        @Override
        public Character next() {
            if (nextValueStatus == NEXT_VALUE_FALSE) {
                return UNKNOWN_CHAR; // Impossible case by design
            } else {
                return safeReadChar();
            }
        }
    }

    @NotNull
    public static <T> Stream<T> iteratorToStream(@NotNull Iterator<T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
    }

    @NotNull
    public static Iterator<Character> inputStreamReaderToIterable(@NotNull InputStreamReader inputStreamReader) {
        return new CharacterIterator(inputStreamReader);
    }

}
