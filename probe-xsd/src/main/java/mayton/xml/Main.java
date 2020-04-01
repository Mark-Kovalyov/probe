package mayton.xml;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;

public class Main {

    public static BufferedReader getBufferedReaderForCompressedFile(String path) throws FileNotFoundException, CompressorException {
        FileInputStream fin = new FileInputStream(path);
        BufferedInputStream bis = new BufferedInputStream(fin);
        CompressorInputStream compressorInputStream = new CompressorStreamFactory().createCompressorInputStream(bis);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(compressorInputStream));
        return br2;
    }

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, CompressorException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        CompressorInputStream compressorInputStream = new CompressorStreamFactory().createCompressorInputStream(new FileInputStream("/bigdata/open-street-map/changesets-latest.osm.bz2"));

        XMLEventReader reader = xmlInputFactory.createXMLEventReader(compressorInputStream);
        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                System.out.print(">");
                System.out.println(startElement.getName());
            }
            if (nextEvent.isEndElement()) {
                EndElement endElement = nextEvent.asEndElement();

                System.out.print("<");
                System.out.println(endElement.getName());
            }
        }


    }

}
