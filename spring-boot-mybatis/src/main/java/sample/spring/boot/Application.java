package sample.spring.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sample.spring.boot.mybatis.SampleRepository;

import javax.annotation.Resource;

/**
 * Created by yamashiro-r on 2017/02/06.
 */
@SpringBootApplication
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Resource
    private SampleRepository sampleRepository;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args -> {
            System.out.println("countByJava:" + this.sampleRepository.countByJava());
            System.out.println("countByXml:" + this.sampleRepository.countByXml());
        });
    }

}
