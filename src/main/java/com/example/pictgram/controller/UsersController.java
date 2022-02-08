package com.example.pictgram.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.pictgram.entity.User;
import com.example.pictgram.entity.User.Authority;
import com.example.pictgram.form.UserForm;
import com.example.pictgram.repository.UserRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsersController {
	
	@Autowired
	private MessageSource messageSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @GetMapping(path = "/users/new")
    public String newUser(Model model) {
        model.addAttribute("form", new UserForm());
        return "users/new";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute("form") UserForm form, BindingResult result, Model model, RedirectAttributes redirAttrs, Locale locale) {
        String name = form.getName();
        String email = form.getEmail();
        String password = form.getPassword();
        String passwordConfirmation = form.getPasswordConfirmation();

        if (repository.findByUsername(email) != null) {
        	FieldError fieldError = new FieldError(result.getObjectName(), "email", messageSource.getMessage("users.create.error.1", new String[] {}, locale));
            result.addError(fieldError);
        }
        if (result.hasErrors()) {
            model.addAttribute("hasMessage", true);
            model.addAttribute("class", "alert-danger");
            model.addAttribute("message", messageSource.getMessage("users.create.flash.1", new String[] {}, locale));
            return "users/new";
        }

        User entity = new User(email, name, passwordEncoder.encode(password), Authority.ROLE_USER);
        repository.saveAndFlush(entity);

        model.addAttribute("hassMessage", true);
        model.addAttribute("class", "alert-info");
        model.addAttribute("message", "ユーザー登録が完了しました。");
        return "layouts/complete";
    }
}
