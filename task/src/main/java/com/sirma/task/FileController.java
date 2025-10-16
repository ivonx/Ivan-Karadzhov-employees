package com.sirma.task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin("http://localhost:8091")
@RestController
@RequestMapping("/api/files")
public class FileController {

    private final CSVReaderService csvReaderService;

    public FileController(CSVReaderService csvReaderService) {
        this.csvReaderService = csvReaderService;
    }

    @GetMapping
    public ResponseEntity<Set<String>> getCSVFiles() {
        return ResponseEntity.ok(this.csvReaderService.getCSVFiles());
    }
}
