package com.ricardoespsanto.gupgop.api.address;

import com.ricardoespsanto.gupgop.model.Outputs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** The web controller for the <code>Address</code> endpoints */
@RestController
@RequestMapping("address")
public class AddressController {

  private AddressService addressService;

  public AddressController(final AddressService addressService) {
    this.addressService = addressService;
  }

  @GetMapping("/{bitcoinAddress}")
  public ResponseEntity<Outputs> getAllUnspentTransactionOutputs(
      @PathVariable final String bitcoinAddress) {
    return addressService.getUnspentOutputs(bitcoinAddress);
  }
}
