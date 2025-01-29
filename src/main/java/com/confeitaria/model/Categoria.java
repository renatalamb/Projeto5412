package main.java.com.confeitaria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Categoria {
    private String nome;
    private String descricao;

    // Lista para armazenar todas as categorias criadas
    private static List<Categoria> categorias = new ArrayList<>();

    // Lista de produtos associados à categoria
    private List<ProdutoFinal> produtos;


    //Construtor
    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.produtos = new ArrayList<>(); // Inicializa a lista de produtos
    }


    // Getter para a lista de categorias
    public static List<Categoria> getCategorias() {
        return categorias;
    }

    //Getter para a lista de produtos
    public List<ProdutoFinal> getProdutos() {
        return produtos;
    }

    // Método para associar um produto à categoria
    public void adicionarProduto(ProdutoFinal produto) {
        produtos.add(produto);
    }

    // Método para remover um produto da categoria
    public void removerProduto(ProdutoFinal produto) {
        produtos.remove(produto);
    }

    //Método para exibir categorias
    public void exibirCategoria() {
        System.out.println("Nome: " + nome);
        System.out.println("Descrição: " + descricao);
    }

    //Método para criar nova Categoria e salvar
    public static void criarCategoria() {
        Scanner sc = new Scanner(System.in);

        //Lê nome da categoria
        System.out.print("Nome da categoria: ");
        String nome = sc.nextLine();

        //Verificar se nome já existe
        if (verificarNomeExistente(nome)) {
            System.out.println("Erro: Já existe uma categoria com este mesmo nome.");
            return; //Interrompe a criação
        }

        //Lê descrição da categoria
        System.out.print("Descrição da categoria: ");
        String descricao = sc.nextLine();

        Categoria novaCategoria = new Categoria(nome, descricao);
        categorias.add(novaCategoria);

        System.out.println("Categoria criada com sucesso!");


    }

    //Metodo para veirifcar se a Categoria já existe
    public static boolean verificarNomeExistente(String nome) {
        for (Categoria categoria : categorias) {
            if (categoria.getNome().equalsIgnoreCase(nome)) {
                return true; // Nome já existe
            }
        }
        return false; // Nome não existe
    }


    // Método para listar todas as categorias
    public static void listarCategorias() {
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
        } else {
            System.out.println("Categorias cadastradas:");
            for (Categoria categoria : categorias) {
                categoria.exibirCategoria();
                System.out.println("--------------------");
            }
        }
    }


    public static void editarCategoria(String nomeCategoria, String novoNome, String novaDescricao) {
        Categoria categoria = buscarCategoriaPorNome(nomeCategoria);
        if (categoria != null) {
            categoria.setNome(novoNome);
            categoria.setDescricao(novaDescricao);
            System.out.println("Categoria editada com sucesso!");
        } else {
            System.out.println("Categoria não encontrada!");
        }
    }

    public static void excluirCategoria(String nomeCategoria) {
        Categoria categoria = buscarCategoriaPorNome(nomeCategoria);  // Supondo que você tenha um método de busca
        if (categoria != null) {
            if (categoria.getProdutos().isEmpty()) {
                // A categoria pode ser excluída
                Categoria.getCategorias().remove(categoria);
                System.out.println("Categoria excluída com sucesso!");
            } else {
                // Não é possível excluir a categoria porque ela tem produtos associados
                System.out.println("Erro: Não é possível excluir a categoria porque ela contém produtos associados.");
            }
        } else {
            System.out.println("Categoria não encontrada!");
        }
    }


    public static Categoria buscarCategoriaPorNome(String nome) {
        for (Categoria categoria : Categoria.getCategorias()) {
            if (categoria.getNome().equalsIgnoreCase(nome)) {
                return categoria;
            }
        }
        return null;
    }

    //Obter nome da categoria
    public String getNome() {
        return nome;
    }


    //Obter descrição da categoria
    public String getDescricao() {
        return descricao;
    }

    //Setter dos atributos e lista
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static void setCategorias(List<Categoria> categorias) {
        Categoria.categorias = categorias;
    }
}

