package mayton;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyFileOutputStream extends FileOutputStream {
    public MyFileOutputStream( String fname ) throws FileNotFoundException {
        super(fname);
    }

    @Override
    public void close() throws IOException {
        this.getChannel().position( 0 );
    }
}