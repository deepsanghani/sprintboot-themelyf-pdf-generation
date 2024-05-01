package com.project.generatedynamicpdf.controller;

import com.project.generatedynamicpdf.entity.Employee;
import com.project.generatedynamicpdf.service.PdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class PdfController {

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @GetMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf() {
        List<Employee> employees = getAllEmployees();
        byte[] pdfBytes = pdfGenerationService.generatePdfFromHtmlFile(employees);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "generated.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    private List<Employee> getAllEmployees() {
        // Make an HTTP GET request to the /get-all-employee endpoint to fetch all employees
        String url = "http://localhost:8080/get-all-employee"; // Change the URL to your actual endpoint
        ResponseEntity<Employee[]> responseEntity = new RestTemplate().getForEntity(url, Employee[].class);
        Employee[] employeesArray = responseEntity.getBody();
        if (employeesArray != null) {
            return Arrays.asList(employeesArray);
        } else {
            return null;
        }
    }
}
