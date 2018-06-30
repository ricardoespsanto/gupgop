package com.ricardoespsanto.gupgop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** The starting point of Gupgop application. This is a spring boot application. */
@SpringBootApplication
@EnableSwagger2
@EnableHystrix
public class GupgopApplication {

  @Bean
  public RestTemplate restTemplate(final RestTemplateBuilder builder) {
    return builder.build();
  }

  public static void main(String[] args) {
    SpringApplication.run(GupgopApplication.class, args);
  }
}
