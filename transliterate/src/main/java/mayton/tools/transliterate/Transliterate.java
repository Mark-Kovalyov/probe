package mayton.tools.transliterate;

import java.io.File;

import static java.lang.System.err;
import static java.lang.System.getProperty;
import static java.lang.System.out;
import static mayton.tools.transliterate.Transliterator.transliterate;

public class Transliterate {

    public static void main(String[] args) throws Exception {
        File currentDir = new File(".");
        out.printf("Current dir = '%s'\n", currentDir.getAbsolutePath());

        String userDir = getProperty("user.dir");
        String userHome = getProperty("user.home");
        out.printf("userDir = '%s'\n", userDir);
        out.printf("userHome = '%s'\n", userHome);

        if (args.length == 0) {
            err.printf("Aborted. Please provide 1-st argument as file extension suffix. (Ex : 'mp3");
            return;
        }

        String suffix = args[0];

        File[] list = currentDir.listFiles();
        for(File file : list) {
            if (!file.isDirectory()) {
                String name = file.getName();
                if (name.toLowerCase().endsWith(suffix)) {
                    String newName = transliterate(name);
                    out.printf("Trying to rename '%s' to '%s'\n", name, newName);
                    if (file.exists()) {
                        boolean res = file.renameTo(new File(newName));
                        if (!res) {
                            err.printf("Error during rename '%s' to '%s'\n", name, newName);
                        }
                    }
                }
            }
        }
    }

}
