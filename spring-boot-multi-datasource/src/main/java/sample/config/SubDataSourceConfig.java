package sample.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import sample.domain.models.sub.SubTable;

import javax.sql.DataSource;

/**
 * @see MainDataSourceConfig
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = {SubTable.class}, entityManagerFactoryRef = "subEntityManagerFactory")
public class SubDataSourceConfig {
    @Value("${spring.ds_sub.datasource.scheme}")
    private String schemeSql;
    @Value("${spring.ds_sub.datasource.data}")
    private String dataSql;
    @Value("${spring.ds_sub.datasource.initialize}")
    private boolean isDataSourceInitialize;

    @Bean("subDataSource")
    @ConfigurationProperties(prefix = "spring.ds_sub.datasource")
    DataSource subDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("subJdbcTemplate")
    JdbcTemplate subJdbcTemplate(@Qualifier("subDataSource") final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("subEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean subEntityManagerFactory(final EntityManagerFactoryBuilder builder) {
        return builder.dataSource(subDataSource())
                .packages(SubTable.class)
                .persistenceUnit("sub_unit")
                .build();
    }

    @Bean("subDataSourceInitializer")
    DataSourceInitializer dataSourceInitializer(@Qualifier("subDataSource") final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        initializer.setEnabled(this.isDataSourceInitialize);
        return initializer;
    }

    private ResourceDatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource(this.schemeSql));
        populator.addScript(new ClassPathResource(this.dataSql));
        return populator;
    }

}
