package com.sirma.task;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final CSVReaderService csvReaderService;
    private final DateService dateService;

    public EmployeeServiceImpl(CSVReaderService csvReaderService, DateService dateService) {
        this.csvReaderService = csvReaderService;
        this.dateService = dateService;
    }

    @Override
    public List<EmployeeRecord> getEmployeeRecordsForProject(String fileName) {
        final List<EmployeeBean> employeeBeans = this.csvReaderService.readEmployees(fileName);
        return this.findLongestPairsForProject(employeeBeans);
    }

    @Override
    public List<EmployeeRecord> getEmployeePairRecords(String fileName) {
        final List<EmployeeBean> employeeBeans = this.csvReaderService.readEmployees(fileName);
        final List<EmployeeRecord> employeeRecords = this.findEmployeeRecordsWithOverlap(employeeBeans);
        if (employeeRecords.isEmpty()) {
            return Collections.emptyList();
        }
        EmployeePairResult employeePairResult = this.findLongestPair(employeeRecords);
        return this.findEmployeeRecordsWithOverlap(employeeBeans).stream()
                .filter(employeeRecord -> employeeRecord.getEmployeePair().equals(employeePairResult.getEmployeePair()))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeePairResult getEmployeePair(String fileName) {
        final List<EmployeeBean> employeeBeans = this.csvReaderService.readEmployees(fileName);
        final List<EmployeeRecord> employeeRecords = this.findEmployeeRecordsWithOverlap(employeeBeans);
        if (employeeRecords.isEmpty()) {
            return new EmployeePairResult();
        }
        return this.findLongestPair(employeeRecords);
    }

    /**
     * Returns a list of employee records with employee pairs that have worked together
     * on common project for the longest period of time.
     */
    private List<EmployeeRecord> findLongestPairsForProject(List<EmployeeBean> employees) {
        Map<Long, List<EmployeeBean>> employeesByProjectId = employees.stream()
                .collect(Collectors.groupingBy(EmployeeBean::getProjectId));

        List<EmployeeRecord> employeeRecords = new ArrayList<>();
        for (Map.Entry<Long, List<EmployeeBean>> entry : employeesByProjectId.entrySet()) {
            Long projectId = entry.getKey();
            List<EmployeeBean> employeeRecord = entry.getValue();

            EmployeeRecord employeeRecordWithLongestTimeForProject = null;

            for (int i = 0; i < employeeRecord.size(); i++) {
                for (int j = i + 1; j < employeeRecord.size(); j++) {
                    EmployeeBean firstEmployee = employeeRecord.get(i);
                    EmployeeBean secondEmployee = employeeRecord.get(j);
                    Long overlap = this.dateService.calculateDateOverlap(
                            firstEmployee.getDateFrom(),
                            firstEmployee.getDateTo(),
                            secondEmployee.getDateFrom(),
                            secondEmployee.getDateTo());

                    if (employeeRecordWithLongestTimeForProject == null || overlap > employeeRecordWithLongestTimeForProject.getDaysWorkedTogether()) {
                        Long firstEmployeeId = Math.min(firstEmployee.getEmployeeId(), secondEmployee.getEmployeeId());
                        Long secondEmployeeId = Math.max(firstEmployee.getEmployeeId(), secondEmployee.getEmployeeId());
                        EmployeePair employeePair = new EmployeePair(firstEmployeeId, secondEmployeeId);
                        employeeRecordWithLongestTimeForProject = new EmployeeRecord(employeePair, projectId, overlap);
                    }
                }
            }
            if (employeeRecordWithLongestTimeForProject != null) {
                employeeRecords.add(employeeRecordWithLongestTimeForProject);
            }
        }
        return employeeRecords;
    }

    /**
     * Returns a list of employee records with employee pairs that have worked together.
     */
    private List<EmployeeRecord> findEmployeeRecordsWithOverlap(List<EmployeeBean> employees) {
        Map<Long, List<EmployeeBean>> employeesByProjectId = employees.stream()
                .collect(Collectors.groupingBy(EmployeeBean::getProjectId));

        List<EmployeeRecord> employeeRecords = new ArrayList<>();
        for (Map.Entry<Long, List<EmployeeBean>> entry : employeesByProjectId.entrySet()) {
            Long projectId = entry.getKey();
            List<EmployeeBean> employee = entry.getValue();

            for (int i = 0; i < employee.size(); i++) {
                for (int j = i + 1; j < employee.size(); j++) {
                    EmployeeBean firstEmployee = employee.get(i);
                    EmployeeBean secondEmployee = employee.get(j);
                    Long overlap = this.dateService.calculateDateOverlap(
                            firstEmployee.getDateFrom(),
                            firstEmployee.getDateTo(),
                            secondEmployee.getDateFrom(),
                            secondEmployee.getDateTo());

                    if (overlap > 0) {
                        Long firstEmployeeId = Math.min(firstEmployee.getEmployeeId(), secondEmployee.getEmployeeId());
                        Long secondEmployeeId = Math.max(firstEmployee.getEmployeeId(), secondEmployee.getEmployeeId());
                        EmployeePair employeePair = new EmployeePair(firstEmployeeId, secondEmployeeId);
                        EmployeeRecord employeeRecord = new EmployeeRecord(employeePair, projectId, overlap);
                        employeeRecords.add(employeeRecord);
                    }
                }
            }
        }
        return employeeRecords;
    }

    /**
     * Returns an employee pair that have worked together on common projects for the longest period of time
     */
    private EmployeePairResult findLongestPair(List<EmployeeRecord> employeeRecords) {
        Map<EmployeePair, Long> pairsWithWorkingDaysTogether = new HashMap<>();
        employeeRecords.forEach(employeeRecord -> {
            if (!pairsWithWorkingDaysTogether.containsKey(employeeRecord.getEmployeePair())) {
                pairsWithWorkingDaysTogether.put(employeeRecord.getEmployeePair(), employeeRecord.getDaysWorkedTogether());
            } else {
                pairsWithWorkingDaysTogether.put(employeeRecord.getEmployeePair(), pairsWithWorkingDaysTogether.get(employeeRecord.getEmployeePair()) + employeeRecord.getDaysWorkedTogether());
            }
        });
        Map.Entry<EmployeePair, Long> resultEntry = Collections.max(pairsWithWorkingDaysTogether.entrySet(), Map.Entry.comparingByValue());
        return new EmployeePairResult(resultEntry.getKey(), resultEntry.getValue());
    }
}
