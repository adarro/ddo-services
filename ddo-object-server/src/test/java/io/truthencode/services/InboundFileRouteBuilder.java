package io.truthencode.services;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component("spellSource")
public class InboundFileRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file://{{spells.dir}}/").log(LoggingLevel.DEBUG, "reading divination file").to("flatpack:foo");
    }
}
