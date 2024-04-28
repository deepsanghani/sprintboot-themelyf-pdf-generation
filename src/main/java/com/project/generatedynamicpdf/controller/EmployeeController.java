package com.project.generatedynamicpdf.controller;

import com.project.generatedynamicpdf.entity.Employee;
import com.project.generatedynamicpdf.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/addemployee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee e){
        String res = employeeService.addEmployee(e);
        if(res.equals("Employee Added Successfully")){
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-employee")
    public ResponseEntity<?> getAllEmployee(){
        List<Employee> res = employeeService.getAllEmployee();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
