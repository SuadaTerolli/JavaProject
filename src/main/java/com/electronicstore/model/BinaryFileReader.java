package com.electronicstore.model;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class BinaryFileReader {
    public static <T> T readFromBinaryFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (T) ois.readObject();
        }
    }
}

