package io.truthencode.simple

// import org.junit.jupiter.api.{Assertions, Test}
import io.truthencode.services.Application
import org.apache.camel.CamelContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
// import org.junit.platform.runner.JUnitPlatform

import org.junit.Test
import org.junit.runner.RunWith

// @RunWith(classOf[SpringJUnit4ClassRunner])
@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[Application]))
class SimpleTest extends SpringAware {

  lazy val camelContextLazyInit: CamelContext = springCtx.getBean(classOf[CamelContext])
  @Autowired
  private var camelContextAutoWired: CamelContext = _

  @Test
  def testEquals5(): Unit = {
    assert(2 + 2 == 4)
    // Assertions.assertEquals(5, 2 + 2, () => "")
  }

  @Test
  def testInjection(): Unit = {
    assume(Option(camelContextAutoWired).isDefined)
    assert(!camelContextAutoWired.getRoutes.isEmpty)
  }

  @Test
  def testContextAwareness(): Unit = {
    assert(Option(springCtx).isDefined, "springCtx not found or initialized")
  }

  @Test
  def testCamelContextFromSpringContext(): Unit = {
    val ctx = springCtx.getBean(classOf[CamelContext])
    assume(Option(ctx).isDefined)
    assert(!ctx.getRoutes.isEmpty, "No routes were configured")
  }

  @Test
  def testLazyLoadContext(): Unit = {
    assert(Option(camelContextLazyInit).isDefined)
  }

}