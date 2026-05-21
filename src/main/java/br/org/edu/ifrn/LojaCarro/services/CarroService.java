package br.org.edu.ifrn.LojaCarro.services;

import br.org.edu.ifrn.LojaCarro.model.Carro;
import br.org.edu.ifrn.LojaCarro.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    public CarroRepository carroRepository;
    public Carro save(Carro c) {
        if (c.getPreco() != null && c.getPreco() < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo!");
        }
        if (c.getModelo() == null || c.getModelo().trim().isEmpty()) {
            throw new IllegalArgumentException("O modelo é obrigatório!");
        }
        return carroRepository.save(c);
    }

    public void deleteById(Long id) {
        if (!carroRepository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar um carro inexistente!");
        }
        carroRepository.deleteById(id);
    }

    public Optional<Carro> findById(Long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        if (carro.isEmpty()) {
            throw new RuntimeException("Carro não encontrado!");
        }
        return carro;
    }

    public List<Carro> findAll() {
        return carroRepository.findAll();
    }

    public Carro update(Carro c) {
        if (!carroRepository.existsById(c.getId())) {
            throw new RuntimeException("Não é possível atualizar um carro inexistente!");
        }
        return carroRepository.save(c);
    }
}