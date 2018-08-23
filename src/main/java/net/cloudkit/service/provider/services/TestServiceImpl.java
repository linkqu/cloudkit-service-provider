package net.cloudkit.service.provider.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    public String sayHello(String name) {
        logger.info("Hello {}!", name);
        return "Hello " + name + "!";
    }
}
