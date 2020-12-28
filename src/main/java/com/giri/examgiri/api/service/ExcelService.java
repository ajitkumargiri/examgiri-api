package com.giri.examgiri.api.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.giri.examgiri.api.domain.College;
import com.giri.examgiri.api.repository.CollegeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@Service
public class ExcelService {

    private final Logger log = LoggerFactory.getLogger(ExcelService.class);
    @Autowired
    CollegeRepository repository;

    public void save(MultipartFile file) {
        try {
            List<College> collegeList = ExcelHelper.excelToColleges(file.getInputStream());
            repository.saveAll(collegeList);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void saveCSV(MultipartFile file) {
        try {
            List<College> collegeList = loadObjectList(College.class, file);
            repository.saveAll(collegeList);
        } catch (Exception e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<College> getAllTutorials() {
        return repository.findAll();
    }

    public <T> List<T> loadObjectList(Class<T> type, MultipartFile file) {
        try {
            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            MappingIterator<T> readValues = mapper.readerFor(type).with(csvSchema).readValues(file.getInputStream());
            //MappingIterator<College> collegeMappingIterator = new CsvMapper().readerFor(type).with(csvSchema).readValues(file.getInputStream());
            return readValues.readAll();
        } catch (Exception e) {
            log.error("Error occurred while loading object list from file " + file.getName(), e);
            return Collections.emptyList();
        }
    }


}