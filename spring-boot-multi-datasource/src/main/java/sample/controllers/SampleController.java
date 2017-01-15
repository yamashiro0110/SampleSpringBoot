package sample.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sample.domain.models.main.JpaMainTableRepository;
import sample.domain.models.main.MainTable;
import sample.domain.models.main.MainTableRepository;
import sample.domain.models.sub.JpaSubTableRepository;
import sample.domain.models.sub.SubTable;
import sample.domain.models.sub.SubTableRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by YamashiroRyota on 2016/05/31.
 */
@RestController
@RequestMapping("/sample")
public class SampleController {
    @Resource
    private MainTableRepository mainTableRepository;
    @Resource
    private JpaMainTableRepository jpaMainTableRepository;
    @Resource
    private SubTableRepository subTableRepository;
    @Resource
    private JpaSubTableRepository jpaSubTableRepository;

    @RequestMapping(value = "main/prototype", method = RequestMethod.GET)
    public MainTable mainPrototype() {
        return MainTable.builder()
                .id(0L)
                .msg("i have a pen.")
                .build();
    }

    @RequestMapping(value = "main/list", method = RequestMethod.GET)
    public List<MainTable> mainTables() {
        return this.mainTableRepository.findAll();
    }

    @RequestMapping(value = "main/{id}", method = RequestMethod.GET)
    public MainTable mainTable(@PathVariable("id") final Long id) {
        return this.jpaMainTableRepository.findOne(id);
    }

    @RequestMapping(value = "sub/list", method = RequestMethod.GET)
    public List<SubTable> subTables() {
        return this.subTableRepository.findAll();
    }

    @RequestMapping(value = "sub/{id}", method = RequestMethod.GET)
    public SubTable subTable(@PathVariable("id") final Long id) {
        return this.jpaSubTableRepository.findOne(id);
    }

}
