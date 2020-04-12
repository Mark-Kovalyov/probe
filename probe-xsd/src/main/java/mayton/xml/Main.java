package mayton.xml;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.zip.GZIPInputStream;

public class Main {

    public static void main(String[] args) throws IOException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream("/bigdata/open-street-map/changesets-latest.osm.gz"));
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(gzipInputStream);
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
