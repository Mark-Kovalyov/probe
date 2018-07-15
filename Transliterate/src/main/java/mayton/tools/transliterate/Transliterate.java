package mayton.tools.transliterate;

import java.io.File;

import static java.lang.System.err;
import static java.lang.System.out;
import static mayton.tools.transliterate.Transliterator.transliterate;

public class Transliterate {

    public static void main(String[] args) throws Exception {
        File currentDir = new File(".");
        File[] list = currentDir.listFiles();
        for(File file : list) {
            if (!file.isDirectory()) {
                String name = file.getName();
                if (name.toLowerCase().endsWith(".mp3")) {
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
