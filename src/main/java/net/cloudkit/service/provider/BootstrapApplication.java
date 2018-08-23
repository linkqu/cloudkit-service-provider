package net.cloudkit.service.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * BootstrapApplication
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2018-06-29 11:04
 */
@SpringBootApplication
@EnableAutoConfiguration
public class BootstrapApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BootstrapApplication.class, args);
    }
}
