package com.example.springbootsessionredis.controller.header.auth;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/header/authentication")
public class HeaderAuthenticationController {

    @ModelAttribute("authorizationKey")
    String authorizationKey(@AuthenticationPrincipal User authenticatedUser) {
        return authenticatedUser.getUsername();
    }

    @GetMapping
    String index() {
        return "header_authentication/index";
    }

    @PostMapping("confirm")
    String confirm(
            @ModelAttribute @Valid HeaderAuthenticationForm form,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("has_error", bindingResult.hasErrors());
            model.addAttribute("error_msg", bindingResult.getFieldError("value").getDefaultMessage());
            return "header_authentication/index";
        }

        model.addAttribute("inputForm", form);
        return "header_authentication/confirm";
    }

    @PostMapping("done")
    String done() {
        return "header_authentication/done";
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class HeaderAuthenticationForm {
        @NotBlank
        String value;
    }


}
