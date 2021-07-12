package mayton.billion;

import mayton.lib.html.ColoredHtmlReport;
import org.apache.commons.io.HexDump;
import org.apache.kerby.util.Hex;

import java.io.*;
import java.nio.ByteBuffer;

public class MagicMarkedStreamDumper {

    private InputStream is;
    private Writer dumpOutputWriter;

    public MagicMarkedStreamDumper(InputStream is, Writer dumpOutputWriter) {
        this.is = is;
        this.dumpOutputWriter = dumpOutputWriter;
    }

    public void go() {

    }

    public static void main(String[] args) throws Exception {

        ColoredHtmlReport coloredHtmlReport = new ColoredHtmlReport(new FileOutputStream("out/report.html"));

        //new MagicMarkedStreamDumper("/bigdata/billion/1.2billion.txt.bz2", System.out);

        //HexDump.dump();
        InputStream is = new FileInputStream();
        int i = 0;
        int[] sb = {0x31, 0x41, 0x59, 0x26, 0x53, 0x59};
        int state = 0;
        DataInputStream dis = new DataInputStream(is);
        int res = 0;
        StringBuilder buf = new StringBuilder();
        int colCnt = 0;
        while((res = dis.readByte()) >= 0) {
            switch (state) {
                case 0: // reading raw
                    if (res == sb[i]) {
                        state = 1;
                    }
                case 1: // reading magic
                    if (res == sb[i]) {

                    }
                case 2: // storing

                case 3: // detectng next magic

                default:
                    // nothng
            }
            colCnt++;
        }
        is.close();

        coloredHtmlReport.close();
    }

}
