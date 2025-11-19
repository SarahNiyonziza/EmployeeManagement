package org.example.employeemanagement.Dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EmployeeResponse {
    private String id;
    private String name;
    private String position;
    private String department;
    private LocalDate hireDate;
}