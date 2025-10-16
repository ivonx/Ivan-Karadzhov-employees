package com.sirma.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeRecordServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        CSVReaderService csvReaderService = new CSVReaderServiceImpl();
        DateService dateService = new DateService();
        this.employeeService = new EmployeeServiceImpl(csvReaderService, dateService);
    }

    @Test
    void testFindLongestEmployeeRecord() {
        List<EmployeeRecord> employeeRecords = this.employeeService.getEmployeeRecordsForProject("employees.csv");
        assertEquals(0L, employeeRecords.getFirst().getDaysWorkedTogether());
        assertEquals(32L, employeeRecords.getLast().getDaysWorkedTogether());
    }

    @Test
    void testEmployeeRecordWithMoreData() {
        List<EmployeeRecord> employeeRecords = this.employeeService.getEmployeeRecordsForProject("bigFile.csv");
        assertEquals(4, employeeRecords.size());
        assertEquals(15, employeeRecords.getFirst().getDaysWorkedTogether());
        assertEquals(15, employeeRecords.getLast().getProjectId());
        assertEquals(6, employeeRecords.getLast().getEmployeePair().getFirstEmployeeId());
        assertEquals(13, employeeRecords.getLast().getEmployeePair().getSecondEmployeeId());
        assertEquals(543, employeeRecords.getLast().getDaysWorkedTogether());
    }

    @Test
    void testEmployeePairResultWithMoreData() {
        EmployeePairResult employeePairResult = this.employeeService.getEmployeePair("bigFile.csv");
        assertEquals(6, employeePairResult.getEmployeePair().getFirstEmployeeId());
        assertEquals(13, employeePairResult.getEmployeePair().getSecondEmployeeId());
        assertEquals(573, employeePairResult.getDaysWorkedTogether());
    }
}
