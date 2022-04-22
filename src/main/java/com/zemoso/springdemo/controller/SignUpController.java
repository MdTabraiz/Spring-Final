package com.zemoso.springdemo.controller;

import com.zemoso.springdemo.constants.Constant;
import com.zemoso.springdemo.entity.User;
import com.zemoso.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/blogs/signup")
public class SignUpController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,editor);
    }


    @GetMapping("/showFormForSignup")
    public String showSignupForm(Model model){

        User user = new User();
        model.addAttribute("theuser",user);

        return Constant.SIGNUP_FORM;
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("theuser")User user,
                               BindingResult result,Model model){


        if(result.hasErrors()){
            return Constant.SIGNUP_FORM;
        }else if(userService.findUser(user.getUsername())){
            model.addAttribute("userExists","Username Already Exists! " +
                    "Enter different one");

            return Constant.SIGNUP_FORM;
        }
        else{
            userService.save(user);
            return "redirect:/blogs/showMyLoginPage";
        }
    }

}
