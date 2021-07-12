package mayton.billion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class SingleCSVRowParser implements Iterator<String> {

    private BufferedReader bufferedReader;

    private String buf = "";

    public SingleCSVRowParser(Reader reader) {
        bufferedReader = new BufferedReader(reader);
    }

    // TODO: This is non-successfull Iterator contract.
    @Override
    public boolean hasNext() {
        if (buf != null) {
            try {
                buf = bufferedReader.readLine();
                if (buf == null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                buf = null;
            }
            return buf != null;
        } else {
            return false;
        }
    }

    @Override
    public String next() {
        return buf;
    }
}
