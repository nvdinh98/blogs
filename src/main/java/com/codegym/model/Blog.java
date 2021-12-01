package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tittle;
    private String content;
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;



    public Blog() {
    }

    public Blog(String tittle, String content, String image, Category category) {
        this.tittle = tittle;
        this.content = content;
        this.image = image;
        this.category = category;
    }

    public Blog(String tittle, String content, String image) {
        this.tittle = tittle;
        this.content = content;
        this.image = image;
    }

    public Blog(Long id, String tittle, String content, String image) {
        this.id = id;
        this.tittle = tittle;
        this.content = content;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
                ", image='" + image + '\'' +
                '}';
    }
}
