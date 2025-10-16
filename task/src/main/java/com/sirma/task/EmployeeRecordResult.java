package com.sirma.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRecordResult {
    private EmployeePair employeePair;
    private Long daysWorkedTogether;
}
