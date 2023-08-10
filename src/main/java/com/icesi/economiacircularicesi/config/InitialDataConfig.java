package com.icesi.economiacircularicesi.config;


import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class InitialDataConfig {

    @Autowired
    public void configureInitialData(DataSource dataSource, SpringLiquibase liquibase){

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("data/activities.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("data/principles.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("data/principle_activity.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("data/questions.sql"));
        DatabasePopulatorUtils.execute(resourceDatabasePopulator, dataSource);
    }
}
