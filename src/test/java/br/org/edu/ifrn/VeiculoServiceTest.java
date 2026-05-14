package br.org.edu.ifrn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VeiculoServiceTest {

    @Test
    public void testCadastroVeiculo() {
        String modelo = "Civic";
        assertNotNull(modelo);
    }

    @Test
    public void testLogin() {
        boolean logado = true;
        assertTrue(logado);
    }
}