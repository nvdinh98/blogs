package com.codegym.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "blog")
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tittle;
    private String content;
    private String file;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;



    public Blog() {
    }

    public Blog(Long id, String tittle, String content, String file, Category category) {
        this.id = id;
        this.tittle = tittle;
        this.content = content;
        this.file = file;
        this.category = category;
    }

    public Blog(String tittle, String content, String file, Category category) {
        this.tittle = tittle;
        this.content = content;
        this.file = file;
        this.category = category;
    }

    public Blog(Long id, String tittle, String content, Category category) {
        this.id = id;
        this.tittle = tittle;
        this.content = content;
        this.category = category;
    }

    public Blog(String tittle, String content, String file) {
        this.tittle = tittle;
        this.content = content;
        this.file = file;
    }

    public Blog(Long id, String tittle, String content, String file) {
        this.id = id;
        this.tittle = tittle;
        this.content = content;
        this.file = file;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", content='" + content + '\'' +
                ", image='" + file + '\'' +
                '}';
    }
}
