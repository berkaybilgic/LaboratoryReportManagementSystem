package com.example.report.entities.concretes;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Images {

    @Id
    private String id;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "image_byte")
    @Lob
    private byte[] imageByte;

    public Images() {
    }

    public Images(String id, String contentType, byte[] imageByte) {
        this.id = id;
        this.contentType = contentType;
        this.imageByte = imageByte;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }
}
