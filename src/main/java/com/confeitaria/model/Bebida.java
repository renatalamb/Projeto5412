package main.java.com.confeitaria.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Bebida {

    // Atributos
    private String nome;
    private int quantidade;
    private LocalDate dataValidade;
    private int quantidadeMin; // Quantidade mínima permitida no estoque
    private List<String> historicoMovimentacoesBebidas; // Histórico de movimentações

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Contrutor
    public Bebida(String nome, int quantidade,  int quantidadeMin, LocalDate dataValidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.quantidadeMin = quantidadeMin;
        this.dataValidade = dataValidade;
        this.historicoMovimentacoesBebidas = new ArrayList<>(); // Inicia histórico com uma lista vazia
    }

    // Contrutor vazio
    public Bebida() {
        this("", 0, 0, LocalDate.now());
    }

    // Funções getter e setter

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Formatação da data de produção para exibição
    public String getDataValidadeFormatada() {
        return dataValidade.format(FORMATTER); // Retorna a data de produção formatada
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public List<String> getHistoricoMovimentacoesBebidas() {
        return historicoMovimentacoesBebidas; // Retorna diretamente a lista de movimentações
    }

    public void setHistoricoMovimentacoesBebidas(List<String> historicoMovimentacoesBebidas) {
        this.historicoMovimentacoesBebidas = historicoMovimentacoesBebidas;
    }

    // Método para adicionar uma movimentação no histórico
    public void adicionarMovimentacao(String movimentacao) {
        historicoMovimentacoesBebidas.add(movimentacao); // Adiciona o registro de movimentação
    }

    // Exibe o histórico de movimentações de forma legível
    public void exibirHistoricoBebidas() {
        if (historicoMovimentacoesBebidas.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            System.out.println("Histórico de movimentações do produto '" + nome + "':");
            for (String movimentacao : historicoMovimentacoesBebidas) {
                System.out.println(movimentacao);
            }
        }
    }

    public int getQuantidadeMin() {
        return quantidadeMin;
    }

    public void setQuantidadeMin(int quantidadeMin) {
        this.quantidadeMin = quantidadeMin;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void adicionarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }
}
