package io.truthencode.simple

import io.truthencode.services.Application
import org.springframework.boot.SpringApplication
import org.springframework.context.{ApplicationContext, ApplicationContextAware}

trait SpringAware extends ApplicationContextAware{
  override def setApplicationContext(applicationContext: ApplicationContext): Unit = ctx = applicationContext
  private var ctx:ApplicationContext = _
  lazy val springCtx: ApplicationContext = {
    if (Option(ctx).isEmpty) {
      ctx = Application.App()
    }
    ctx
  }
}
