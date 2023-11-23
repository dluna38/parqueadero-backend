package co.edu.iudigital.parqueadero;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsuariosEndPointsTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFail() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().is4xxClientError());
    }
}
