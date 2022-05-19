package com.example.report.controller;

import com.example.report.entities.concretes.Images;
import com.example.report.service.concretes.ImagesService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ImagesController {

    private ImagesService imagesService;

    public ImagesController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @GetMapping("images/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Optional<Images> imageEntityOptional = imagesService.getFile(id);

        if (!imageEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        Images images = imageEntityOptional.get();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(images.getContentType()))
                .body(images.getImageByte());
    }

}
