package com.example.employeeexcel.controller;

import com.example.employeeexcel.entity.Employee;
import com.example.employeeexcel.service.EmployeeService;
import com.example.employeeexcel.service.ReportGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ReportGeneration reportGeneration;
    @GetMapping("/viewAllEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/exporttoexcel")
    public String exportToExcel() {
        List<Employee> employees = employeeService.getAllEmployees();
        try {
            reportGeneration.exportToExcel(employees);
            return "Data exported to Excel successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error exporting data to Excel: " + e.getMessage();
        }
    }


}
