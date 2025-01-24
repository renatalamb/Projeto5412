package sistema.confeitaria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ingrediente {


    // declaraçao de variaveis * utiliza a classe "private" pq a variavel será acessivel dentro da classe

    private String nome;
    private int quantidade;
    private String unidadeMedida;
    private int quantMinima;
    LocalDate dataValidade;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");


    // Lista para armazenar todos os ingredientes ccriados
    private static List<Ingrediente> listaIngredientes = new ArrayList<Ingrediente>();


    // Construtor
    public Ingrediente(String nome, int quantidade, String unidadeMedida, int quantMinima, LocalDate dataValidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.quantMinima = quantMinima;
        this.dataValidade = dataValidade;
    }

    // Metodo executa tarefa especifica

    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("UnidadeMedida: " + unidadeMedida);
        System.out.println("QuantMinima: " + quantMinima);
        System.out.println("Data de Validade: " + dataValidade.format(formatter));
    }

    public static void criarIngrediente() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do ingrediente: ");
        String nome = sc.nextLine();

        if (verificarNomeExistente(nome)) {
            System.out.println("Já existe um ingrediente com esse nome.");
            return;
        }

        System.out.println("Digite o quantidade do ingrediente: ");
        int quantidade = sc.nextInt();

        System.out.println("Digite o unidade do ingrediente: ");
        String unidadeMedida = sc.nextLine();

        System.out.println("Digite o quantidade mínima do ingrediente: ");
        int quantMinima = sc.nextInt();

        System.out.println("Digite a data de validade do ingrediente: ");
        LocalDate dataValidade = LocalDate.parse(sc.nextLine());

        listaIngredientes.add(new Ingrediente(nome, quantidade, unidadeMedida, quantMinima, dataValidade));

        System.out.println("Ingrediente registado com sucesso!");
    }

    // Criar as funções que permitam acessar os dados do ingrediente
    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public int getQuantMinima() {
        return quantMinima;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    // Criar uma função para  verificar se o nome do ingrediente ja existe

    public static boolean verificarNomeExistente(String nome) {
        for (Ingrediente ingrediente : listaIngredientes) {
            if (ingrediente.getNome().equals(nome)) {
                return true; //nome já existe
            }
        }
        return false; //Nome não existe

    }

    ;


    // MEtodo para listar todos os ingredientes
    public static void listarIngredientes() {
        if (listaIngredientes.isEmpty()) {
            System.out.println("Nenhum ingrediente encontrado");
        } else {
            System.out.println("Lista de ingredientes cadastrados:");
            for (Ingrediente ingrediente : listaIngredientes) {
                ingrediente.exibirDados();
                System.out.println("-----------------------");
            }
        }
    }


    public static void main(String[] args) {
        //Ingrediente novoIngrediente = new Ingrediente("Farinha de Trigo", 2, "KG", 1, LocalDate.of(2025, 9, 20));

        Ingrediente.criarIngrediente();
        Ingrediente.criarIngrediente();
        Ingrediente.criarIngrediente();

        listarIngredientes();

    }


}
