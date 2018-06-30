/*
 * (c) Copyright Reserved EVRYTHNG Limited 2017. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.ricardoespsanto.gupgop;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class DocumentationConfiguration {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.ricardoespsanto"))
        .paths(PathSelectors.ant("/address/*"))
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        "Gopgup API",
        "An application to facade unspent outputs from other block explorer APIs",
        "1.0.0",
        "http://localhost:8080/termsofservice.html",
        new Contact(
            "Ricardo Espirito Santo",
            "https://www.linkedin.com/in/ricardoespsanto",
            "ricardoespsanto@gmail.com"),
        "Apache License 2.0",
        "https://www.apache.org/licenses/LICENSE-2.0",
        Collections.emptyList());
  }
}
