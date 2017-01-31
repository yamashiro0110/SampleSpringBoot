package sample.spring.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Resource
    private SampleTableRepository sampleTableRepository;

    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext context) {
        return (args -> this.sampleTableRepository.findAll().forEach(System.out::println));
    }
}
