package com.test.batchsql.config;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.ClassRule;
import org.postgresql.Driver;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;

import javax.sql.DataSource;

/**
 * Конфиг, который заменяет датасурс на тестовый, поднимаемый к тест-контейнеру
 */
@ActiveProfiles(profiles = {"junit"})
@Import(value = {ConfigDbCommon.class})
@TestConfiguration()
public class ConfigDataSourcesOnTestcontainers {
    @ClassRule
    public static final PostgreSQLContainer<?> modernPreparedDataLayerDb;

    static {
        modernPreparedDataLayerDb = new PostgreSQLContainer<>("postgres:14.3")
                .withInitScript("test-scheme/ddl.sql");
        modernPreparedDataLayerDb.start();
        ScriptUtils.runInitScript(new JdbcDatabaseDelegate(modernPreparedDataLayerDb, ""), "test-scheme/dml-generated.sql");
    }

    @Bean("dataSource")
    public DataSource dataSource() {
        var dataSource = new HikariDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setJdbcUrl(modernPreparedDataLayerDb.getJdbcUrl());
        dataSource.setUsername(modernPreparedDataLayerDb.getUsername());
        dataSource.setPassword(modernPreparedDataLayerDb.getPassword());
        return dataSource;
    }
}