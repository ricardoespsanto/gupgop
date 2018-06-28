package com.ricardoespsanto.gupgop.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import lombok.Value;

/** A wrapper for multiple <code>UnspentOutputs</code> */
@Value(staticConstructor = "of")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Outputs {

  // This field is actually used and accessible via Lombok
  @SuppressWarnings("unused")
  @JsonProperty("outputs")
  @JsonAlias("unspent_outputs")
  private Collection<UnspentOutput> unspentOutputs;
}
