package net.cloudkit.service.provider;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * ServletInitializer
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2018-04-26 18:59:01
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BootstrapApplication.class);
    }

}
