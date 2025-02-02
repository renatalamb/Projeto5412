package main.java.com.confeitaria.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProdutoFinal implements Serializable{
    private static final long serialVersionUID = 1L;

    //Atributos
    private String nome;
    private LocalDate dataProducao;
    private int qtdEstoque; //Quantidade atual no estoque
    private int qtdMinima; //Quantidade mínima permitida no estoque
    // Lista para armazenar o histórico de movimentações de estoque
    private List<String> historicoMovimentacoes;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Construtor principal
    public ProdutoFinal(String nome, int qtdEstoque, int qtdMinima) {
        this.nome = nome;
        this.qtdEstoque = qtdEstoque;
        this.qtdMinima = qtdMinima;
        this.historicoMovimentacoes = new ArrayList<>(); // Inicializa o histórico como uma lista vazia
        this.dataProducao = LocalDate.now(); // Definir data de produção
    }

    // Contrutor se parâmetros
    public ProdutoFinal() {
        this("", 0, 0);
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

    public List<String> getHistoricoMovimentacoes() {
        return new ArrayList<>(historicoMovimentacoes);
    }

    public void setHistoricoMovimentacoes(List<String> historicoMovimentacoes) {
        this.historicoMovimentacoes = new ArrayList<>(historicoMovimentacoes);
    }

}

