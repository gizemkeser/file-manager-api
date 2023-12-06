package com.example.fileupload.service;

import com.example.fileupload.TestData;
import com.example.fileupload.model.FileEntry;
import com.example.fileupload.repository.FileEntryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileUploadServiceImplTest {

    @Mock
    private FileEntryRepository fileEntryRepository;

    @InjectMocks
    private FileEntryServiceImpl fileEntryService;

    @Test
    void upload_ShouldSuccessfullyUploadFileEntries() {
        List<FileEntry> fileEntryList = TestData.mockFileEntryList();
        when(fileEntryRepository.saveAll(fileEntryList)).thenReturn(Flux.fromIterable(fileEntryList));

        fileEntryService.upload(fileEntryList);

        verify(fileEntryRepository, times(1)).saveAll(fileEntryList);
    }

    @Test
    void getAll_ShouldSuccessfullyReturnAllFileEntries() {
        List<FileEntry> fileEntryList = TestData.mockFileEntryList();
        when(fileEntryRepository.findAll()).thenReturn(Flux.fromIterable(fileEntryList));

        Flux<FileEntry> result = fileEntryService.getAll();

        verify(fileEntryRepository, times(1)).findAll();
        assertEquals(fileEntryList.size(), result.collectList().block().size());
    }

    @Test
    void getByCode_ValidCode_ShouldSuccessfullyReturnFileEntry() {
        String code = "001";
        FileEntry fileEntry = TestData.mockFileEntry();
        when(fileEntryRepository.findByCode(code)).thenReturn(Mono.just(fileEntry));

        Mono<FileEntry> result = fileEntryService.getByCode(code);

        verify(fileEntryRepository, times(1)).findByCode(code);
        assertEquals(fileEntry, result.block());
    }

    @Test
    void deleteAll_ShouldSuccessfullyDeleteAllFileEntries() {
        when(fileEntryRepository.deleteAll()).thenReturn(Mono.empty());

        fileEntryService.deleteAll();

        verify(fileEntryRepository, times(1)).deleteAll();
    }

}
