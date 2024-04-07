package com.example.site;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController implements ErrorController {

  /*
   * Controller for pages unrelated to any particular resource. Errors and miscellaneous pages might
   * be better off in separate controllers, but for simplicity they're grouped here.
   */

  @GetMapping("/")
  public String home() {
    return "index";
  }

  // Replaces Spring Boot's default error page
  @GetMapping("/error")
  public String error() {
    return "errors/404";
  }

  @GetMapping("/internal-server-error")
  public String internalServerError() {
    return "errors/500";
  }
}
