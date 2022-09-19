package com.solvd.bank.JaxbTask;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JAXBTask {

    public static void main(String[] args) {
        System.out.println(XmlToJaxbObject().toString());

        Book book = new Book(1, "Book1", "Author1", "English");

        jaxbObjectToXML(book);
    }

    private static void jaxbObjectToXML(Book book)
    {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            jaxbMarshaller.marshal(book, new File("src/main/java/com/solvd/bank/JaxbTask/Book1.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static Book XmlToJaxbObject() {
        try {
            JAXBContext context = JAXBContext.newInstance(Book.class);
            return (Book) context.createUnmarshaller().unmarshal(new FileReader("src/main/java/com/solvd/bank/JaxbTask/Book.xml"));
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
