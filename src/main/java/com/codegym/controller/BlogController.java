package com.codegym.controller;

import com.codegym.exception.NotFoundException;
import com.codegym.model.Blog;
import com.codegym.model.BlogForm;
import com.codegym.model.Category;
import com.codegym.service.blog.IBlogService;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public ModelAndView showList(@PageableDefault(value = 5) Pageable pageable) {
        Page<Blog> blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @ModelAttribute("categories")
    public Iterable<Category> add(Pageable pageable) {
        return categoryService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blogForm", new BlogForm());
        return modelAndView;

    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute BlogForm blogForm) {
        MultipartFile multipartFile = blogForm.getFile();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(blogForm.getFile().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Blog blog = new Blog(blogForm.getTittle(), blogForm.getContent(), fileName, blogForm.getCategory());
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blogForm", new BlogForm());
        modelAndView.addObject("message", "New Blog was created!");
        return modelAndView;
    }
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView showViewNotFound() {
        return new ModelAndView("error.404");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) throws NotFoundException {
        Optional<Blog> blog = blogService.findById(id);
            ModelAndView modelAndView = new ModelAndView("blog/edit");
            BlogForm blogForm = new BlogForm();
            blogForm.setId(blog.get().getId());
            blogForm.setTittle(blog.get().getTittle());
            blogForm.setContent(blog.get().getContent());
            blogForm.setCategory(blog.get().getCategory());
            modelAndView.addObject("blogForm", blogForm);
            modelAndView.addObject("blog",blog);
            return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute BlogForm blogForm) throws NotFoundException {
        MultipartFile multipartFile = blogForm.getFile();
        Optional<Blog> blog1 = blogService.findById(blogForm.getId());
        String fileName;
        if(multipartFile.isEmpty()){
            fileName = blog1.get().getFile();
        }else {
            fileName = multipartFile.getOriginalFilename();
        }
            try {
                FileCopyUtils.copy(blogForm.getFile().getBytes(), new File(fileUpload + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Blog blog = new Blog(blogForm.getId(),blogForm.getTittle(), blogForm.getContent(), fileName, blogForm.getCategory());
            blogService.save(blog);
            ModelAndView modelAndView = new ModelAndView("blog/edit");
            modelAndView.addObject("blogForm", blogForm);
            modelAndView.addObject("message", "New Blog was updated!");
            return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        blogService.remove(id);
        redirectAttributes.addFlashAttribute("success", "Blog was deleted!");
        return "redirect:/blogs";
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable Long id) throws NotFoundException {
        Optional<Blog> blog = blogService.findById(id);
            ModelAndView modelAndView = new ModelAndView("blog/view");
            modelAndView.addObject("blog", blog.get());
            return modelAndView;
    }
}
