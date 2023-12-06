package com.example.fileupload.repository;

import com.example.fileupload.model.FileEntry;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface FileEntryRepository extends ReactiveCrudRepository<FileEntry, Long> {
    @Query("SELECT * FROM file_entry WHERE code = :code")
    Mono<FileEntry> findByCode(String code);
}
