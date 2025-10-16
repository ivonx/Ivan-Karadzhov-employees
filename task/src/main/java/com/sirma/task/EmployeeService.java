package com.sirma.task;

import java.util.List;

public interface EmployeeService {
    List<EmployeeRecord> getEmployeeRecordsForProject(String fileName);

    List<EmployeeRecord> getEmployeeRecords(String fileName);

    EmployeeRecordResult getEmployeeRecordResult(String fileName);
}
