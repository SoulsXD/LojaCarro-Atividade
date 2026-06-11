package br.org.edu.ifrn.LojaCarro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A marca não pode ser vazia")
    @Size(max = 50, message = "A marca é muito grande")
    @Pattern(regexp = "^[a-zA-Z0-9À-ÿ ]+$", message = "Caracteres especiais não permitidos (Proteção XSS)")
    private String marca;

    @NotBlank(message = "O modelo não pode ser vazio")
    @Size(max = 50, message = "O modelo é muito grande")
    @Pattern(regexp = "^[a-zA-Z0-9À-ÿ ]+$", message = "Caracteres especiais não permitidos (Proteção XSS)")
    private String modelo;

    @NotNull(message = "O ano é obrigatório")
    private Integer ano;

    @NotNull(message = "O preço é obrigatório")
    private Double preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}