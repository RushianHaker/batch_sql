package com.test.batchsql.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * Устанавливает конфигурации и соединение с базой данных
 */
@Configuration
public class ConfigDbCommon extends DefaultDbConfig{
    @Bean
    @Qualifier("postgres")
    @ConfigurationProperties(prefix = "app.db.postgres")
    SpringDataJdbcProperties frapPgJdbcProperties() {
        return new SpringDataJdbcProperties();
    }

    @Bean
    @Qualifier("postgres")
    public DataSource frapPgDataSource(@Qualifier("postgres") SpringDataJdbcProperties properties) {
        return hikariDataSource("POSTGRES", properties);
    }

    @Bean
    @Qualifier("postgres")
    JdbcTemplate frapPgJdbcTemplate(@Qualifier("postgres") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Qualifier("postgres")
    NamedParameterJdbcTemplate namedFrapJdbcTemplate(@Qualifier("postgres") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
