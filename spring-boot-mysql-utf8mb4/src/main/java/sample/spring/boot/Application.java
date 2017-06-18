package sample.spring.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Resource
    private SampleTableRepository sampleTableRepository;
    @Resource
    private SampleTableMapper sampleTableMapper;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args -> this.sampleTableRepository.findAll().forEach(System.out::println));
    }

    @Bean
    public CommandLineRunner findSampleTable() {
        return args -> {
            this.sampleTableMapper.findByQuery("ビール").forEach(System.out::println);
            this.sampleTableMapper.findByQuery("ヒール").forEach(System.out::println);
        };
    }
}
