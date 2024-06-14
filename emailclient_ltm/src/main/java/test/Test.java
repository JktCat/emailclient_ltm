/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author ThaiNTD
 */

import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class Test {
    public static void main(String[] args) {
        String filePath = "bangdiem.xlsx"; // Đường dẫn đến file Excel

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = (Sheet) workbook.getSheetAt(0); // Lấy sheet đầu tiên

            int rowCount = sheet.getLastRowNum(); // Số dòng trong sheet

            for (int i = 1; i <= rowCount; i++) { // Bắt đầu từ dòng thứ 2 (bỏ qua header)
                Row row = sheet.getRow(i);

                // Đọc giá trị từ từng cột
                Cell emailCell = row.getCell(0);
                Cell nameCell = row.getCell(1);
                Cell scoreCell = row.getCell(2);

                String email = emailCell.getStringCellValue();
                String name = nameCell.getStringCellValue();
                double score = scoreCell.getNumericCellValue();

                // Xử lý dữ liệu tùy ý
                System.out.println("Email: " + email);
                System.out.println("Họ và tên: " + name);
                System.out.println("Điểm: " + score);
                System.out.println("----------------------");
            }

            workbook.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
