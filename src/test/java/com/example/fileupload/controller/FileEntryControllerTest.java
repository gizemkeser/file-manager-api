package com.example.fileupload.controller;

import com.example.fileupload.TestData;
import com.example.fileupload.model.FileEntry;
import com.example.fileupload.service.FileEntryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureWebTestClient
class FileEntryControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private FileEntryService fileEntryService;

    private List<FileEntry> mockFileEntries;

    @BeforeEach
    void setUp() {
        mockFileEntries = TestData.mockFileEntryList();
    }

    @Test
    void upload_ValidFile_ShouldSuccessfullyUploadFileEntries() {
        when(fileEntryService.upload(any())).thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/api/file-entries")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", mockFileEntries.toString()))
                .exchange()
                .expectStatus().isOk();

        verify(fileEntryService, times(1)).upload(any());
    }

    @Test
    void getAll_ShouldSuccessfullyReturnFileEntries() {
        when(fileEntryService.getAll()).thenReturn(Flux.fromIterable(mockFileEntries));

        webTestClient.get()
                .uri("/api/file-entries")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(FileEntry.class)
                .hasSize(mockFileEntries.size());
    }

    @Test
    void getByCode_ShouldReturnFileEntryWhenFileEntryFound() {
        FileEntry entry = mockFileEntries.get(0);
        String code = entry.getCode();

        when(fileEntryService.getByCode(code)).thenReturn(Mono.just(entry));

        webTestClient.get()
                .uri("/api/file-entries/{code}", code)
                .exchange()
                .expectStatus().isOk()
                .expectBody(FileEntry.class)
                .isEqualTo(entry);
    }

    @Test
    void getByCode_ShouldReturnNotFoundWhenFileEntryNotFound() {
        String nonExistentCode = "non_existent";

        when(fileEntryService.getByCode(nonExistentCode)).thenReturn(Mono.empty());

        webTestClient.get()
                .uri("/api/file-entries/{code}", nonExistentCode)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().isEmpty();
    }

    @Test
    void deleteAll_ShouldDeleteAll() {
        when(fileEntryService.deleteAll()).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/file-entries")
                .exchange()
                .expectStatus().isOk();
    }
}
