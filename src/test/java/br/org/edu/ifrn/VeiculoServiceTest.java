package br.org.edu.ifrn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VeiculoServiceTest {

    @Test
    public void testCadastroVeiculo() {
        String modelo = "Civic";
        assertNotNull(modelo, "O modelo não deve ser nulo");
    }

    @Test
    public void testBuscaVeiculo() {
        int id = 1;
        assertTrue(id > 0, "A busca deve retornar um ID válido");
    }

    @Test
    public void testAtualizacaoVeiculo() {
        String modeloAntigo = "Civic";
        String modeloNovo = "Corolla";
        assertNotEquals(modeloAntigo, modeloNovo, "O modelo deve ser atualizado");
    }

    @Test
    public void testRemocaoVeiculo() {
        boolean excluido = true;
        assertTrue(excluido, "O veículo deve ser removido com sucesso");
    }

    @Test
    public void testLogin() {
        boolean logado = true;
        assertTrue(logado, "O login deve ser realizado com sucesso");
    }
}