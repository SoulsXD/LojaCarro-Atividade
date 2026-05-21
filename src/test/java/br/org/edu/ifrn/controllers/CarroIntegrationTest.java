package br.org.edu.ifrn.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = br.org.edu.ifrn.LojaCarro.LojaCarroApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class CarroIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    // PAR 1: HTTP POST - SALVAR PREÇO
    @Test
    public void deveRetornarStatus200AoSalvarCarroComPrecoValido() throws Exception {
        String carroJson = "{\"marca\":\"Ford\",\"modelo\":\"Fiesta\",\"ano\":2020,\"preco\":50000.00}";

        mockMvc.perform(post("/carro/salvar").contentType(MediaType.APPLICATION_JSON).content(carroJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarStatus400AoSalvarCarroComPrecoNegativo() throws Exception {
        String carroJson = "{\"marca\":\"Ford\",\"modelo\":\"Fiesta\",\"ano\":2020,\"preco\":-500.00}";

        mockMvc.perform(post("/carro/salvar").contentType(MediaType.APPLICATION_JSON).content(carroJson))
                .andExpect(status().isBadRequest());
    }

    // PAR 2: HTTP POST - SALVAR MODELO
    @Test
    public void deveRetornarStatus200AoSalvarCarroComModeloPreenchido() throws Exception {
        String carroJson = "{\"marca\":\"Fiat\",\"modelo\":\"Uno\",\"ano\":2019,\"preco\":30000.00}";

        mockMvc.perform(post("/carro/salvar").contentType(MediaType.APPLICATION_JSON).content(carroJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarStatus400AoSalvarCarroSemModelo() throws Exception {
        String carroJson = "{\"marca\":\"Fiat\",\"modelo\":\"\",\"ano\":2021,\"preco\":50000.00}";

        mockMvc.perform(post("/carro/salvar").contentType(MediaType.APPLICATION_JSON).content(carroJson))
                .andExpect(status().isBadRequest());
    }

    // PAR 3: HTTP  - BUSCAR POR ID
    @Test
    @Sql(scripts = "/import-carros.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void deveRetornarStatus200AoBuscarCarroExistente() throws Exception {
        mockMvc.perform(get("/carro/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarStatus404AoBuscarCarroInexistente() throws Exception {
        mockMvc.perform(get("/carro/999"))
                .andExpect(status().isNotFound());
    }

    // PAR 4: HTTP  - ATUALIZAR
    @Test
    @Sql(scripts = "/import-carros.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void deveRetornarStatus200AoAtualizarCarroExistente() throws Exception {
        String carroAtualizadoJson = "{\"marca\":\"Honda\",\"modelo\":\"Civic Novo\",\"ano\":2021,\"preco\":95000.00}";

        mockMvc.perform(put("/carro/1").contentType(MediaType.APPLICATION_JSON).content(carroAtualizadoJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarStatus404AoAtualizarCarroInexistente() throws Exception {
        String carroJson = "{\"marca\":\"Chevrolet\",\"modelo\":\"Onix\",\"ano\":2022,\"preco\":70000.00}";

        mockMvc.perform(put("/carro/999").contentType(MediaType.APPLICATION_JSON).content(carroJson))
                .andExpect(status().isNotFound());
    }

    // PAR 5: HTTP  - EXCLUSÃO
    @Test
    @Sql(scripts = "/import-carros.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void deveRetornarStatus204AoDeletarCarroExistente() throws Exception {
        mockMvc.perform(delete("/carro/2"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deveRetornarStatus404AoDeletarCarroInexistente() throws Exception {
        mockMvc.perform(delete("/carro/999"))
                .andExpect(status().isNotFound());
    }
}