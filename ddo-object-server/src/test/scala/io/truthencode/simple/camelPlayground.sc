import java.time.Duration

import org.apache.camel.LoggingLevel
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.properties.PropertiesComponent
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.model.dataformat.FlatpackDataFormat
import org.apache.camel.spi.DataFormat


val camelContext = new DefaultCamelContext

val timeout = Duration.ofSeconds(15).toMillis
val pc = new PropertiesComponent
pc.setLocation("classpath:camel.properties")
camelContext.addComponent("properties", pc)
camelContext.addRoutes(new RouteBuilder() {
  override def configure(): Unit = {
    //from("test-jms:queue:test.queue").to("file://test")
    val df = new FlatpackDataFormat()
    from("file://{{spells.dir}}/")
      .unmarshal(df)
    //  .to("seda:queue:neworder"))
      .log(LoggingLevel.DEBUG, s"reading divination file")
      .to("mock:results-df")
   //   .to("flatpack:foo?splitRows=true")

  }
})
camelContext.start()
println(s"Starting Uptime ${camelContext.getUptime}")

while (camelContext.getUptimeMillis < timeout) {}

println(s"Ending Uptime ${camelContext.getUptime}")
println("Done")
camelContext.stop()
