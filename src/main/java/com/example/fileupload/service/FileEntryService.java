package com.example.fileupload.service;

import com.example.fileupload.model.FileEntry;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FileEntryService {
    Mono<Void> upload(List<FileEntry> fileEntries);
    Flux<FileEntry> getAll();
    Mono<FileEntry> getByCode(String code);
    Mono<Void> deleteAll();
}
