package br.org.edu.ifrn.LojaCarro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.org.edu.ifrn.LojaCarro.model.UserRole;
import br.org.edu.ifrn.LojaCarro.model.Usuario;
import br.org.edu.ifrn.LojaCarro.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LojaCarroApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaCarroApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
		return args -> {
			if(repository.findByLogin("admin") == null) {
				Usuario admin = new Usuario();
				admin.setLogin("admin");
				admin.setSenha(passwordEncoder.encode("123456"));
				admin.setRole(UserRole.GERENTE);
				repository.save(admin);
			}
		};
	}
}