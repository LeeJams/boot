package org.example.springbootdev.file.controller;

import org.example.springbootdev.FileAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @PostMapping("/file")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());

        try {
            FileAdapter.uploadFile(file);
        } catch (Exception e) {
            System.out.println(e);
        }


        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
