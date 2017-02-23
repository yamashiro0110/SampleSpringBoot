package sample.spring.boot;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by yamashiro-r on 2017/02/02.
 */
@Controller
@RequestMapping("/sample")
@Transactional
public class SampleTableController {
    @Resource
    private SampleTableRepository sampleTableRepository;
    @Resource
    private SampleTableMapper sampleTableMapper;

    private SampleTable create(final String value) {
        return SampleTable.builder()
                .id(this.sampleTableRepository.count() + 1)
                .name("ピコ太郎")
                .post(value)
                .created(new Date())
                .build();
    }

    @ModelAttribute("sampleTables")
    private List<SampleTable> sampleTables() {
        return this.sampleTableRepository.findAll();
    }

    @GetMapping
    String index() {
        return "sample";
    }

    @GetMapping("list/jpa")
    String listByJpa(final Model model) {
        model.addAttribute("sampleTables", this.sampleTableRepository.findAll());
        return "sample";
    }

    @GetMapping("save/jpa")
    String redirectSaveByJpa() {
        return "redirect:/sample/list/jpa";
    }

    @PostMapping("save/jpa")
    String saveByJpa(@RequestParam("post") final String value) {
        this.sampleTableRepository.saveAndFlush(this.create(value));
        return "redirect:/sample/list/jpa";
    }

    @GetMapping("list/mapper")
    String listByMyBatis(final Model model) {
        model.addAttribute("sampleTables", this.sampleTableMapper.findAll());
        return "sample";
    }

    @GetMapping("save/mapper")
    String redirectSaveByMapper() {
        return "redirect:/sample/list/mapper";
    }

    @PostMapping("save/mapper")
    String saveByMapper(@RequestParam("post") final String value) {
        this.sampleTableMapper.save(this.create(value));
        return "redirect:/sample/list/mapper";
    }
}
