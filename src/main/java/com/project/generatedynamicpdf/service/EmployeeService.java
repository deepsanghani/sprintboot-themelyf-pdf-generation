package com.project.generatedynamicpdf.service;

import com.project.generatedynamicpdf.entity.Employee;
import com.project.generatedynamicpdf.repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public String addEmployee(Employee employee){
        Employee emp = employeeRepo.save(employee);
        log.info("Employee added: {}", emp);
        return "Employee Added Successfully";
    }

    public List<Employee> getAllEmployee(){
        List<Employee> emp = employeeRepo.findAll();
        log.info("Employee fetched successfully: {}", emp);
        return emp;
    }
}
