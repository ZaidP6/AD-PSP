package edu.trianasalesianos.dam.vizitable.files.service;

import edu.trianasalesianos.dam.vizitable.files.model.FileMetadata;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    FileMetadata store(MultipartFile file);

    Resource loadAsResource(String filename);

    void deleteFile(String filename);

    void delete(String fotoPerfil);
}
