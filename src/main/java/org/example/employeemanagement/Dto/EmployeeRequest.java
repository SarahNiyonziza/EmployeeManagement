package org.example.employeemanagement.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EmployeeRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Position is required")
    private String position;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Hire date is required")
    private LocalDate hireDate;
}