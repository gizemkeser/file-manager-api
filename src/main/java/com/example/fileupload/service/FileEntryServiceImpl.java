package com.example.fileupload.service;

import com.example.fileupload.model.FileEntry;
import com.example.fileupload.repository.FileEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class FileEntryServiceImpl implements FileEntryService {

    @Autowired
    private FileEntryRepository fileEntryRepository;

    @Transactional
    public Mono<Void> upload(List<FileEntry> fileEntries) {
        return fileEntryRepository.saveAll(fileEntries).then();
    }

    public Flux<FileEntry> getAll() {
        return fileEntryRepository.findAll();
    }

    public Mono<FileEntry> getByCode(String code) {
        return fileEntryRepository.findByCode(code);
    }

    @Transactional
    public Mono<Void> deleteAll() {
        return fileEntryRepository.deleteAll().then();
    }
}
