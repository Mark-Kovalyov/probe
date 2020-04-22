package mayton.network.dht;

public class Ed2kLink extends GenericDhtLink {

    private String link;

    public Ed2kLink(String link) {
        this.link = link;
    }

    // ed2k://|file|The_Two_Towers-The_Purist_Edit-Trailer.avi|14997504|965c013e991ee246d63d45ea71954c4d|/
    // ed2k://|file|The_Two_Towers-The_Purist_Edit-Trailer.avi|14997504|965c013e991ee246d63d45ea71954c4d|/|sources,202.89.123.6:4662|/

}
