package com.sirma.task;

import java.util.List;

public interface EmployeeService {
    List<EmployeeRecord> getEmployeeRecordsForProject(String fileName);

    List<EmployeeRecord> getEmployeePairRecords(String fileName);

    EmployeePairResult getEmployeePair(String fileName);
}
