package io.truthencode.services.config;

import org.apache.camel.CamelContext;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig extends CamelConfiguration {


    @Override
    protected void setupCamelContext(CamelContext camelContext) throws Exception {
        PropertiesComponent pc = new PropertiesComponent();
        pc.setLocation("classpath:camel.properties");
        camelContext.addComponent("properties",pc);
    }
}