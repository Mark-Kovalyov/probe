package mayton.semantic;

import com.mpatric.mp3agic.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.jena.datatypes.BaseDatatype;
import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MetaVisitor extends SimpleFileVisitor<Path> {

    static Logger logger = LoggerFactory.getLogger(MetaVisitor.class);

    private MessageDigest md4processor = null;

    private Model model;

    private long id;

    public MetaVisitor(Model model) {
        super();
        try {
            md4processor = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            logger.error("",e);
        }
        this.model = model;
        this.model.setNsPrefix("sm", "http://semantic-meta#");
        this.model.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        this.model.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        this.model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
        this.model.setNsPrefix("owl", "http://www.w3.org/2002/07/owl#");
        this.model.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");
        this.model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
        this.model.setNsPrefix("dcterms", "http://purl.org/dc/terms/");
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.getFileName().toString();
        if (fileName.endsWith(".mp3")) {
            long length = file.toFile().length();
            id++;
            Resource idRes = model.createResource("http://semantic-meta#id" + id);
            model.add(
                    idRes,
                    model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"),
                    "/audio/mpeg"
            );
            model.addLiteral(
                    idRes,
                    model.createProperty("http://semantic-meta#length"),
                    length
            );
            logger.info("file = {}", file);

            try {
                Mp3File mp3file = new Mp3File(file);
                logger.debug(" Length of this mp3 is: {} seconds", mp3file.getLengthInSeconds());
                logger.debug(" Bitrate: " + mp3file.getBitrate() + " kbps " + (mp3file.isVbr() ? "(VBR)" : "(CBR)"));
                int sampleRate = mp3file.getSampleRate();
                logger.debug(" Sample rate: " + sampleRate + " Hz");
                model.addLiteral(idRes, model.createProperty("http://semantic-meta#sampleRate"), sampleRate);
                boolean hasId3v1Tag = mp3file.hasId3v1Tag();
                model.addLiteral(idRes, model.createProperty("http://semantic-meta#hasId3v1Tag"), hasId3v1Tag);
                logger.debug(" Has ID3v1 tag?: " + (hasId3v1Tag ? "YES" : "NO"));
                if (hasId3v1Tag) {
                    ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                    String track = id3v1Tag.getTrack();
                    if (track != null) {
                        model.addLiteral(idRes, model.createProperty("http://semantic-meta#Track"), track);
                    }

                    logger.debug("  Artist: " + id3v1Tag.getArtist());
                    model.addLiteral(idRes, model.createProperty("http://semantic-meta#Artist"), id3v1Tag.getArtist());
                    logger.debug("  Title: " + id3v1Tag.getTitle());
                    model.addLiteral(idRes, model.createProperty("http://semantic-meta#Title"), id3v1Tag.getTitle());
                    logger.debug("  Album: " + id3v1Tag.getAlbum());
                    logger.debug("  Year: " + id3v1Tag.getYear());
                    logger.debug("  Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
                    logger.debug("  Comment: " + id3v1Tag.getComment());
                }
                logger.debug("Has ID3v2 tag?: " + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
                logger.debug("Has custom tag?: " + (mp3file.hasCustomTag() ? "YES" : "NO"));
                if (mp3file.hasId3v2Tag()) {
                    ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                    logger.debug("Track: " + id3v2Tag.getTrack());
                    logger.debug("Artist: " + id3v2Tag.getArtist());
                    logger.debug("Title: " + id3v2Tag.getTitle());
                    logger.debug("Album: " + id3v2Tag.getAlbum());
                    logger.debug("Year: " + id3v2Tag.getYear());
                    logger.debug("Genre: " + id3v2Tag.getGenre() + " (" + id3v2Tag.getGenreDescription() + ")");
                    logger.debug("Comment: " + id3v2Tag.getComment());
                    logger.debug("Lyrics: " + id3v2Tag.getLyrics());
                    logger.debug("Composer: " + id3v2Tag.getComposer());
                    logger.debug("Publisher: " + id3v2Tag.getPublisher());
                    logger.debug("Original artist: " + id3v2Tag.getOriginalArtist());
                    logger.debug("Album artist: " + id3v2Tag.getAlbumArtist());
                    logger.debug("Copyright: " + id3v2Tag.getCopyright());
                    logger.debug("URL: " + id3v2Tag.getUrl());
                    logger.debug("Encoder: " + id3v2Tag.getEncoder());
                }
                String md5 = "";
                String sha1 = "";
                if (length < 9728000) {
                    InputStream is = new FileInputStream(file.toFile());
                    int size = 0;
                    byte[] buf = new byte[4096];
                    while((size = is.read()) >= 0) {
                        md4processor.update(buf, 0, size);
                    }
                    is.close();
                    String md4 = Base64.encodeBase64String(md4processor.digest());
                    String ed2k = String.format("ed2k://|file|%s|%d|%s|/",
                            fileName, length, md4);
                    model.addLiteral(
                            idRes,
                            model.createProperty("http://semantic-meta#ed2k"),
                            ed2k
                    );
                }
            } catch (UnsupportedTagException e) {
                logger.error("",e);
            } catch (InvalidDataException e) {
                logger.error("",e);
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return super.visitFileFailed(file, exc);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return super.postVisitDirectory(dir, exc);
    }

    public Model getModel() {
        return model;
    }
}
