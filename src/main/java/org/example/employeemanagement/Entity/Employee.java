//package org.example.employeemanagement.Entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
//import lombok.*;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "employees")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Employee {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank
//    private String name;
//
//    @NotBlank
//    private String position;
//
//    @NotBlank
//    private String department;
//
//    private LocalDate hireDate;
//}
