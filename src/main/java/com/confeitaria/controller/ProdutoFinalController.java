package main.java.com.confeitaria.controller;

import main.java.com.confeitaria.model.Categoria;
import main.java.com.confeitaria.model.ProdutoFinal;
import main.java.com.confeitaria.service.ProdutoFinalService;

import java.util.List;
import java.util.Scanner;


public class ProdutoFinalController {

    // Método para criar um novo produto
    public static ProdutoFinal criarProduto() {
        Scanner sc = new Scanner(System.in);

        //Chama metodo para escolher categoria
        Categoria categoriaEscolhida = selecionarCategoria();

        //Se categoria for inválida, não cria o produto
        if (categoriaEscolhida == null) {
            return null;
        }

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

        // Cria um novo produto final
        ProdutoFinal produto = new ProdutoFinal(nomeProduto, qtdEstoque, qtdMinima, categoriaEscolhida);

        // Adiciona o produto a lista de produtos da categoria
        categoriaEscolhida.adicionarProduto(produto);

        System.out.println("Produto criado com sucesso!");

        // Chama metodo para lançar o produto no sistema
        ProdutoFinalService.lancarProdutoNoSistema(produto);

        return produto;
    }

    //Método para selecionar uma categoria
    public static Categoria selecionarCategoria() {
        Scanner sc = new Scanner(System.in);

        // Exibe as categorias existentes
        System.out.println("Categorias disponíveis:");
        var categorias = Categoria.getCategorias(); // Obtém a lista de categorias existentes

        // Caso não haja nenhuma categoria criada, não pode criar um produto final
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            return null;
        }

        // Exibe todas as categorias disponíveis
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i).getNome());
        }

        // Solicita ao usuário a escolha da categoria
        System.out.print("Digite o número da categoria desejada: ");
        int escolhaCategoria = sc.nextInt();
        sc.nextLine();

        // Verifica se a escolha é válida
        if (escolhaCategoria < 1 || escolhaCategoria > categorias.size()) {
            System.out.println("Categoria inválida. Produto não será criado.");
            return null;
        }

        // Obtém e retorna a categoria escolhida
        return categorias.get(escolhaCategoria - 1);
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
}
