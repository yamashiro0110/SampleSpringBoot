package com.example.demo;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Resource
    private DataSource dataSource;

    @Bean
    CommandLineRunner flywayCommandLineRunner() {
        return args -> {
            Flyway flyway = new Flyway();
            flyway.setDataSource(this.dataSource);
            flyway.setLocations("db/migration", "db/data");
            flyway.setTarget(MigrationVersion.LATEST);
            flyway.repair();
            flyway.migrate();
        };
    }

}
