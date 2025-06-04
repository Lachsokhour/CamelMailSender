package com.slach.research.camelmailsender.config;

import com.slach.research.camelmailsender.service.EmailService;
import java.io.File;
import java.util.List;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileWatcherRoute extends RouteBuilder {


  private final EmailService emailService;

  public FileWatcherRoute(EmailService emailService) {
    this.emailService = emailService;
  }

  @Override
  public void configure() throws Exception {
    from("file:/path/to/watch?noop=true")
      .routeId("file-watcher-email")
      .process(exchange -> {
        File file = exchange.getIn().getBody(File.class);
        emailService.sendEmail("sokhour.lach@allweb.com.kh", "New File Notification",
          "File found: " + file.getName(), List.of(file));
      });
  }
}
