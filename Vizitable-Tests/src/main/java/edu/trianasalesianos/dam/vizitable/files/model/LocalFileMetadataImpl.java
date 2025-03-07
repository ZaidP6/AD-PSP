package edu.trianasalesianos.dam.vizitable.files.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class LocalFileMetadataImpl extends AbstractFileMetadata{

    public static FileMetadata of(String filename) {
        return LocalFileMetadataImpl.builder()
                .filename(filename)
                .build();
    }
}
