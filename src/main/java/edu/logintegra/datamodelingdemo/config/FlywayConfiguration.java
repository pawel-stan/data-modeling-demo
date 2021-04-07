package edu.logintegra.datamodelingdemo.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Potrzebne, by migracje Flyway wykonywały się po akcjach Hibernate (np. po utworzeniu tabeli).
 *
 * Za https://stackoverflow.com/a/58989276/2224598
 */
@Configuration
public class FlywayConfiguration {

    @Autowired
    public FlywayConfiguration(DataSource dataSource) {
        Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
    }
}
