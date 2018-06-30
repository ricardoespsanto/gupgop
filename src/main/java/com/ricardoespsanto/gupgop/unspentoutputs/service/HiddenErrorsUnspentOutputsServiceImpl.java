package com.ricardoespsanto.gupgop.unspentoutputs.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ricardoespsanto.gupgop.unspentoutputs.model.Outputs;
import java.net.URI;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This service applies a <i>always valid</i> answer strategy in which regardless of being able to
 * contact the block explorer or not it will always return an answer that seems valid.
 *
 * <p>The block explorer service backing the features in this service are configurable by a
 * properties file with the value <code>blockExplorerUrl</code> or, in absence, an <code>
 * environment variable</code> with the same key, will be used instead.
 */
@Service
class HiddenErrorsUnspentOutputsServiceImpl implements UnspentOutputsService {

  private static final Logger logger =
      LoggerFactory.getLogger(HiddenErrorsUnspentOutputsServiceImpl.class);

  private final RestTemplate restTemplate;

  @Value("${blockExplorerUrl:#{environment.blockExplorerUrl}}")
  private String blockExplorerUrl;

  public HiddenErrorsUnspentOutputsServiceImpl(final RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
   * Retrieve Unspent Outputs for a given address.
   *
   * <p>This method will return all the unspent outputs wrapped in a {@link Outputs} Object.
   *
   * <p><strong>Note that</strong>: There are two conditions under which this method will return a
   * value that may make it seem like the account has no unspent outputs but that may not be the
   * case:
   *
   * <ul>
   *   <li>when the block explorer service backing this feature is not reachable
   *   <li>when the server returned some sort of error or unparsable content
   * </ul>
   *
   * In these instances, the method will return an empty <code>Collection</code> of unspent outputs.
   *
   * @param address the address for which we want to retrieve the unspent outputs
   * @return Unspent outputs wrapped in {@link Outputs}
   */
  @Override
  @HystrixCommand(fallbackMethod = "noResponse")
  public Outputs getUnspentOutputs(final String address) {
    URI blockExplorerUri = URI.create(blockExplorerUrl + address);
    return restTemplate.exchange(blockExplorerUri, HttpMethod.GET, null, Outputs.class).getBody();
  }

  /**
   * A fallback strategy returning an empty <code>Collection</code> of unspent outputs.
   *
   * <p>This method is used by the circuit breaker
   *
   * @return an empty Outputs Object
   */
  @SuppressWarnings("unused")
  public Outputs noResponse(final String address) {
    logger.info(
        "Couldn't retrieve the required information from: {}. Returning a default answer",
        blockExplorerUrl + address);
    return Outputs.of(Collections.emptyList());
  }
}
