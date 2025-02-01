package main.java.com.confeitaria.controller;

import main.java.com.confeitaria.model.ProdutoFinal;
import main.java.com.confeitaria.repository.ProdutoFinalRepository;
import main.java.com.confeitaria.service.ProdutoFinalService;

import java.util.List;
import java.util.Scanner;


public class ProdutoFinalController {

    private static ProdutoFinal produto;

    // Método para criar um novo produto
    public static ProdutoFinal criarProduto() {
        Scanner sc = new Scanner(System.in);


        // Lê nome do produto
        System.out.print("Nome do produto: ");
        String nomeProduto = sc.nextLine();

        // Lê quantidade atual no estoque
        System.out.print("Quantidade em estoque: ");
        int qtdEstoque = sc.nextInt();

        // Lê quantidade mínima exigida no estoque
        System.out.print("Quantidade mínima: ");
        int qtdMinima = sc.nextInt();
        sc.nextLine();

        System.out.println("Produto criado com sucesso!");

        // Chama metodo para lançar o produto no sistema
        ProdutoFinalService.lancarProdutoNoSistema(produto);

        return produto;
    }


    public static void registrarProduto(ProdutoFinal produto) {
        ProdutoFinalService.lancarProdutoNoSistema(produto);
    }

    public static void mostrarHistorico(ProdutoFinal produto) {
        ProdutoFinalService.exibirHistoricoMovimentacoes(produto);
    }

    public static void mostrarDados(ProdutoFinal produto) {
        ProdutoFinalService.exibirDados(produto);
    }

    public static void salvarProdutosCSV(List<ProdutoFinal> produtos) {
        ProdutoFinalRepository.exportarParaCSV(produtos);
    }

    public static List<ProdutoFinal> carregarProdutosCSV() {
        return ProdutoFinalRepository.importarDeCSV();
    }

    public static void salvarProdutosBinario(List<ProdutoFinal> produtos) {
        ProdutoFinalRepository.exportarParaBinario(produtos);
    }

    public static List<ProdutoFinal> carregarProdutosBinario() {
        return ProdutoFinalRepository.importarDeBinario();
    }
}
