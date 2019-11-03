package mayton.video;

import com.google.common.collect.Range;
import mayton.image.Rect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TemporalSmooth {

    static FloatingPointRaster floatingPointRaster;

    static Logger logger = LoggerFactory.getLogger(TemporalSmooth.class);

    @Nonnull
    static Range<Integer> detectRange(int allFrames, int fileredRange) {
        return Range.closedOpen(0, allFrames - fileredRange);
    }

    @Nonnull
    static Rect detectRectangle(@Nonnull InputStream inputStream, String format) {
        return new Rect(0, 0, 712, 564);
    }

    @Nonnull
    static String detectExtension(@Nonnull String filename) {
        return filename.substring(filename.lastIndexOf('.'));
    }

    public static void main(String[] args) throws FileNotFoundException {

        logger.info(":: start");

        String inputPath = args[0];
        String pattern = args[1];
        logger.trace(":: detected pattern = '{}'", pattern);
        int windowSize = Integer.valueOf(args[2]);
        logger.trace(":: detected window size = {}", windowSize);
        String outputPath = args[3];
        String outputExtension = "PNG";

        File inputPathObject = new File(inputPath);
        File outputPathObject = new File(outputPath);
        Pattern patternObject = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);

        List<String> list = new ArrayList<>();

        logger.trace(":: detected raw list size = {}", inputPathObject.list().length);

        for (String item : inputPathObject.list()) {
            Matcher matcher = patternObject.matcher(item);
            if (matcher.matches()) {
                list.add(item);
            }
        }

        list.sort(String::compareTo);

        int listSize = list.size();

        logger.trace(":: detected filtered list size = {}", listSize);

        if (listSize == 0) {
            logger.warn(":: See no files by mask '{}'. Abandoned. Nothing to do!", pattern);
            return;
        }

        Range<Integer> range = detectRange(listSize, windowSize);

        boolean isFirst = false;

        Rect rect = null;
        String extension = null;

        for (int i = range.lowerEndpoint(); i < range.upperEndpoint(); i++) {
            //cleanColorPlanes();
            for (int j = 0; j < windowSize; j++) {
                if (rect == null) {
                    // TODO:
                    rect = detectRectangle(new FileInputStream(new File(inputPathObject, list.get(i + j))), "PNG");
                    logger.trace(":: detected rectangle = {}", rect.toString());
                    //floatingPointRaster = new FloatingPointRaster()
                    // TODO:
                    extension = "PNG"; //detectExtension("");
                }
                BufferedImage bufferedImage = null;
                //addToColorPlanes(0.1f, bufferedImage);
            }
            writeResultImage("out-" + i + ".png");
        }

        logger.info(":: finish");

    }

    private static void writeResultImage(String s) {

    }


}
