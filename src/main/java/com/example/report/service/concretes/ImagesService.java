package com.example.report.service.concretes;

import com.example.report.entities.concretes.Images;
import com.example.report.repository.abstracts.ImagesRepository;
import com.example.report.service.abstracts.ImagesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagesService implements ImagesServiceInterface {

    private ImagesRepository imagesRepository;

    @Autowired
    public ImagesService(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    public void save(Images images) {
        imagesRepository.save(images);
    }

    public Optional<Images> getFile(String id) {
        return imagesRepository.findById(id);
    }

    public List<Images> getAllFiles() {
        return imagesRepository.findAll();
    }


}


