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
import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,editor);
    }

    @GetMapping("/list")
    public String listBlogs(Model model){
        List<Blog> blogs = blogService.findAll();
        model.addAttribute("blogs",blogs);

        return "blog-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Blog theblog = new Blog();
        model.addAttribute("theblog",theblog);

        return "blog-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid  @ModelAttribute("theblog") Blog blog, BindingResult result){

        if(result.hasErrors()){
            return "blog-form";
        }else{
            blogService.save(blog);
            return "redirect:/blogs/list";
        }
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("blogId") int theId, Model model){

        Blog blog = blogService.findById(theId);

        model.addAttribute("theblog",blog);

        return "blog-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("blogId") int theId){

        blogService.deleteById(theId);
        return "redirect:/blogs/list";
    }

    @GetMapping("/showMyLoginPage")
    public String showLoginPage(){
        return "login";
    }

}
