package com.intuit.players.controller;

import com.intuit.players.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


@RestController
public class FileController {
    private final FileService fileService;
    @Autowired
    public FileController(FileService fileService) {
           this.fileService = fileService;
    }


    @GetMapping("/api/read")
    public void readFile() throws IOException, ExecutionException, InterruptedException {
          fileService.readFile();
    }

}
