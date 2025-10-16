package com.sirma.task;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Slf4j
@Service
public class CSVReaderServiceImpl implements CSVReaderService {

    private static final String FOLDER_PATH = "/files";

    @Override
    public Set<String> getCSVFiles() {
        URL folderUrl = CSVReaderServiceImpl.class.getResource(FOLDER_PATH);
        if (folderUrl == null) {
            throw new IllegalArgumentException("Folder not found in resources");
        }
        File folder;
        try {
            folder = new File(folderUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Invalid folder URL", e);
        }

        // Check if folder exists and is a directory
        if (!folder.exists() || !folder.isDirectory()) {
            log.info("Folder does not exist or is not a directory.");
            return Collections.emptySet();
        }

        File[] files = folder.listFiles();

        // Check if folder is empty
        if (files == null || files.length == 0) {
            log.info("Folder is empty.");
            return Collections.emptySet();
        }

        // List only CSV files
        return Arrays.stream(files)
                .filter(File::isFile)
                .map(File::getName)
                .filter(name -> name.toLowerCase().endsWith(".csv"))
                .collect(Collectors.toSet());
    }

    @Override
    public List<EmployeeBean> readEmployees(String fileName) {
        final String filePath = FOLDER_PATH + "/" + fileName;
        try (InputStream inputStream = getClass().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found in resources: " + filePath);
            }

            CsvToBean<EmployeeBean> csvToBean = new CsvToBeanBuilder<EmployeeBean>(new InputStreamReader(inputStream))
                    .withType(EmployeeBean.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(Delimiter.COMMA.getDelimiter())
                    .build();

            List<EmployeeBean> employeeBeans = csvToBean.parse().stream()
                    .filter(employee -> employee.getEmployeeId() != null && employee.getProjectId() != null)
                    .collect(Collectors.toList());

            employeeBeans.forEach(e -> {
                e.setDateFrom(e.getDateFrom().trim());
                e.setDateTo("NULL".equals(e.getDateTo().trim()) ? null : e.getDateTo().trim());
            });

            return employeeBeans;

        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV file: " + filePath, e);
        }
    }
}
