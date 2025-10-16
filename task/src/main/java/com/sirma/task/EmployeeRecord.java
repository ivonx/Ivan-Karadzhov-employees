package com.sirma.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRecord {
    private EmployeePair employeePair;
    private Long projectId;
    private Long daysWorkedTogether;
}
