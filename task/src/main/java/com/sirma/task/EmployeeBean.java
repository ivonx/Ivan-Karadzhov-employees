package com.sirma.task;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBean {

    @CsvBindByName(column = "EmpID")
    private Long employeeId;
    @CsvBindByName(column = "ProjectID")
    private Long ProjectId;
    @CsvBindByName(column = "DateFrom")
    private String dateFrom;
    @CsvBindByName(column = "DateTo")
    private String dateTo;

}
