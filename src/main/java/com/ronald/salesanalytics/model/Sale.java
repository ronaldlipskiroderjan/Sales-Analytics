package com.ronald.salesanalytics.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_vendas")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String produto;
    private Double valor;
    private LocalDate dataVenda;

    public Sale() {}

    // Construtor cheio para facilitar na hora de criar testes ou dados falsos
    public Sale(String produto, Double valor, LocalDate dataVenda) {
        this.produto = produto;
        this.valor = valor;
        this.dataVenda = dataVenda;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProduto() { return produto; }
    public void setProduto(String produto) { this.produto = produto; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public LocalDate getDataVenda() { return dataVenda; }
    public void setDataVenda( LocalDate dataVenda) { this.dataVenda = dataVenda; }
}
