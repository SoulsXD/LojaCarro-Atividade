package br.org.edu.ifrn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = br.org.edu.ifrn.LojaCarro.LojaCarroApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CarroIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    // 1. Buscar um ID inexistente
    @Test
    @Sql(statements = "DELETE FROM carro", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void deveRetornarStatus404AoBuscarCarroInexistente() throws Exception {
        mockMvc.perform(get("/carro/999"))
                .andExpect(status().isNotFound()); // Este vai PASSAR!
    }

    // 2. Salvar carro com preço negativo
    @Test
    public void deveRetornarStatus400AoSalvarCarroComPrecoNegativo() throws Exception {
        String carroJson = "{\"marca\":\"Ford\",\"modelo\":\"Fiesta\",\"ano\":2020,\"preco\":-500.00}";

        mockMvc.perform(post("/carro/salvar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carroJson))
                .andExpect(status().isBadRequest()); // Este vai FALHAR (O sistema aceita salvar, retornando 200 OK)
    }

    // 3. Atualizar um carro inexistente
    @Test
    public void deveRetornarStatus404AoAtualizarCarroInexistente() throws Exception {
        String carroJson = "{\"marca\":\"Chevrolet\",\"modelo\":\"Onix\",\"ano\":2022,\"preco\":70000.00}";

        mockMvc.perform(put("/carro/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carroJson))
                .andExpect(status().isNotFound()); // Este vai FALHAR (O sistema estoura uma exceção 500 no console)
    }

    // 4. Salvar carro sem modelo
    @Test
    public void deveRetornarStatus400AoSalvarCarroSemModelo() throws Exception {
        String carroJson = "{\"marca\":\"Fiat\",\"ano\":2021,\"preco\":50000.00}";

        mockMvc.perform(post("/carro/salvar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carroJson))
                .andExpect(status().isBadRequest()); // Este vai FALHAR (O sistema aceita salvar nulo, retornando 200 OK)
    }
}