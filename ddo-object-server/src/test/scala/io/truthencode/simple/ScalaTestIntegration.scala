package io.truthencode.simple

import io.truthencode.services.Application
import org.apache.camel.CamelContext
import org.junit.runner.RunWith
import org.scalatest.{FunSpec, Matchers}
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.junit.Test

@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[Application]))
class ScalaTestIntegration extends FunSpec with Matchers with SpringAware {
  lazy val camelContextLazyInit: CamelContext = springCtx.getBean(classOf[CamelContext])

  describe("A test with Spring / Scala 2.12 and JUnit 5") {
    they("Should work together") {
      assume(Option(camelContextLazyInit).isDefined)
      camelContextLazyInit.getRoutes should not be empty
    }
  }

  @Test
  def findMe(): Unit = {
    1 + 2 shouldBe 3
  }
}
