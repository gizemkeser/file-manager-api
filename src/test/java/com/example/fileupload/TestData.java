package com.example.fileupload;

import com.example.fileupload.model.FileEntry;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static List<FileEntry> mockFileEntryList() {
        FileEntry entry1 = new FileEntry();
        entry1.setId(1L);
        entry1.setCode("code-1");
        entry1.setSource("source-1");
        entry1.setDisplayValue("display-value-1");
        entry1.setCodeListCode("code-list-code-1");
        entry1.setLongDescription("description-1");
        entry1.setSortingPriority(1);
        entry1.setFromDate(LocalDate.of(2023, 1, 1));
        entry1.setToDate(LocalDate.of(2023, 2, 1));

        FileEntry entry2 = new FileEntry();
        entry2.setId(1L);
        entry2.setCode("code-2");
        entry2.setSource("source-2");
        entry2.setDisplayValue("display-value-2");
        entry2.setCodeListCode("code-list-code-2");
        entry2.setLongDescription("description-2");
        entry2.setSortingPriority(2);
        entry2.setFromDate(LocalDate.of(2023, 2, 1));
        entry2.setToDate(LocalDate.of(2023, 3, 1));

        List mockFileEntries = new ArrayList<FileEntry>();
        mockFileEntries.add(entry1);
        mockFileEntries.add(entry2);
        return mockFileEntries;
    }

    public static FileEntry mockFileEntry() {
        FileEntry entry = new FileEntry();
        entry.setId(1L);
        entry.setCode("code-1");
        entry.setSource("source-1");
        entry.setDisplayValue("display-value-1");
        entry.setCodeListCode("code-list-code-1");
        entry.setLongDescription("description-1");
        entry.setSortingPriority(1);
        entry.setFromDate(LocalDate.of(2023, 1, 1));
        entry.setToDate(LocalDate.of(2023, 2, 1));
        return entry;
    }

    public static String mockValidCsvContent() {
        return "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority\n" +
                "src1,list1,001,Value 1,Description 1,2023-01-01,2023-01-31,1\n" +
                "src2,list2,002,Value 2,Description 2,2023-02-01,2023-02-28,2";
    }

    public static MultipartFile mockMultipartFile(String content) {
        return new MockMultipartFile(
                "file", "test.csv", "text/csv", content.getBytes(StandardCharsets.UTF_8));

    }
}
