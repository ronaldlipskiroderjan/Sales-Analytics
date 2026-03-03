package com.ronald.salesanalytics.config;

import com.ronald.salesanalytics.model.Sale;
import com.ronald.salesanalytics.repository.SaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class TestConfig implements CommandLineRunner {

    private final SaleRepository repository;

    public TestConfig(SaleRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        Sale v1 = new Sale("Notebook Gamer", 4500.0, LocalDate.now());
        Sale v2 = new Sale("Mouse Sem Fio", 150.0, LocalDate.now());
        Sale v3 = new Sale("Teclado Mecânico", 350.0, LocalDate.now());
        Sale v4 = new Sale("Monitor 24pol", 900.0, LocalDate.now());

        repository.saveAll(Arrays.asList(v1, v2, v3, v4));

        System.out.println("--- DADOS DE TESTE INSERIDOS COM SUCESSO ---");
    }
}
