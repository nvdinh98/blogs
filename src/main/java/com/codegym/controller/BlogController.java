package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.blog.IBlogService;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("")
    public ModelAndView showList(@PageableDefault(value = 2)Pageable pageable){
        Page<Blog>blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("blog/list");
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }
    @ModelAttribute("categories")
    public Iterable<Category> add(Pageable pageable){
        return categoryService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blog",new Blog());
        return modelAndView;

    }
    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blog",new Blog());
        modelAndView.addObject("message","New Blog was created!");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<Blog> blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blog",blog.get());
        return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blog",blog);
        modelAndView.addObject("message","Blog was updated");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        blogService.remove(id);
        redirectAttributes.addFlashAttribute("success","Blog was deleted!");
        return "redirect:/blogs";
    }
    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable Long id){
        Optional<Blog> blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("blog/view");
        modelAndView.addObject("blog",blog.get());
        return modelAndView;
    }
}
