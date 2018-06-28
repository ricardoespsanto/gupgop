package com.ricardoespsanto.gupgop.api.address;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ricardoespsanto.gupgop.model.Outputs;
import java.net.URI;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/** A supporting service for querying the block explorer address features */
@Service
class AddressService {

  private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

  private final RestTemplate restTemplate;

  @Value("${blockExplorerUrl}")
  private String blockExplorerUrl;

  public AddressService(final RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @HystrixCommand(fallbackMethod = "noResponse")
  ResponseEntity<Outputs> getUnspentOutputs(final String address) {
    URI blockExplorerUri = URI.create(blockExplorerUrl + address);
    try {
      return restTemplate.exchange(
          blockExplorerUri, HttpMethod.GET, null, new ParameterizedTypeReference<Outputs>() {});
    } catch (HttpServerErrorException e) {
      return ResponseEntity.ok(Outputs.of(Collections.emptyList()));
    }
  }

  /**
   * A fallback strategy returning an empty <code>Outputs</code>.
   *
   * <p>This method is used by the circuit breaker
   *
   * @return an empty Outputs Object
   */
  @SuppressWarnings("unused")
  public ResponseEntity<Outputs> noResponse(final String address) {
    logger.info("Couldn't contact: {}. Returning a default answer", blockExplorerUrl + address);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(Outputs.of(Collections.emptyList()));
  }
}
