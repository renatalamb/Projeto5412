package sistema.confeitaria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Categoria {
    private String nome;
    private String descricao;

    // Lista para armazenar todas as categorias criadas
    private static List<Categoria> categorias = new ArrayList<>();


    //Construtor
    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    //Obter nome da categoria
    public String obterNome() {
        return nome;
    }


    //Obter descrição da categoria
    public String obterDescricao() {
        return descricao;
    }

    //Método
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
            if (categoria.obterNome().equalsIgnoreCase(nome)) {
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


    public static void main(String[] args) {
        //Testando método exibirCategoria
        Categoria.criarCategoria();
        Categoria.criarCategoria();
        Categoria.criarCategoria();

        listarCategorias();


    }
}

