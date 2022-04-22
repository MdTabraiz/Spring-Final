package com.zemoso.springdemo.controller;

import com.zemoso.springdemo.entity.Blog;
import com.zemoso.springdemo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/blogs/user")
public class BlogUserController {

    @Autowired
    private BlogService blogService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,editor);
    }

    @GetMapping("/list")
    public String blogsList(Model model, Principal principal){
        List<Blog> blogs = blogService.findByAuthor(principal.getName());
        List<Blog> allBlogs = blogService.findAll();
        model.addAttribute("blogs",blogs);
        model.addAttribute("allBlogs",allBlogs);
        return "user-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model,Principal principal){
        Blog theblog = new Blog();
        theblog.setAuthorName(principal.getName());
        model.addAttribute("theblog",theblog);

        return "user-blog-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("theblog") Blog blog, BindingResult result){

        if(result.hasErrors()){
            return "user-blog-form";
        }else{
            blogService.save(blog);
            return "redirect:/blogs/user/list";
        }
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("blogId") int theId, Model model){

        Blog blog = blogService.findById(theId);

        model.addAttribute("theblog",blog);

        return "user-blog-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("blogId") int theId){

        blogService.deleteById(theId);
        return "redirect:/blogs/user/list";
    }

}
