package com.example.fileupload.util;

import com.example.fileupload.TestData;
import com.example.fileupload.model.FileEntry;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvUtilTest {

    @Test
    void readCsvFile_ValidFile_ReturnsExpectedEntries() throws IOException {
        MultipartFile multipartFile = TestData.mockMultipartFile(TestData.mockValidCsvContent());
        List<FileEntry> fileEntries = CsvUtil.readCsvFile(multipartFile);

        // Assertions
        assertEquals(2, fileEntries.size());
        assertEquals("src1", fileEntries.get(0).getSource());
        assertEquals("list1", fileEntries.get(0).getCodeListCode());
        assertEquals("001", fileEntries.get(0).getCode());
        assertEquals("Value 1", fileEntries.get(0).getDisplayValue());
        assertEquals("Description 1", fileEntries.get(0).getLongDescription());
        assertEquals(LocalDate.of(2023, 1, 1), fileEntries.get(0).getFromDate());
        assertEquals(LocalDate.of(2023, 1, 31), fileEntries.get(0).getToDate());
        assertEquals(1, fileEntries.get(0).getSortingPriority());
        assertEquals("src2", fileEntries.get(1).getSource());
        assertEquals("list2", fileEntries.get(1).getCodeListCode());
        assertEquals("002", fileEntries.get(1).getCode());
        assertEquals("Value 2", fileEntries.get(1).getDisplayValue());
        assertEquals("Description 2", fileEntries.get(1).getLongDescription());
        assertEquals(LocalDate.of(2023, 2, 1), fileEntries.get(1).getFromDate());
        assertEquals(LocalDate.of(2023, 2, 28), fileEntries.get(1).getToDate());
        assertEquals(2, fileEntries.get(1).getSortingPriority());
    }

    @Test
    void readCsvFile_EmptyFile_ThrowsException() throws IOException {
        MultipartFile multipartFile = TestData.mockMultipartFile("");
        assertThrowsExactly(IllegalArgumentException.class, () -> CsvUtil.readCsvFile(multipartFile));
    }
}
