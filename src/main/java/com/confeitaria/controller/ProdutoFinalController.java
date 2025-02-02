package main.java.com.confeitaria.controller;

import main.java.com.confeitaria.model.ProdutoFinal;
import main.java.com.confeitaria.repository.ProdutoFinalRepository;
import main.java.com.confeitaria.service.ProdutoFinalService;


public class ProdutoFinalController {

    private ProdutoFinalService produtoFinalService;
    private ProdutoFinalRepository produtoFinalRepository;

    // Contrutos do Controller com dependências
    public ProdutoFinalController(ProdutoFinalService produtoFinalService, ProdutoFinalRepository produtoFinalRepository) {
        this.produtoFinalService = produtoFinalService;
        this.produtoFinalRepository = produtoFinalRepository;
    }


    // Método para criar um novo produto
    public static ProdutoFinal criarProduto(String nomeProduto, int qtdEstoque, int qtdMinima) {
        ProdutoFinal produto = new ProdutoFinal(nomeProduto, qtdEstoque, qtdMinima);
        produtoFinalRepository.salvarProdutoCSV(produto); // Salva o produto no CSV
        System.out.println("Produto criado e salvo com sucesso!");
        return produto;
    }

    // Atualizar estoque e registrar movimentação
    public void atualizarEstoque(ProdutoFinal produto, int quantidade, boolean entrada) {
        boolean sucesso = produtoFinalService.atualizarEstoque(produto, quantidade, entrada);
        if (sucesso) {
            produtoFinalRepository.registrarMovimentacaoCSV(produto, (entrada ? "Entrada de " : "Saída de ") + quantidade);
            produtoFinalRepository.salvarProdutosCSV(produtoFinalRepository.carregarProdutosCSV()); // Atualiza os produtos no CSV
        } else {
            System.out.println("Erro: Estoque insuficiente para saída de " + quantidade);
        }
    }

    // Mostrar histórico de movimentações
    public static void mostrarHistorico(ProdutoFinal produto) {
        produtoFinalService.mostrarHistorico(produto);
    }

    // Mostrar dados do produto
    public static void mostrarDados(ProdutoFinal produto) {
        produtoFinalService.exibirProduto(produto);
    }

}
