package com.ricardoespsanto.gupgop.unspentoutputs.service;

import static java.util.Collections.singleton;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ricardoespsanto.gupgop.api.address.AddressController;
import com.ricardoespsanto.gupgop.unspentoutputs.model.Outputs;
import com.ricardoespsanto.gupgop.unspentoutputs.model.UnspentOutput;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
public class HiddenErrorsUnspentOutputsServiceImplTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private UnspentOutputsService unspentOutputsService;

  @Test
  public void getUnspentOutputsReturnsEmptyIfServiceReturnsEmpty() throws Exception {
    when(unspentOutputsService.getUnspentOutputs(anyString()))
        .thenReturn(Outputs.of(Collections.emptyList()));

    String emptyOutputs = "{\n" + "  \"outputs\" : [ ]\n" + "}";
    mockMvc
        .perform(get("/address/1Aff4FgrtA1dZDwajmknWTwU2WtwUvfiXa"))
        .andExpect(status().isOk())
        .andExpect(content().json(emptyOutputs));
  }

  @Test
  public void getUnspentOutputsReturnsOutputsIfServiceReturnsOutputs() throws Exception {
    Outputs outputs = Outputs.of(singleton(UnspentOutput.of("val", "tx_hash", "output_index")));
    when(unspentOutputsService.getUnspentOutputs(anyString())).thenReturn(outputs);

    String jsonOutputs =
        "{\n"
            + "  \"outputs\" : [ {\n"
            + "    \"value\" : \"val\",\n"
            + "    \"tx_hash\" : \"tx_hash\",\n"
            + "    \"output_idx\" : \"output_index\"\n"
            + "  } ]\n"
            + "}";
    mockMvc
        .perform(get("/address/1Aff4FgrtA1dZDwajmknWTwU2WtwUvfiXa"))
        .andExpect(status().isOk())
        .andExpect(content().string(jsonOutputs));
  }
}
