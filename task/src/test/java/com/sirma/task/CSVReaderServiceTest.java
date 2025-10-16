package com.sirma.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CSVReaderServiceTest {

    private CSVReaderService csvReaderService;

    @BeforeEach
    void setUp() {
        this.csvReaderService = new CSVReaderServiceImpl();
    }

    @Test
    void testNumberOfCSVFiles() {
        Set<String> csvFiles = this.csvReaderService.getCSVFiles();
        assertEquals(3, csvFiles.size());
    }
}
