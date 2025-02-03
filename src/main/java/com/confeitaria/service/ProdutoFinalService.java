package main.java.com.confeitaria.service;

import main.java.com.confeitaria.model.ProdutoFinal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProdutoFinalService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private List<ProdutoFinal> produtos;

    // Construtor
    public ProdutoFinalService() {
        produtos = new ArrayList<>();
    }

    // Adiciona um novo produto à lista
    public void adicionarProduto(ProdutoFinal produto) {
        produtos.add(produto);
    }

    // Busca um produto pelo nome
    public ProdutoFinal buscarProdutoPorNome(String nome) {
        for (ProdutoFinal produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null; // Caso não encontre
    }

    // Atualiza o estoque de um produto
    public boolean atualizarEstoque(ProdutoFinal produto, int quantidade, boolean flag) {
        if (quantidade > 0) {
            produto.setQtdEstoque(produto.getQtdEstoque() + quantidade); // Adiciona ao estoque
            registrarMovimentacao(produto, quantidade, "Entrada");
            verificarLimiteMinimo(produto);
        } else {
            System.out.println("Erro: A quantidade deve ser maior que zero.");
        }
        return flag;
    }

    // Registra uma venda de um produto
    public void registrarVenda(ProdutoFinal produto, int quantidadeVendida) {
        if (quantidadeVendida > 0 && quantidadeVendida <= produto.getQtdEstoque()) {
            produto.setQtdEstoque(produto.getQtdEstoque() - quantidadeVendida);
            registrarMovimentacao(produto, quantidadeVendida, "Saída");
            verificarLimiteMinimo(produto);
        } else {
            System.out.println("Erro: Quantidade inválida ou insuficiente.");
        }
    }

    // Verifica se o estoque do produto está abaixo do mínimo
    private void verificarLimiteMinimo(ProdutoFinal produto) {
        if (produto.getQtdEstoque() < produto.getQtdMinima()) {
            System.out.println("Atenção: Estoque do produto '" + produto.getNome() + "' abaixo do mínimo!");
        }
    }

    // Registra uma movimentação (entrada ou saída)
    private void registrarMovimentacao(ProdutoFinal produto, int quantidade, String tipo) {
        if (produto.getHistoricoMovimentacoes() == null) {
            produto.setHistoricoMovimentacoes(new ArrayList<>());
        }
        String registro = "Tipo: " + tipo + " | Quantidade: " + quantidade + " | Data/Hora: " + LocalDateTime.now();
        produto.getHistoricoMovimentacoes().add(registro);
    }

    // Exibe o histórico de movimentações de um produto
    public void exibirHistoricoMovimentacoes(ProdutoFinal produto) {
        List<String> historico = produto.getHistoricoMovimentacoes();
        if (historico == null || historico.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            for (String movimentacao : historico) {
                System.out.println(movimentacao);
            }
        }
    }

    // Exibe os dados de um produto
    public void exibirProduto(ProdutoFinal produto) {
        System.out.println("Produto: " + produto.getNome());
        System.out.println("Estoque: " + produto.getQtdEstoque());
        System.out.println("Mínimo: " + produto.getQtdMinima());
    }
}