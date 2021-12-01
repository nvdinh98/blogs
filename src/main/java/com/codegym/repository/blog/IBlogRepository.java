package com.codegym.repository.blog;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IBlogRepository extends PagingAndSortingRepository<Blog,Long> {
//     Page<Blog> findAllByCategoryContaining(Pageable pageable);
Iterable<Blog> findAllByCategory(Category category);
}
