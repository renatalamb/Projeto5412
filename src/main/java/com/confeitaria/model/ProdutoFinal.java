package main.java.com.confeitaria.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProdutoFinal implements Serializable {
    private static final long serialVersionUID = 1L;

    // Atributos
    private String nome;
    private LocalDate dataProducao;
    private int qtdEstoque; // Quantidade atual no estoque
    private int qtdMinima; // Quantidade mínima permitida no estoque
    private List<String> historicoMovimentacoes; // Histórico de movimentações

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Construtor principal
    public ProdutoFinal(String nome, int qtdEstoque, int qtdMinima) {
        this.nome = nome;
        this.qtdEstoque = qtdEstoque;
        this.qtdMinima = qtdMinima;
        this.historicoMovimentacoes = new ArrayList<>(); // Inicializa o histórico como uma lista vazia
        this.dataProducao = LocalDate.now(); // Definir data de produção
    }

    // Construtor vazio
    public ProdutoFinal() {
        this("", 0, 0);
    }

    // Funções getter e setter
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
        return historicoMovimentacoes; // Retorna diretamente a lista de movimentações
    }

    public void setHistoricoMovimentacoes(List<String> historicoMovimentacoes) {
        this.historicoMovimentacoes = historicoMovimentacoes;
    }

    // Método para adicionar uma movimentação no histórico
    public void adicionarMovimentacao(String movimentacao) {
        historicoMovimentacoes.add(movimentacao); // Adiciona o registro de movimentação
    }

    // Formatação da data de produção para exibição
    public String getDataProducaoFormatada() {
        return dataProducao.format(FORMATTER); // Retorna a data de produção formatada
    }

    // Exibe o histórico de movimentações de forma legível
    public void exibirHistorico() {
        if (historicoMovimentacoes.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            System.out.println("Histórico de movimentações do produto '" + nome + "':");
            for (String movimentacao : historicoMovimentacoes) {
                System.out.println(movimentacao);
            }
        }
    }

    @Override
    public String toString() {
        return "Produto: " + nome + ", Estoque: " + qtdEstoque + ", Mínimo: " + qtdMinima;
    }
}
