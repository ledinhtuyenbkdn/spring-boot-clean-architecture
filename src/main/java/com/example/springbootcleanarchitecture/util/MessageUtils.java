package com.example.springbootcleanarchitecture.util;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

//@PropertySource("classpath:messages.properties")
@Component
public class MessageUtils {

    private final Environment env;

    public MessageUtils(Environment env) {
        this.env = env;
    }

    public String getMessageValue(String key) {
        return env.getProperty(key);
    }
}
