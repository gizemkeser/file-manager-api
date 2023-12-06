package com.example.fileupload.controller;

import com.example.fileupload.exception.FileEntryNotFoundException;
import com.example.fileupload.model.FileEntry;
import com.example.fileupload.service.FileEntryService;
import com.example.fileupload.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/file-entries")
public class FileEntryController {

    @Autowired
    private FileEntryService fileEntryService;

    @PostMapping
    public Mono<Void> upload(@RequestPart("file") MultipartFile file) {
        List<FileEntry> fileEntries = CsvUtil.readCsvFile(file);
        return fileEntryService.upload(fileEntries);
    }

    @GetMapping
    public Flux<FileEntry> getAll() {
        return fileEntryService.getAll();
    }

    @GetMapping("/{code}")
    public Mono<FileEntry> getByCode(@PathVariable String code) {
        return fileEntryService.getByCode(code)
                .switchIfEmpty(Mono.error(new FileEntryNotFoundException()));
    }

    @DeleteMapping
    public Mono<Void> deleteAll() {
        return fileEntryService.deleteAll();
    }
}
