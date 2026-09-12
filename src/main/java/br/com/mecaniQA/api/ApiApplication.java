package br.com.mecaniQA.api;

import br.com.mecaniQA.api.model.OrdemServico;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		OrdemServico ordemservico = new OrdemServico();
	}

}
