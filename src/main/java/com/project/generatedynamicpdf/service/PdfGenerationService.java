package com.project.generatedynamicpdf.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.project.generatedynamicpdf.entity.Employee;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class PdfGenerationService {

    public byte[] generatePdfFromHtmlFile(List<Employee> employees) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            InputStream inputStream = new FileInputStream("src/main/resources/templates/pdf-template.html");
            String htmlContent = populateHtmlTable(employees, inputStream);
            HtmlConverter.convertToPdf(htmlContent, outputStream);
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    private String populateHtmlTable(List<Employee> employees, InputStream inputStream) throws IOException {
        StringBuilder htmlBuilder = new StringBuilder();
        int ch;
        while ((ch = inputStream.read()) != -1) {
            htmlBuilder.append((char) ch);
        }
        String htmlContent = htmlBuilder.toString();
        // Find the placeholder for the table body
        int startIndex = htmlContent.indexOf("<tbody>");
        int endIndex = htmlContent.indexOf("</tbody>");

        if (startIndex != -1 && endIndex != -1) {
            StringBuilder tableBody = new StringBuilder();

            // Populate the HTML content dynamically with employee data
            for (Employee employee : employees) {
                String employeeRow = "<tr>" +
                        "<td>" + employee.getName() + "</td>" +
                        "<td>" + employee.getDesignation() + "</td>" +
                        "<td>" + employee.getSalary() + "</td>" +
                        "</tr>";
                tableBody.append(employeeRow);
            }

            // Replace the placeholder for the table body with the generated rows
            htmlContent = htmlContent.substring(0, startIndex + "<tbody>".length()) +
                    tableBody.toString() +
                    htmlContent.substring(endIndex);
        }

        return htmlContent;
    }

}
