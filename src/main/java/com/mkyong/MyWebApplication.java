package com.mkyong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class MyWebApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(MyWebApplication.class, args);
//    }
//
//}

@SpringBootApplication
public class MyWebApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {
      SpringApplication.run(MyWebApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
      return builder.sources(MyWebApplication.class);
  }
}
