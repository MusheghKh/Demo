package org.example.demo.controller;

import org.example.demo.util.Constants;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ControllerUtils {


    static void storeFile(MultipartFile file, String filename) {
        try {
            File newFile = new File(Constants.UPLOAD_DIR + filename);

            file.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong");
        }
    }

    static void deleteFile(String filename) {
        File file = new File(Constants.UPLOAD_DIR + filename);
        file.delete();
    }
}
