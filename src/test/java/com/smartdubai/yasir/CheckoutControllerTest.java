package com.smartdubai.yasir;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smartdubai.yasir.controller.CheckoutController;
import com.smartdubai.yasir.dto.CheckoutDTO;
import com.smartdubai.yasir.dto.CheckoutRequestDTO;
import com.smartdubai.yasir.dto.CheckoutResponseDTO;
import com.smartdubai.yasir.service.CheckoutService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SmartDubaiTestApplication.class})
@WebAppConfiguration
@SpringBootTest
public class CheckoutControllerTest {


    public static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());
    private MockMvc mvc;
    private CheckoutService checkoutService = Mockito.mock(CheckoutService.class);

    @Before
    public void setup() {
        CheckoutController checkoutController = new CheckoutController(checkoutService);
        mvc = MockMvcBuilders.standaloneSetup(checkoutController).build();
    }


    @org.junit.Test
    public void testCheckout() throws Exception {
        CheckoutRequestDTO checkoutRequestDTO = CheckoutRequestDTO.builder()
                .checkoutList(List.of(CheckoutDTO.builder().bookId(1l).quantity(2l).build()))
                .promoCode("SMART")
                .build();


        CheckoutResponseDTO checkoutResponseDTO = CheckoutResponseDTO.builder()
                .totalPrice(320d)
                .build();

        when(checkoutService.checkout(checkoutRequestDTO)).thenReturn(checkoutResponseDTO);

        mvc.perform(post("/api/checkout").
                        content(getJson(checkoutRequestDTO)).contentType(APPLICATION_JSON)).
                andExpect(status().is2xxSuccessful());

    }


    private String getJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(obj);
    }
}
