package org.example.employeemanagement.Security;

import org.example.employeemanagement.Dto.EmployeeRequest;
import org.example.employeemanagement.Dto.EmployeeResponse;
import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest request);
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse getEmployeeById(String id);
    EmployeeResponse updateEmployee(String id, EmployeeRequest request);
    void deleteEmployee(String id);
}