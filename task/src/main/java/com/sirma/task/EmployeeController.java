package com.sirma.task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8091")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/records/{fileName}")
    public ResponseEntity<List<EmployeeRecord>> getEmployeeRecords(@PathVariable String fileName) {
        return ResponseEntity.ok(this.employeeService.getEmployeeRecords(fileName));
    }

    @GetMapping("/record/{fileName}")
    public ResponseEntity<EmployeeRecordResult> getEmployeeRecordResult(@PathVariable String fileName) {
        return ResponseEntity.ok(this.employeeService.getEmployeeRecordResult(fileName));
    }

    @GetMapping("/project-records/{fileName}")
    public ResponseEntity<List<EmployeeRecord>> getEmployeeRecordsForProject(@PathVariable String fileName) {
        return ResponseEntity.ok(this.employeeService.getEmployeeRecordsForProject(fileName));
    }
}
