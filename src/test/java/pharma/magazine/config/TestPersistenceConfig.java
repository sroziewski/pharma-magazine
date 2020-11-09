package pharma.magazine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories
public class TestPersistenceConfig {

    @Bean(destroyMethod = "close")
    @Profile("test")
    public PostgreSQLContainer postgres() {
        PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:10.13-alpine");
        postgres.start();
        return postgres;
    }

    @Bean
    @Profile("test")
    public DataSource dataSource(PostgreSQLContainer postgres) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.testcontainers.jdbc.ContainerDatabaseDriver");
        dataSource.setUrl(postgres.getJdbcUrl());
        dataSource.setUsername(postgres.getUsername());
        dataSource.setPassword(postgres.getPassword());
        return dataSource;
    }
}
