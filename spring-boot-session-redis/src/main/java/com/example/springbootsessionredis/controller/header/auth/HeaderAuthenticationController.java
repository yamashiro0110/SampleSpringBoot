package com.example.springbootsessionredis.controller.header.auth;

import lombok.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/header/authentication")
public class HeaderAuthenticationController {

    @ModelAttribute("authorizationKey")
    String authorizationKey(@AuthenticationPrincipal String authorizationKey) {
        return authorizationKey;
    }

    @GetMapping
    String index() {
        return "header_authentication/index";
    }

    @PostMapping("confirm")
    String confirm(
            @ModelAttribute HeaderAuthenticationForm form,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
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
        String value;
    }


}
