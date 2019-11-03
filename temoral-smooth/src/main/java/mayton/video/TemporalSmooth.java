package mayton.video;

import com.google.common.collect.Range;
import mayton.image.Rect;

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

    @Nonnull
    static Range<Integer> detectRange(int allFrames, int fileredRange) {
        return Range.closedOpen(fileredRange + fileredRange / 2, allFrames - fileredRange / 2);
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

        File currentDir = new File("/storage/video/Дон Сезар де Базан");  // args[0];

        Pattern pattern = Pattern.compile("^.+\\.png$", Pattern.CASE_INSENSITIVE); // args[1];

        final int FILTER_WIDTH = 288;  // Integer.valueOf(args[2]);

        String outputExtension = "PNG";

        List<String> list = new ArrayList<>();

        for (String item : currentDir.list()) {
            Matcher matcher = pattern.matcher(item);
            if (matcher.matches()) {
                list.add(item);
            }
        }

        list.sort(String::compareTo);

        list.forEach(item -> {
            System.out.println(item);
        });

        Range<Integer> range = detectRange(list.size(), FILTER_WIDTH);


        boolean isFirst = false;

        Rect rect = null;
        String extension = null;

        for (int i = range.lowerEndpoint(); i < range.upperEndpoint(); i++) {
            //cleanColorPlanes();
            for (int j = -FILTER_WIDTH / 2; j < FILTER_WIDTH / 2; j++) {
                if (rect == null) {
                    // TODO:
                    rect = detectRectangle(new FileInputStream(new File(currentDir, "")), "PNG");
                    //floatingPointRaster = new FloatingPointRaster()
                    // TODO:
                    extension =  "PNG"; //detectExtension("");
                }
                BufferedImage bufferedImage = null;
                //addToColorPlanes(0.1f, bufferedImage);
            }
            writeResultImage("out-" + i + ".png");
        }

    }

    private static void writeResultImage(String s) {
        
    }




}
