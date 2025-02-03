package main.java.com.confeitaria.controller;

import main.java.com.confeitaria.model.ProdutoFinal;
import main.java.com.confeitaria.repository.ProdutoFinalRepository;
import main.java.com.confeitaria.service.ProdutoFinalService;

import java.util.HashMap;
import java.util.Map;

public class ProdutoFinalController {

    private ProdutoFinalService produtoFinalService;
    private ProdutoFinalRepository produtoFinalRepository;
    private Map<String, ProdutoFinal> produtosMap; // Mapa para armazenar produtos por nome

    // Construtor do Controller com dependências
    public ProdutoFinalController(ProdutoFinalService produtoFinalService, ProdutoFinalRepository produtoFinalRepository) {
        this.produtoFinalService = produtoFinalService;
        this.produtoFinalRepository = produtoFinalRepository;
        this.produtosMap = new HashMap<>(); // Inicializando o mapa de produtos
    }

    // Método para criar um novo produto
    public ProdutoFinal criarProduto(String nomeProduto, int qtdEstoque, int qtdMinima) {
        ProdutoFinal produto = new ProdutoFinal(nomeProduto, qtdEstoque, qtdMinima);
        produtosMap.put(nomeProduto, produto); // Adiciona o produto no mapa
        produtoFinalRepository.salvarProdutoCSV(produto); // Salva o produto no CSV
        System.out.println("Produto criado e salvo com sucesso!");
        return produto;
    }

    // Atualizar estoque e registrar movimentação
    public void atualizarEstoque(String nomeProduto, int quantidade, boolean entrada) {
        ProdutoFinal produto = produtosMap.get(nomeProduto); // Busca o produto pelo nome
        if (produto != null) {
            boolean sucesso = produtoFinalService.atualizarEstoque(produto, quantidade, entrada);
            if (sucesso) {
                produtoFinalRepository.registrarMovimentacaoCSV(produto, (entrada ? "Entrada de " : "Saída de ") + quantidade);
                produtoFinalRepository.salvarProdutosCSV(produtoFinalRepository.carregarProdutosCSV()); // Atualiza os produtos no CSV
            } else {
                System.out.println("Erro: Estoque insuficiente para saída de " + quantidade);
            }
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    // Mostrar histórico de movimentações
    public void mostrarHistorico(String nomeProduto) {
        ProdutoFinal produto = produtosMap.get(nomeProduto); // Busca o produto pelo nome
        if (produto != null) {
            produtoFinalService.exibirHistoricoMovimentacoes(produto);
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    // Mostrar dados do produto
    public void mostrarDados(String nomeProduto) {
        ProdutoFinal produto = produtosMap.get(nomeProduto); // Busca o produto pelo nome
        if (produto != null) {
            produtoFinalService.exibirProduto(produto);
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    // Método para listar todos os produtos cadastrados
    public void listarProdutos() {
        if (produtosMap.isEmpty()) {
            System.out.println("Não há produtos cadastrados.");
        } else {
            System.out.println("Produtos cadastrados:");
            for (ProdutoFinal produto : produtosMap.values()) {
                produtoFinalService.exibirProduto(produto); // Exibe todos os produtos
            }
        }
    }
}
