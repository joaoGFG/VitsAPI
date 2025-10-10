package br.com.vits.orc.vits_agrochain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VitsAgrochainApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitsAgrochainApplication.class, args);
	}
	
	@GetMapping("/")
	public String index(){
		return "Helloasd asdsasadsa";
	}

}
