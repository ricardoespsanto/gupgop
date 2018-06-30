/*
 * (c) Copyright Reserved EVRYTHNG Limited 2017. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.ricardoespsanto.gupgop.api.address;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  public void shouldReturnDefaultMessage() throws Exception {
    String emptyOutputs = "{\n" + "  \"outputs\" : [ ]\n" + "}";
    mockMvc
        .perform(get("/address/something_that_would_not_work"))
        .andExpect(status().isOk())
        .andExpect(content().json(emptyOutputs));
  }
}
