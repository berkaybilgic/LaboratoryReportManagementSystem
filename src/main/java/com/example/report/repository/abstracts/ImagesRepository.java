package com.example.report.repository.abstracts;

import com.example.report.entities.concretes.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Images, String> {
}
