package com.ricardoespsanto.gupgop.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/**
 * A class to support the concept of unspent output. Essentially an output of a transaction which
 * has not been used as input to another one.
 */
@Value
class UnspentOutput {

  private String value;

  @JsonProperty("tx_hash")
  private String transactionHash;

  @JsonProperty("output_idx")
  @JsonAlias("tx_output_n")
  private String outputIndex;
}
