package com.voto.associado;

import com.voto.associado.service.PautaService;
import com.voto.associado.service.VotacaoService;
import com.voto.associado.service.VotoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackageClasses = {PautaService.class, VotacaoService.class, VotoService.class})
@EntityScan(basePackages = {"com.voto.associado.model"},
		basePackageClasses = VotacaoService.class)
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.voto.associado.repository"})
@EnableTransactionManagement
public class AssociadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssociadoApplication.class, args);
	}

}
