package mayton.files;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class FileStats {

    int depth = 0;
    Map<String, StatsEntity> map = new HashMap<>();
    Map<String, Map<String, StatsEntity>> subFolderMap = new HashMap<>();

    // /mozilla-central/layout : [xml:20k], [xvg:2k], [xhtml:1.5k] ...
    // /mozilla-central/layout : [js:], [html: ], [build]

    @NotNull
    String detectExtension(@NotNull String name) {
        int l = name.lastIndexOf('.');
        String ext = l < 0 ? null : name.substring(l + 1).toLowerCase();
        if (ext == null) {
            ext = "";
        } else if (!pattern.matcher(ext).matches()) {
            System.err.printf("Extension '%s' doesn't look like extension! Skipped.\n", ext);
            ext = "";
        }
        return ext;
    }

    static Pattern pattern = Pattern.compile("[a-z0-9]+");

    void processFile(@NotNull File file, int level) {
        String ext = detectExtension(file.getName());
        if (map.containsKey(ext)) {
            StatsEntity entity = map.get(ext);
            entity.setCount(entity.getCount() + 1);
            entity.setLength(entity.getLength() + file.length());
        } else {
            map.put(ext, new StatsEntity(1, file.length()));
        }
        if (level > depth) {
            //
        }
    }

    void processFolder(File dir, int level) throws IOException {
        if (dir != null) {
            for (File node : dir.listFiles()) {
                if (!node.getName().startsWith(".")) {
                    if (node.isDirectory()) {
                        processFolder(node, level + 1);
                    } else {
                        processFile(node, level + 1);
                    }
                } else {
                    System.err.printf("Entity %s will be ignored with childs!\n", node.getAbsolutePath());
                }
            }
        }
    }

    public void go(String[] args) throws Exception {
        processFolder(new File(args[0]), 0);
        if (args.length > 1) {
            depth = Integer.parseInt(args[1]);
        }
        System.out.printf("Extension;Count;Length\n");
        long allCnt = 0;
        long allLength = 0;
        map.entrySet()
                .stream()
                .sorted((entry1, entry2) -> {
                    return -1 * ((Integer) entry1.getValue().getCount()).compareTo(entry2.getValue().getCount());
                })
                .forEach(entry -> {
                    System.out.printf("%s;%s;%s\n",
                            entry.getKey(),
                            entry.getValue().getCount(),
                            entry.getValue().getLength());
                });
        for (Map.Entry<String, StatsEntity> entityEntry : map.entrySet()) {
            allCnt += entityEntry.getValue().getCount();
            allLength += entityEntry.getValue().getLength();
        }
        System.out.printf(";%s;%s\n", allCnt, allLength);
    }

    public static void main(String[] args) throws Exception {
        FileStats fileStats = new FileStats();
        fileStats.go(args);
    }

}
