package mayton.probe.docindexer;

import com.itextpdf.text.pdf.PdfReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PdfAttributesExtractor implements Function<InputStream, Map<String, Object>> {

    static Logger logger = LogManager.getLogger(PdfAttributesExtractor.class);

    @Override
    public Map<String, Object> apply(InputStream inputStream) {
        Map<String,Object> map = new HashMap<>();
        try {
            PdfReader reader = new PdfReader(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
