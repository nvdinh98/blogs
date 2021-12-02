package com.codegym.service.blog;

import com.codegym.exception.NotFoundException;
import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBlogService {
    Iterable<Blog> findAllByCategory(Category category);
    Page<Blog> findAll(Pageable pageable);
    Iterable<Blog> findAll();
    Optional<Blog> findById(Long id) throws NotFoundException;
    void save(Blog blog);
    void remove(Long id);
}
