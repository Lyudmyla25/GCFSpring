package org.example.gcfspring;

import org.example.gcfspring.controller.Book2Controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Book2Controller.class)
class Book2ControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/getAllBooks?isSample=false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].title").value("Book 1"))
                .andExpect(jsonPath("$[1].title").value("Book 2"))
                .andExpect(jsonPath("$[2].title").value("Book 3"));
    }

    @Test
    void testListBooksSample() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/getAllBooks?isSample=true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Book 1"));
    }

    @Test
    void testListBooksFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/getAllBooks"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testListBooksInvalidParam() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/getAllBooks?isSample=notabool"))
                .andExpect(status().isBadRequest());
    }
}
