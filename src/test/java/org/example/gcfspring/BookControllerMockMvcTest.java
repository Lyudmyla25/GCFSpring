package org.example.gcfspring;

import org.example.gcfspring.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("book-list"));
    }

    @Test
    void testShowAddBookForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-book"));
    }

    @Test
    void testAddBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books/add")
                .param("id", "10")
                .param("title", "Test Title")
                .param("author", "Test Author"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @Test
    void testEditBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books/edit/1")
                .param("id", "1")
                .param("title", "Updated Title")
                .param("author", "Updated Author"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }
}
