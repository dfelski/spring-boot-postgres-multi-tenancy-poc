package com.example.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("databases.liquibase.datasource")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @LiquibaseDataSource
    @ConfigurationProperties("databases.liquibase.datasource.hikari")
    public DataSource masterDataSource() {
        HikariDataSource dataSource = masterDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
        dataSource.setPoolName("liquibaseDataSource");
        return dataSource;
    }

    @Bean
    @Primary
    @ConfigurationProperties("databases.app.datasource")
    public DataSourceProperties tenantDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("databases.app.datasource.hikari")
    public DataSource tenantDataSource() {
        HikariDataSource dataSource = tenantDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
        dataSource.setPoolName("appDataSource");
        return new TenantAwareDataSource(dataSource);
    }
}
