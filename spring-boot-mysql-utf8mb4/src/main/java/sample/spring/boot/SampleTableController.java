package sample.spring.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by yamashiro-r on 2017/02/02.
 */
@RestController
@RequestMapping("/sample")
public class SampleTableController {
    @Resource
    private SampleTableRepository sampleTableRepository;
    @Resource
    private SampleTableMapper sampleTableMapper;

    @RequestMapping(method = GET, path = "list")
    String list() {
        return this.sampleTableRepository.findAll().toString();
    }

    @RequestMapping(method = GET, path = "save/jpa")
    String saveByJpa(@RequestParam("val") final String value) {
        final SampleTable sampleTable = SampleTable.builder()
                .id((long) LocalDateTime.now().getSecond())
                .name(value)
                .created(new Date())
                .build();
        this.sampleTableRepository.save(sampleTable);
        return String.format("save: %s, %s", value, this.list());
    }

    @RequestMapping(method = GET, path = "save/mapper")
    String saveByMapper(@RequestParam("val") final String value) {
        final SampleTable sampleTable = SampleTable.builder()
                .id((long) LocalDateTime.now().getSecond())
                .name(value)
                .created(new Date())
                .build();
        this.sampleTableMapper.save(sampleTable);
        return String.format("save: %s, %s", value, this.list());
    }
}
