package br.org.edu.ifrn.services;

import br.org.edu.ifrn.LojaCarro.model.Carro;
import br.org.edu.ifrn.LojaCarro.services.CarroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = br.org.edu.ifrn.LojaCarro.LojaCarroApplication.class)
@ActiveProfiles("test")
public class VeiculoServiceTest {

    @Autowired
    private CarroService carroService;
    // PAR 1: VALIDAÇÃO DE PREÇO
    @Test
    public void deveSalvarCarroComPrecoValido() {
        Carro carro = new Carro();
        carro.setMarca("Ford");
        carro.setModelo("Fiesta");
        carro.setAno(2020);
        carro.setPreco(50000.00);

        Carro salvo = carroService.save(carro);
        assertNotNull(salvo.getId());
    }

    @Test
    public void deveLancarIllegalArgumentExceptionAoSalvarPrecoNegativo() {
        Carro carro = new Carro();
        carro.setMarca("Ford");
        carro.setModelo("Fiesta");
        carro.setAno(2020);
        carro.setPreco(-500.00);

        assertThrows(IllegalArgumentException.class, () -> {
            carroService.save(carro);
        });
    }

    // PAR 2: VALIDAÇÃO DE MODELO
    @Test
    public void deveSalvarCarroComModeloPreenchido() {
        Carro carro = new Carro();
        carro.setMarca("Fiat");
        carro.setModelo("Uno");
        carro.setAno(2019);
        carro.setPreco(30000.00);

        Carro salvo = carroService.save(carro);
        assertNotNull(salvo.getId());
    }

    @Test
    public void deveLancarIllegalArgumentExceptionAoSalvarSemModelo() {
        Carro carro = new Carro();
        carro.setMarca("Fiat");
        carro.setModelo("");
        carro.setAno(2021);
        carro.setPreco(50000.00);

        assertThrows(IllegalArgumentException.class, () -> {
            carroService.save(carro);
        });
    }
    // PAR 3: BUSCAR POR ID
    @Test
    public void deveBuscarCarroComSucessoQuandoIdExistir() {
        Carro carro = new Carro();
        carro.setMarca("Honda");
        carro.setModelo("Civic");
        carro.setAno(2020);
        carro.setPreco(90000.00);
        Carro salvo = carroService.save(carro);

        Optional<Carro> encontrado = carroService.findById(salvo.getId());
        assertTrue(encontrado.isPresent());
    }

    @Test
    public void deveLancarRuntimeExceptionAoBuscarCarroComIdInexistente() {
        assertThrows(RuntimeException.class, () -> {
            carroService.findById(999L);
        });
    }

    // PAR 4: ATUALIZAR
    @Test
    public void deveAtualizarCarroComSucessoQuandoIdExistir() {
        Carro carro = new Carro();
        carro.setMarca("Toyota");
        carro.setModelo("Corolla");
        carro.setAno(2021);
        carro.setPreco(120000.00);
        Carro salvo = carroService.save(carro);

        salvo.setModelo("Corolla XEI");
        Carro atualizado = carroService.update(salvo);
        assertEquals("Corolla XEI", atualizado.getModelo());
    }

    @Test
    public void deveLancarRuntimeExceptionAoAtualizarCarroInexistente() {
        Carro carroInexistente = new Carro();
        carroInexistente.setId(999L);
        carroInexistente.setMarca("Chevrolet");
        carroInexistente.setModelo("Onix");

        assertThrows(RuntimeException.class, () -> {
            carroService.update(carroInexistente);
        });
    }

    // PAR 5: EXCLUSÃO
    @Test
    public void deveDeletarCarroComSucessoQuandoIdExistir() {
        Carro carro = new Carro();
        carro.setMarca("Chevrolet");
        carro.setModelo("Onix");
        carro.setAno(2022);
        carro.setPreco(70000.00);
        Carro salvo = carroService.save(carro);

        assertDoesNotThrow(() -> {
            carroService.deleteById(salvo.getId());
        });
    }

    @Test
    public void deveLancarRuntimeExceptionAoDeletarCarroInexistente() {
        assertThrows(RuntimeException.class, () -> {
            carroService.deleteById(999L);
        });
    }
}