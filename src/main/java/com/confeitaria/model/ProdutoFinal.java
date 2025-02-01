package main.java.com.confeitaria.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutoFinal implements Serializable {
    private static final long serialVersionUID = 1L;

    //Atributos da classe ProdutoFinal
    private String nome;
    private LocalDate dataProducao;
    private int qtdEstoque; //Quantidade atual no estoque
    private int qtdMinima; //Quantidade mínima permitida no estoque
    // Lista para armazenar o histórico de movimentações de estoque
    private List<String> historicoMovimentacoes;

    //Construtor
    public ProdutoFinal(String nome, int qtdEstoque, int qtdMinima) {
        this.nome = nome;
        this.qtdEstoque = qtdEstoque;
        this.qtdMinima = qtdMinima;

        this.historicoMovimentacoes = new ArrayList<>(); // Inicializa o histórico como uma lista vazia
    }


    //Funções getter e setter dos atributos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataProducao() {
        return dataProducao;
    }

    public void setDataProducao(LocalDate dataProducao) {
        this.dataProducao = dataProducao;
    }


    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public <E> List<E> getHistoricoMovimentacoes() {
        return List.of();
    }

    public void setDataProdutocao(LocalDate now) {
    }
}

