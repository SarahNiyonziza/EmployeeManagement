package org.example.employeemanagement.Controller;

import org.example.employeemanagement.Dto.EmployeeRequest;
import org.example.employeemanagement.Dto.EmployeeResponse;
import org.example.employeemanagement.Security.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Employees", description = "Employee management endpoints")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Create a new employee", description = "Create a new employee (Admin only)")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.createEmployee(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all employees", description = "Retrieve list of all employees")
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get employee by ID", description = "Retrieve a specific employee by their ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable String id) {
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @Operation(summary = "Update employee", description = "Update an existing employee's details")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable String id, @Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.updateEmployee(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete employee", description = "Delete an employee (Admin only)")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}