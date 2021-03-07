package probe.mayton.orc;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Utils {

    private Utils() {

    }

    @NotNull
    public static Properties getProps() throws IOException {
        Properties properties = new Properties();
        if (new File("sensitive.properties").exists()) {
            properties.load(new FileInputStream("sensitive.properties"));
        } else {
            properties.put("omskCsvFile",    "~/omskCsvFile.csv");
            properties.put("maxMindCsvFile", "~/maxMindCsvFile.csv");
            properties.put("primeOrcFile",   "~/prime-orc-file.csv");
        }
        return properties;
    }

    public static int ip(@NotNull String ips) {
        int[] ip = new int[4];
        String[] parts = ips.split("\\.");
        for (int i = 0; i < 4; i++) {
            ip[i] = Integer.parseInt(parts[i]);
        }
        return  ip[3] << 24 |
                ip[2] << 16 |
                ip[1] << 8 |
                ip[0] ;
    }

}
