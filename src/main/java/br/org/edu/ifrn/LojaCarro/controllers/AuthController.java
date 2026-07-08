package br.org.edu.ifrn.LojaCarro.controllers;

import br.org.edu.ifrn.LojaCarro.model.Usuario;
import br.org.edu.ifrn.LojaCarro.repository.UsuarioRepository;
import br.org.edu.ifrn.LojaCarro.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")

public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    public record DadosLogin(String login, String senha) {}
    public record DadosTokenJWT(String token) {}

    // ENDPOINT 1: FAZER LOGIN E GERAR TOKEN (Público)
    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosLogin dados) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
            var authentication = authenticationManager.authenticate(authenticationToken);
            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("Erro de autenticação: " + e.getMessage());
        }
    }

    // ENDPOINT 2: CADASTRAR NOVO USUÁRIO (Apenas GERENTE acessa)
    @PostMapping("/registrar")
    @PreAuthorize("hasAuthority('GERENTE')")
    public ResponseEntity registrar(@RequestBody @Valid Usuario novoUsuario) {
        if (repository.findByLogin(novoUsuario.getLogin()) != null) {
            return ResponseEntity.badRequest().body("Login já existe!");
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}