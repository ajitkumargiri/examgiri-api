package com.giri.examgiri.api.service;

import com.giri.examgiri.api.domain.College;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ExcelHelper {
    private static final Logger log = LoggerFactory.getLogger(ExcelHelper.class);
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String CSV_TYPE = "text/csv";


    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static boolean hasCsvFormat(MultipartFile file) {
        log.info(file.getContentType());
        if (CSV_TYPE.equals(file.getContentType()) || "application/vnd.ms-excel".equals(file.getContentType())) {
            return true;
        }

        return false;
    }
    public static List<College> excelToColleges(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            List<College> collegeList = new ArrayList<>();
            for (int index = 0; index < sheet.getPhysicalNumberOfRows(); index++) {
                if (index > 0) {
                    College college = new College();
                    Row row = sheet.getRow(index);
                  //  college.setId((long) row.getCell(0).getNumericCellValue());
                    college.setName(row.getCell(0).getStringCellValue());
                    college.setCode(row.getCell(1).getStringCellValue());
                    collegeList.add(college);
                }
            }
            workbook.close();

            return collegeList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}