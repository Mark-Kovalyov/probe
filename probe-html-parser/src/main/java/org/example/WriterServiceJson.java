package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterServiceJson implements MemberWriterService {

    public Writer writer;

    public volatile boolean unrecovarableIOError = false;

    static Logger logger = LogManager.getLogger(WriterServiceJson.class);

    @Override
    public synchronized void write(MemberInfo memberInfo) {
        if (unrecovarableIOError) {
            logger.warn("unrecovarableIOError");
            return;
        }
        if (writer == null) {
            try {
                writer = new FileWriter("csv/members.csv");
            } catch (IOException ex) {
                unrecovarableIOError = true;
            }
        } else {
            try {
                writer.write(memberInfo.toString());
            } catch (IOException ex) {
                logger.error("", ex);
                unrecovarableIOError = true;
            }
        }
    }
}
