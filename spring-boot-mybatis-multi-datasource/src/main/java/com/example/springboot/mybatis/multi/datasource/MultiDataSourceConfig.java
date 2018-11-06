package com.example.springboot.mybatis.multi.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

public class MultiDataSourceConfig {

    @MapperScan(basePackages = "com.example.springboot.mybatis.multi.datasource.mapper.apple", sqlSessionFactoryRef = AppleDataSource.SQL_SESSION_FACTORY_NAME)
    @Configuration
    public static class AppleDataSource {
        static final String SQL_SESSION_FACTORY_NAME = "appleDataSourceSqlSessionFactory";

        @Primary
        @Bean("appleDataSourceProperties")
        @ConfigurationProperties(prefix = "spring.apple.datasource")
        public DataSourceProperties appleDataSourceProperties() {
            return new DataSourceProperties();
        }

        @Primary
        @Bean("appleDataSource")
        public DataSource appleDataSource() {
            return this.appleDataSourceProperties()
                    .initializeDataSourceBuilder()
                    .build();
        }

        @Primary
        @Bean(name = SQL_SESSION_FACTORY_NAME)
        public SqlSessionFactory appleDataSourceSqlSessionFactory() throws Exception {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(this.appleDataSource());
            return sqlSessionFactoryBean.getObject();
        }
    }

    @MapperScan(basePackages = "com.example.springboot.mybatis.multi.datasource.mapper.pineapple", sqlSessionFactoryRef = PineappleDataSource.SQL_SESSION_FACTORY_NAME)
    @Configuration
    public static class PineappleDataSource {
        static final String SQL_SESSION_FACTORY_NAME = "pineappleDataSourceSqlSessionFactory";

        @Bean("pineappleDataSourceProperties")
        @ConfigurationProperties(prefix = "spring.pineapple.datasource")
        public DataSourceProperties pineappleDataSourceProperties() {
            return new DataSourceProperties();
        }

        @Bean("pineappleDataSource")
        public DataSource pineappleDataSource() {
            return this.pineappleDataSourceProperties()
                    .initializeDataSourceBuilder()
                    .build();
        }

        @Bean(name = SQL_SESSION_FACTORY_NAME)
        public SqlSessionFactory pineappleDataSourceSqlSessionFactory() throws Exception {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(this.pineappleDataSource());
            return sqlSessionFactoryBean.getObject();
        }

        @Bean("pineappleDataSourceInitializer")
        public DataSourceInitializer pineappleDataSourceInitializer() {
            DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
            dataSourceInitializer.setDataSource(this.pineappleDataSource());
            dataSourceInitializer.setDatabasePopulator(this.pineappleDatabasePopulator());
            return dataSourceInitializer;
        }

        private ResourceDatabasePopulator pineappleDatabasePopulator() {
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            DataSourceProperties dataSourceProperties = this.pineappleDataSourceProperties();

            dataSourceProperties.getSchema().stream()
                    .map(ClassPathResource::new)
                    .forEach(databasePopulator::addScript);

            dataSourceProperties.getData().stream()
                    .map(ClassPathResource::new)
                    .forEach(databasePopulator::addScript);

            return databasePopulator;
        }
    }

}
