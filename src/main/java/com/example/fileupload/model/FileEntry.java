package com.example.fileupload.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("file_entries")
public class FileEntry {

    @Id
    private Long id;

    @CsvBindByName(column = "source")
    private String source;

    @CsvBindByName(column = "codeListCode")
    private String codeListCode;

    @CsvBindByName(column = "code")
    private String code;

    @CsvBindByName(column = "displayValue")
    private String displayValue;

    @CsvBindByName(column = "longDescription")
    private String longDescription;

    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByName(column = "fromDate")
    private LocalDate fromDate;

    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByName(column = "toDate")
    private LocalDate toDate;

    @CsvBindByName(column = "sortingPriority")
    private int sortingPriority;
}
