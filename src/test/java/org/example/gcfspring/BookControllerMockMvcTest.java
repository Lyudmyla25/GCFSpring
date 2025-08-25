package org.example.gcfspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
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
    void testDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
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
                .param("title", "Test Title")
                .param("author", "Test Author"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Test Title")));
    }

    @Test
    void testEditBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books/add")
                .param("title", "Old Title")
                .param("author", "Old Author"))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(MockMvcRequestBuilders.post("/books/edit/1")
                .param("title", "Updated Title")
                .param("author", "Updated Author"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Updated Title")));
    }
}
