package com.electronicstore.model;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BinaryFileWriter {
    public static <T> void writeToBinaryFile(String filePath, T object) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(object);
        }
    }
}