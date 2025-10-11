package br.com.vits.orc.vits_agrochain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@SpringBootApplication
@RestController
@Tag(name = "Status", description = "Verificação de status da aplicação")
public class VitsAgrochainApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitsAgrochainApplication.class, args);
	}
	
	@GetMapping("/")
	@Operation(summary = "Status", description = "Verifica se a API está em execução")
	public String index(){
		return "Verdantis API is running";
	}

}
