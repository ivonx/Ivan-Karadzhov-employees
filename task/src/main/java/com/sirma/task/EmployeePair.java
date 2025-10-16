package com.sirma.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePair {
    private Long firstEmployeeId;
    private Long secondEmployeeId;
}
