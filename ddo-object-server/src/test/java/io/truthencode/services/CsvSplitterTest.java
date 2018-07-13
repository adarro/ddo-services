package io.truthencode.services;

import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.apache.camel.spring.javaconfig.Main;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
// @ExtendWith(SpringExtension.class)
@RunWith(CamelSpringBootRunner.class)
class CsvSplitterTest {

//    class RB extends RouteBuilder {
//        @Override
//        public void configure() throws Exception {
//            from("file://{{spells.dir}}/").log(LoggingLevel.DEBUG, "reading divination file").to("flatpack:foo");
//        }
//    }
//
//    class Confg extends CamelConfiguration {
//        @Override
//        protected void setupCamelContext(CamelContext camelContext) throws Exception {
//            camelContext.addRoutes(new RB());
//        }
//    }
    @Autowired
    private CamelContext camelContext;

    @Test
    void doFoo() {


    }


}
