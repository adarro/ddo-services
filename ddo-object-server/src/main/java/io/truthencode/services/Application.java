package io.truthencode.services;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    @Autowired
    private CamelContext camelContext;
    private static Class DefaultClass = Application.class;

    public static ConfigurableApplicationContext App(Class clazz, String[] args) {
        return SpringApplication.run(Application.class, args);
    }

    public static ConfigurableApplicationContext App(String... args) {
        return App(DefaultClass, args);
    }

    public static void main(String[] args) {
        App(Application.class, args);
    }
}
