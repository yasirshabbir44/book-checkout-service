package com.smartdubai.yasir;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smartdubai.yasir.controller.BookController;
import com.smartdubai.yasir.dto.BookDTO;
import com.smartdubai.yasir.exception.BookException;
import com.smartdubai.yasir.service.BookService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SmartDubaiTestApplication.class})
@WebAppConfiguration
@SpringBootTest
public class BookControllerTest {

    private MockMvc mvc;

    private BookService bookService = Mockito.mock(BookService.class);

    public static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    @Before
    public void setup() {
        BookController bookController = new BookController(bookService);
        mvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }


    @org.junit.Test
    public void testGetAllBook() throws Exception {

        List<BookDTO> books = List.of(BookDTO.builder().id(1l)
                .author("Yasir")
                .createDate(new Date())
                .description("Coding")
                .isbn("111-222-333")
                .modifyDate(new Date())
                .price(200f)
                .type("fiction")
                .name("Java Book")
                .build());


        when(bookService.getAllBook()).thenReturn(books);

        mvc.perform(get("/api/book")).andExpect(status().is2xxSuccessful()).andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));

        Mockito.verify(bookService, Mockito.times(1)).getAllBook();
    }



    @org.junit.Test
    public void testGetBook() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .id(1l)
                .author("Yasir")
                .createDate(new Date())
                .description("Coding")
                .isbn("111-222-333")
                .modifyDate(new Date())
                .price(200f)
                .type("fiction")
                .name("Java Book")
                .build();
        when(bookService.getBookDTOById(1l)).thenReturn(bookDTO);

        mvc.perform(get("/api/book/1")).andExpect(status().is2xxSuccessful()).andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
        Mockito.verify(bookService, Mockito.times(1)).getBookDTOById(Mockito.eq(1l));
    }


    @org.junit.Test
    public void testCreateBook() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .author("Yasir")
                .createDate(new Date())
                .description("Coding")
                .isbn("111-222-333")
                .modifyDate(new Date())
                .price(200f)
                .type("fiction")
                .name("Java Book")
                .build();

        BookDTO saveBookDTO = bookDTO.toBuilder().id(1l).build();
        when(bookService.save(bookDTO)).thenReturn(saveBookDTO);
        mvc.perform(post("/api/book").content(getJson(bookDTO)).contentType(APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
        //Mockito.verify(bookService, Mockito.times(1)).save(saveBookDTO);
    }


    @org.junit.Test
    public void testUpdateBook() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .id(1l)
                .author("Yasir")
                .createDate(new Date())
                .description("Coding")
                .isbn("111-222-333")
                .modifyDate(new Date())
                .price(200f)
                .type("fiction")
                .name("Java Book")
                .build();
        when(bookService.update(bookDTO)).thenReturn(bookDTO);
        mvc.perform(put("/api/book").
                content(getJson(bookDTO)).contentType(APPLICATION_JSON)).andExpect( status().is2xxSuccessful());
        Mockito.verify(bookService, Mockito.times(1)).update(bookDTO);
    }

    @org.junit.Test
    public void testDeleteBook() throws Exception {
        when(bookService.delete(1l)).thenReturn(true);
        mvc.perform(delete("/api/book/1")).andExpect(status().is2xxSuccessful());
        Mockito.verify(bookService, Mockito.times(1)).delete(1l);
    }


    @org.junit.Test
    public void testDeleteBookError() throws Exception {
        when(bookService.delete(2l)).thenThrow(new BookException());
        mvc.perform(delete("/api/book/2")).andExpect(status().is4xxClientError());
        Mockito.verify(bookService, Mockito.times(1)).delete(2l);
    }

    @org.junit.Test
    public void testDeleteBookErrorArgument() throws Exception {
        when(bookService.delete(2l)).thenThrow(new BookException(404,"NotFound"));
        mvc.perform(delete("/api/book/2")).andExpect(status().is4xxClientError());
        Mockito.verify(bookService, Mockito.times(1)).delete(2l);
    }

    private String getJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(obj);
    }
}
