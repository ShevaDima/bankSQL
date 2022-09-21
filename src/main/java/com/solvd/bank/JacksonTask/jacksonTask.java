package com.solvd.bank.JacksonTask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class jacksonTask {

    public static void main(String[] args) {
        readJson();

        writeJson(new Book("ABC", "CDE", "EFG", Arrays.asList("123", "456")));
    }

    public static void readJson() {
        ObjectMapper om = new ObjectMapper();

        try {
            JavaType javaType = om.getTypeFactory().constructType(Book.class);
            Book book = om.readValue(new File("src/main/java/com/solvd/bank/JacksonTask/file.json"), javaType);
            System.out.println(book.toString());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeJson(Book book) {
        ObjectMapper om = new ObjectMapper();

        try {
            om.writeValue(new File("src/main/java/com/solvd/bank/JacksonTask/fileWritten.json"), book);
            System.out.println(book.toString());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
