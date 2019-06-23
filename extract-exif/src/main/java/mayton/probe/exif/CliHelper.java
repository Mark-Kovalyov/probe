package mayton.probe.exif;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CliHelper {

    private CliHelper(){}

    public static Options prepareOptions() {
        return new Options()
                .addOption(new Option("h", "help", false, "print this message" ))
                .addOption(new Option("i", "input", true, "input dir"))
                .addOption(new Option("o", "output", true, "output dir"))
                .addOption(new Option("a", "aliaslist", true, "\"date=${'Exif IFD0', model=${''}"))
                .addOption(new Option("f", "format", true, "ex: ${date}/"))
                .addOption(new Option("c", "copystrategy", true, "[ copy | hardlink | symlink | cow ]"));
    }

}
