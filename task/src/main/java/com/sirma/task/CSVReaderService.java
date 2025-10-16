package com.sirma.task;

import java.util.List;
import java.util.Set;

public interface CSVReaderService {
    Set<String> getCSVFiles();

    List<EmployeeBean> readEmployees(String fileName);
}
