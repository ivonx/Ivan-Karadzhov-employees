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

    @GetMapping("/{fileName}")
    public ResponseEntity<List<EmployeeRecord>> getEmployeeRecords(@PathVariable String fileName) {
        return ResponseEntity.ok(this.employeeService.getEmployeeRecordsForProject(fileName));
    }

    @GetMapping("/pair-records/{fileName}")
    public ResponseEntity<List<EmployeeRecord>> getEmployeePairRecords(@PathVariable String fileName) {
        return ResponseEntity.ok(this.employeeService.getEmployeePairRecords(fileName));
    }

    @GetMapping("/pair/{fileName}")
    public ResponseEntity<EmployeePairResult> getEmployeePair(@PathVariable String fileName) {
        return ResponseEntity.ok(this.employeeService.getEmployeePair(fileName));
    }
}
