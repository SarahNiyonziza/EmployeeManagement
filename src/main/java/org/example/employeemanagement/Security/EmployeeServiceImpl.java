package org.example.employeemanagement.Security;

import org.example.employeemanagement.Dto.EmployeeRequest;
import org.example.employeemanagement.Dto.EmployeeResponse;
import org.example.employeemanagement.Entity.Employee;
import org.example.employeemanagement.Exception.ResourceNotFoundException;
import org.example.employeemanagement.Repository.EmployeeRepository;
import org.example.employeemanagement.Security.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employee employee = Employee.builder()
                .name(request.getName())
                .position(request.getPosition())
                .department(request.getDepartment())
                .hireDate(request.getHireDate())
                .build();

        Employee saved = employeeRepository.save(employee);
        return convertToResponse(saved);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return convertToResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(String id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        employee.setName(request.getName());
        employee.setPosition(request.getPosition());
        employee.setDepartment(request.getDepartment());
        employee.setHireDate(request.getHireDate());

        Employee updated = employeeRepository.save(employee);
        return convertToResponse(updated);
    }

    @Override
    public void deleteEmployee(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    private EmployeeResponse convertToResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setPosition(employee.getPosition());
        response.setDepartment(employee.getDepartment());
        response.setHireDate(employee.getHireDate());
        return response;
    }
}