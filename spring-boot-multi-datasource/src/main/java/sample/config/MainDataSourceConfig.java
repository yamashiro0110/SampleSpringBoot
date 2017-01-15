package sample.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import sample.domain.models.main.MainTable;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackageClasses = {MainTable.class}, entityManagerFactoryRef = "mainEntityManagerFactory")
public class MainDataSourceConfig {
    @Value("${spring.ds_main.datasource.scheme}")
    private String schemeSql;
    @Value("${spring.ds_main.datasource.data}")
    private String dataSql;
    @Value("${spring.ds_main.datasource.initialize}")
    private boolean isDataSourceInitialize;

    /**
     * {@link DataSource}の設定<br>
     * `application.properties`の`spring.ds_main.datasource.*`を参照
     *
     * @return
     */
    @Primary
    @Bean("mainDataSource")
    @ConfigurationProperties(prefix = "spring.ds_main.datasource")
    DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("mainJdbcTemplate")
    JdbcTemplate mainJdbcTemplate(@Qualifier("mainDataSource") final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * {@link javax.persistence.EntityManager}の設定
     *
     * @param builder
     * @return
     */
    @Primary
    @Bean("mainEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean mainEntityManagerFactory(final EntityManagerFactoryBuilder builder) {
        return builder.dataSource(mainDataSource())
                .packages(MainTable.class)
                .persistenceUnit("main_unit")
                .build();
    }

    /**
     * application起動時にDBを初期化する
     *
     * @param dataSource
     * @return
     */
    @Bean("mainDataSourceInitializer")
    DataSourceInitializer mainDataSourceInitializer(@Qualifier("mainDataSource") final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(mainDatabasePopulator());
        initializer.setEnabled(this.isDataSourceInitialize);
        return initializer;
    }

    private ResourceDatabasePopulator mainDatabasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource(this.schemeSql));
        populator.addScript(new ClassPathResource(this.dataSql));
        return populator;
    }

}
