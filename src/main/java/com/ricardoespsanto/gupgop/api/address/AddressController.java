package com.ricardoespsanto.gupgop.api.address;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.ricardoespsanto.gupgop.unspentoutputs.model.Outputs;
import com.ricardoespsanto.gupgop.unspentoutputs.service.UnspentOutputsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** The web controller for the <code>Address</code> endpoints */
@Api(value = "Address", tags = "Address")
@RestController
@RequestMapping(value = "address", produces = APPLICATION_JSON_VALUE)
public class AddressController {

  private UnspentOutputsService unspentOutputsService;

  public AddressController(final UnspentOutputsService unspentOutputsService) {
    this.unspentOutputsService = unspentOutputsService;
  }

  @ApiOperation(
      httpMethod = "GET",
      value = "Retrieve unspent outputs",
      notes = "Retrieves all unspent outputs for a given address",
      response = Outputs.class,
      responseContainer = "Collection")
  @GetMapping("/{address}")
  public ResponseEntity<Outputs> getAllUnspentTransactionOutputs(
      @ApiParam(value = "The address for which to get the transactions", required = true)
          @PathVariable
          final String address) {
    return ResponseEntity.ok(unspentOutputsService.getUnspentOutputs(address));
  }
}
