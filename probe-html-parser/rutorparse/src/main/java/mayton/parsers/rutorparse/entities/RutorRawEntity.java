package mayton.parsers.rutorparse.entities;

public class RutorRawEntity {

    private String added;
    private String name;
    private String symbolicSize;
    private String seeds;
    private String leechers;
    private String magnetLink;

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbolicSize() {
        return symbolicSize;
    }

    public void setSymbolicSize(String symbolicSize) {
        this.symbolicSize = symbolicSize;
    }

    public String getSeeds() {
        return seeds;
    }

    public void setSeeds(String seeds) {
        this.seeds = seeds;
    }

    public String getLeechers() {
        return leechers;
    }

    public void setLeechers(String leechers) {
        this.leechers = leechers;
    }

    public String getMagnetLink() {
        return magnetLink;
    }

    public void setMagnetLink(String magnetLink) {
        this.magnetLink = magnetLink;
    }
}
