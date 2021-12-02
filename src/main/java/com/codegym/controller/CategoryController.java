package com.codegym.controller;

import com.codegym.exception.NotFoundException;
import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.repository.blog.IBlogRepository;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IBlogRepository blogRepository;
    @GetMapping("")
    public ModelAndView list(@PageableDefault(value = 2) Pageable pageable){
      Page<Category> categories = categoryService.findAll(pageable);
      ModelAndView modelAndView = new ModelAndView("category/list");
      modelAndView.addObject("categories",categories);
      return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category",new Category());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category",new Category());
        return modelAndView;
    }
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView showViewNotFound(){
        return new ModelAndView("error.404");
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) throws NotFoundException{
        Optional<Category> category = categoryService.findById(id);
            ModelAndView modelAndView = new ModelAndView("category/edit");
            modelAndView.addObject("category",category.get());
            return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView update(@ModelAttribute Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("category/edit");
        modelAndView.addObject("category",category);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        categoryService.remove(id);
        return "redirect:/categories";
    }
    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") Long id) throws NotFoundException{
        ModelAndView modelAndView = new ModelAndView("category/view");
        Optional<Category> category = categoryService.findById(id);
            Iterable<Blog>blogs = blogRepository.findAllByCategory(category.get());
            modelAndView.addObject("category",category.get());
            modelAndView.addObject("blogs",blogs);
            return modelAndView;
    }
}
