package com.codegym.service.category;

import com.codegym.exception.NotFoundException;
import com.codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable);

    Iterable<Category> findAll();

    Optional<Category> findById(Long id) throws NotFoundException;

    void save(Category province);

    void remove(Long id);
}
