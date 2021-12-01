package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class BlogForm {
    private Long id;
    private String tittle;
    private String content;
    private MultipartFile file;
    private Category category;

    public BlogForm() {
    }

    public BlogForm(Long id, String tittle, String content, MultipartFile file, Category category) {
        this.id = id;
        this.tittle = tittle;
        this.content = content;
        this.file = file;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
