package com.solvd.bank.StaxTask;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class XmlStaxParser {

    public static void main(String[] args) {
        String fileName = "src/main/java/com/solvd/bank/StaxTask/Books.xml";
        List<Book> booksList = parseXMLfile(fileName);
        System.out.println(booksList);
    }

    private static List<Book> parseXMLfile(String fileName) {
        List<Book> booksList = new ArrayList<>();
        Book book = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));

            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();

                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();

                    if (startElement.getName().getLocalPart().equals("Book")) {
                        book = new Book();
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));

                        if (idAttr != null) {
                            book.setId(Integer.parseInt(idAttr.getValue()));
                        }
                        } else if (startElement.getName().getLocalPart().equals("title")) {
                            xmlEvent = reader.nextEvent();
                            book.setTitle(xmlEvent.asCharacters().getData());
                        } else if (startElement.getName().getLocalPart().equals("author")) {
                            xmlEvent = reader.nextEvent();
                            book.setAuthor(xmlEvent.asCharacters().getData());
                        } else if (startElement.getName().getLocalPart().equals("language")) {
                            xmlEvent = reader.nextEvent();
                            book.setLanguage(xmlEvent.asCharacters().getData());
                        }
                    }

                    if (xmlEvent.isEndElement()) {
                        EndElement endElement = xmlEvent.asEndElement();
                        if (endElement.getName().getLocalPart().equals("Book")) {
                            booksList.add(book);
                        }
                    }
                }

            } catch (FileNotFoundException | XMLStreamException exc) {
                exc.printStackTrace();
            }
        return booksList;
    }
}
