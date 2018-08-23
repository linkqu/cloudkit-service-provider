package net.cloudkit.service.provider.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * JdbcTemplateConfiguration
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2018-05-16 2:04 PM
 */
@Configuration
public class JdbcTemplateConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
