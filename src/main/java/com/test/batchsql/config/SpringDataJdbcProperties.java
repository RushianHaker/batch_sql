package com.test.batchsql.config;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Формирует датасурсы
 */

@Data
@NoArgsConstructor
public class SpringDataJdbcProperties {
    /**
     * JDBC URL property
     */
    String url;
    /**
     * JDBC driver class name property
     */
    String driver;
    /**
     * JDBC username property
     */
    String user;
    /**
     * JDBC password property
     */
    String password;
    /**
     * Hikari / Vertica maxPoolSize property
     */
    String poolSize;
    /**
     * Minimum pool size
     */
    int minPoolSize = 4;
    /**
     * Maximum pool size
     */
    int maxPoolSize = 10;
    /**
     * This property controls the maximum amount of time (in milliseconds) that a connection is allowed to
     * sit idle in the pool. A value of 0 means that idle connections are never removed from the pool.
     */
    long idleTimeout;
    /**
     * This property controls the maximum lifetime of a connection in the pool. When a connection
     * reaches this timeout, even if recently used, it will be retired from the pool.
     * An in-use connection will never be retired, only when it is idle will it be removed
     */
    long maxLifetime;
    /**
     * Bulk insert size
     */
    Integer bulkSize;


    /**
     * All-args constructor for {@link SpringDataJdbcProperties#toString()} (logging)
     *
     * @param url JDBC driver class name property
     * @param driver JDBC driver class name property
     * @param user JDBC username property
     * @param password JDBC password property
     * @param poolSize Hikari / Vertica maxPoolSize property
     * @param bulkSize bulk insert size
     */
    public SpringDataJdbcProperties(
            String url, String driver, String user, String password, String poolSize, Integer bulkSize) {
        this.url = url;
        this.driver = driver;
        this.user = user;
        this.password = password;
        this.poolSize = poolSize;
        this.bulkSize = bulkSize;
    }



    /**
     * Возвращает строковое представление экземпляра объекта в формате JSON
     *
     * @return строковое представление экземпляра объекта в формате JSON
     */
    @Override
    public String toString() {
        var checkedPassword = ((password == null) || password.isEmpty()) ? "" : "*****";

        return "SpringDataJdbcProperties{" +
                "url='" + url + '\'' +
                ", driver='" + driver + '\'' +
                ", user='" + user + '\'' +
                ", password='" + checkedPassword + '\'' +
                ", poolSize='" + poolSize + '\'' +
                ", minPoolSize=" + minPoolSize +
                ", maxPoolSize=" + maxPoolSize +
                ", idleTimeout=" + idleTimeout +
                ", maxLifetime=" + maxLifetime +
                ", bulkSize=" + bulkSize +
                '}';
    }
}
