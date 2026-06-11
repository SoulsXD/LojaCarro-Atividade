package br.org.edu.ifrn.LojaCarro.controllers;

import br.org.edu.ifrn.LojaCarro.model.Carro;
import br.org.edu.ifrn.LojaCarro.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @PostMapping("salvar")
    @PreAuthorize("hasAnyAuthority('GERENTE', 'VENDEDOR')")
    public ResponseEntity<?> salvarCarro(@RequestBody @Valid Carro c) {
        try {
            Carro savedCarro = carroService.save(c);
            return ResponseEntity.ok(savedCarro); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage()); // 400 Bad Request
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<?> atualizarCarro(@PathVariable Long id, @RequestBody Carro c) {
        try {
            c.setId(id);
            Carro updatedCarro = carroService.update(c);
            return ResponseEntity.ok(updatedCarro); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity<?> deletarCarro(@PathVariable Long id) {
        try {
            carroService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content (Deu certo)
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> pesquisarCarroPorId(@PathVariable Long id) {
        try {
            Optional<Carro> carro = carroService.findById(id);
            return ResponseEntity.ok(carro.get()); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @GetMapping
    public ResponseEntity<List<Carro>> pesquisarTodosCarros() {
        List<Carro> carros = carroService.findAll();
        return ResponseEntity.ok(carros);
    }
}