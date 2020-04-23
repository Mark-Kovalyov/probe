package mayton.network.dht;

public class Ed2kLink extends GenericDhtLink {

    private String link;

    public Ed2kLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return link;
    }



}
