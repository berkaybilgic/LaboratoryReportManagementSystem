package com.example.report.service.abstracts;

import com.example.report.entities.concretes.Images;

import java.util.List;
import java.util.Optional;

public interface ImagesServiceInterface {


    void save(Images images);

    Optional<Images> getFile(String id);

    List<Images> getAllFiles();

}
