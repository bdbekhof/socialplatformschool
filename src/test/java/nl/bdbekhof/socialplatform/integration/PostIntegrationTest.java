package nl.bdbekhof.socialplatform.integration;

import nl.bdbekhof.socialplatform.controller.PostController;
import nl.bdbekhof.socialplatform.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
public class PostIntegrationTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    PostService postService;

    @Test
    void createPost() throws Exception {
        mvc.perform(post("/posts").param("content", "Hallo wereld"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        verify(postService, times(1)).create(any());
    }
}
