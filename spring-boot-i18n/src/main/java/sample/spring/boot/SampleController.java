package sample.spring.boot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yamashiro-r on 2017/02/04.
 */
@Controller
@RequestMapping("/sample")
@SessionAttributes("sampleModel")
public class SampleController {

    @ModelAttribute("sampleModel")
    private SampleModel sampleModel() {
        return new SampleModel();
    }

    @GetMapping
    String index(final Model model) {
        model.addAttribute("msg", "apple pen");
        return "/index";
    }

    @GetMapping("valid")
    String valid(final Model model) {
        return "/valid/index";
    }

    @PostMapping("valid")
    String valid(
            @ModelAttribute("sampleModel") @Valid final SampleModel sampleModel,
            final BindingResult bindingResult,
            final Model model) {

        if (bindingResult.hasErrors()) {
            return "/valid/index";
        }

        model.addAttribute("sampleModel", sampleModel);
        return "/valid/success";
    }

}
