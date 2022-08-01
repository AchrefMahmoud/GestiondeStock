package com.tn.GestiondeStock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestiondeStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiondeStockApplication.class, args);
	}

}
