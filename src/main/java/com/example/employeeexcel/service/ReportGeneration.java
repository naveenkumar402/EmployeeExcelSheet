package com.example.employeeexcel.service;

import com.example.employeeexcel.entity.Employee;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportGeneration {

    @Value("${excel.export.path}")
    private String filepath;
    public void exportToExcel(List<Employee> employees) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Employee Data");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Email");
            headerRow.createCell(3).setCellValue("Role");
            headerRow.createCell(4).setCellValue("Salary");
            int rowIndex = 1;
            for (Employee employee : employees) {
                Row dataRow = sheet.createRow(rowIndex++);
                dataRow.createCell(0).setCellValue(employee.getId());
                dataRow.createCell(1).setCellValue(employee.getName());
                dataRow.createCell(2).setCellValue(employee.getEmail());
                dataRow.createCell(3).setCellValue(employee.getRole());
                dataRow.createCell(4).setCellValue(employee.getSalary());
            }

            try (FileOutputStream fileOut = new FileOutputStream(filepath)) {
                workbook.write(fileOut);
            }
        }
    }
}
