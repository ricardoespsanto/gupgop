# Gupgop - A blockchain API

This application serves as a simplistic facade over other block explorers such as [Blockchain's API](https://blockchain.info/api/blockchain_api).

A particular aspect of this application is that it strives to return an empty answer when one was not,
for whatever reason, possible to be extracted. This can became handy if we're using a block explorer 
through a minimal system and wish to present always updated values or use cached ones when not possible. 

## Tech stack
Below is a short summary of the tech involved in this application:

### Framework
Gupgop is a [Spring boot](https://spring.io/projects/spring-boot) application, that was scaffolded 
with [Spring initializr](https://start.spring.io/).

### Base language
This application was developed in Java 8 but should be migrated to Java 9 as soon as possible even 
if no code change is done as this would [improve memory consumption](https://medium.com/@ricardoespsanto/java-9-a-more-space-efficient-string-32c2354509dc).

Given some of the more practical aspects of developing an application nowadays, some code _generation_
tools have been used. In particular - [Lombok](https://projectlombok.org/). As this may not be to 
everyone's taste, ([is lombok a hack ?](https://medium.com/@ricardoespsanto/lombok-31997912dd88)) an 
alternative can be used instead.

### Build tool
It uses [gradle](https://gradle.org/) as a building tool.

### Others
It depends on a few other [spring cloud](https://projects.spring.io/spring-cloud/) 
pieces of machinery to provide additional behaviour and hardening it for production environments.

#### Circuit breaker
[Hystrix](https://spring.io/guides/gs/circuit-breaker/) is used as a circuit breaker _(the 
implementation packaged by spring as the original [Hystrix](https://github.com/Netflix/Hystrix) was 
developed by Netflix OSS)_

#### Health
It provides a few health endpoints through [spring boot's actuator](https://spring.io/guides/gs/actuator-service/)

#### Documentation 
The application's source code is naturally documented with **Javadoc** but also relies on [Swagger](https://swagger.io/)
to provide a live documentation of its API. This was implemented under the hood with [Springfox](http://springfox.github.io/springfox/)

## Current status
The application is production ready event though, as with all software, there's more work to be done.

### Known issues
The target API (Blockchain's API) supports multiple addresses as parameters to the endpoint but due 
to the natural nature of Spring's URL encoding that behaviour is lost. This is an easy fix given the
right configuration.


## Future enhancements
- We need to include a build pipeline code. Perhaps 
[Spring cloud pipeline's](https://cloud.spring.io/spring-cloud-pipelines/single/spring-cloud-pipelines.html)? 
... Concourse is a [personal favorite](https://medium.com/@ricardoespsanto/jenkins-is-dead-long-live-concourse-ce13f94e4975)
.. wink wink.
- Containerisation, obviously.
- We should think about hosting this in a cloud... AWS _cloudformation_ code 
- Perhaps convert the API into an AWS gateway and have the executing logic converted into a serverless
component like AWS Lambda
- Use [Kotlin](https://kotlinlang.org/) as a replacement for the code generation mechanism (lombok)
- Track code quality and documentation and test coverage with a static code analysis tool that 
publishes to [sonarqube](https://www.sonarqube.org/)

## Contributions
Please don't... this is a short lived project with a limited usage. It is not intended to be 
representative of any future work.