package mayton.probe.exif;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.System.getProperty;

public class App {

    static Logger logger = LoggerFactory.getLogger("App");

    private String userHome = getProperty("user.home");

    private String source = "foto";

    private String dest = "";

    private static Predicate<String> extensionTest = new ExtensionApplicable();

    public static void processFolder(File dir)  {
        logger.info("Process folder {}", dir.getAbsolutePath());
        File[] list = dir.listFiles();
        for(File node : list) {
            if (!node.isDirectory()) {
                if (extensionTest.test(node.getPath())){
                    process(node.getPath());
                }
            } else {
                processFolder(node);
            }
        }
    }

    public static void process(String path) {
        try {
            File jpegFile = new File(path);
            Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {

                    String dir     = tag.getDirectoryName();
                    String tagName = tag.getTagName();
                    String desc    = tag.getDescription();
                    int type       = tag.getTagType();
                    String height = "";
                    String width = "";
                    String date = "";

                    if (dir.equals("Exif IFD0") && tagName.equals("Date/Time")) {
                        logger.info(":: {}", desc);
                        Function<String, LocalDateTime> func = new ExifToLocalDateTime();
                        date = func.apply(desc) + ".jpg";
                    }
                    if (dir.equals("JPEG")) {
                        if (tagName.equals("Image Height")) {
                            height = desc;
                        }
                        if (tagName.equals("Image Width")) {
                            width = desc;
                        }
                    }
                    logger.trace("dir = '{}', tagName = '{}', type = {}, desc = '{}'",dir,tagName,type,desc);
                    logger.info("/{}/{}x{}.jpg", date, width, height);


                }
            }
        }
        catch (Exception ex) {
            logger.error("file = '{}'", path, ex);
        }
    }

    private static void copy(String path, String newName) {

    }

    public static void main(String[] args) throws Exception {
        processFolder(new File("/home/mayton/foto/OLD"));
        //HelpFormatter helpFormatter = new HelpFormatter();
        //helpFormatter.printHelp("Help:", CliHelper.prepareOptions());
    }
}
