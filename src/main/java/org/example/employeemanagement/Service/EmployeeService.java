package org.example.employeemanagement.Service;

import org.example.employeemanagement.Dto.EmployeeRequest;
import org.example.employeemanagement.Dto.EmployeeResponse;
import org.example.employeemanagement.Entity.Employee;
import org.example.employeemanagement.Repository.EmployeeRepository;
import org.example.employeemanagement.Exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {

        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setPosition(employeeRequest.getPosition());
        employee.setDepartment(employeeRequest.getDepartment());
        employee.setHireDate(employeeRequest.getHireDate());


        Employee savedEmployee = employeeRepository.save(employee);


        return convertToResponse(savedEmployee);
    }


    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    public EmployeeResponse getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        return convertToResponse(employee);
    }


    public EmployeeResponse updateEmployee(String id, EmployeeRequest updatedEmployee) {
        Employee existingEmployee = getEmployeeByIdEntity(id); // Use a separate method to get the entity

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setPosition(updatedEmployee.getPosition());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setHireDate(updatedEmployee.getHireDate());


        Employee savedEmployee = employeeRepository.save(existingEmployee);
        return convertToResponse(savedEmployee);
    }

    public boolean deleteEmployee(String id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }


    private Employee getEmployeeByIdEntity(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
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