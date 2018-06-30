/*
 * (c) Copyright Reserved EVRYTHNG Limited 2017. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.ricardoespsanto.gupgop.unspentoutputs.service;

import com.ricardoespsanto.gupgop.unspentoutputs.model.Outputs;

/** A supporting service for querying the block explorer unspent outputs features. */
public interface UnspentOutputsService {

  /**
   * Retrieve all Unspent outputs for the given address
   *
   * @param address the address for which we want to retrieve the unspent outputs
   * @return a wrapper {@link Outputs} object with a <code>Collection</code> of unspent outputs
   */
  Outputs getUnspentOutputs(final String address);
}
