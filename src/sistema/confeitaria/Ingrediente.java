//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package sistema.confeitaria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ingrediente {
    private String nome;
    private int quantidade;
    private String unidadeMedida;
    private int quantMinima;
    private LocalDate dataValidade;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static List<Ingrediente> listaIngredientes = new ArrayList();

    public Ingrediente(String nome, int quantidade, String unidadeMedida, int quantMinima, LocalDate dataValidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.quantMinima = quantMinima;
        this.dataValidade = dataValidade;
    }

    public void exibirDados() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Quantidade: " + this.quantidade);
        System.out.println("UnidadeMedida: " + this.unidadeMedida);
        System.out.println("QuantMinima: " + this.quantMinima);
        System.out.println("Data de Validade: " + this.dataValidade.format(this.formatter));
    }

    public static void criarIngrediente() {
        Scanner sc = new Scanner(System.in);

        //Le nome do ingrediente
        System.out.println("Digite o nome do ingrediente: ");
        String nome = sc.nextLine();

        //Verifica se existe ingrediente registado com este nome
        if (verificarNomeExistente(nome)) {
            System.out.println("Já existe um ingrediente com esse nome.");
            //Caso nao exista continua com os outros dados
        } else {
            //Le quantidade que será adicionada ao estoque
            System.out.println("Digite o quantidade do ingrediente: ");
            int quantidade = sc.nextInt();
            sc.nextLine();

            //Le unidade de medida (kg ou unidade)
            //****** fazer enum
            System.out.println("Digite o unidade do ingrediente: ");
            String unidadeMedida = sc.nextLine();

            //Le quantidade minima necessaria em estoque
            System.out.println("Digite o quantidade mínima do ingrediente: ");
            int quantMinima = sc.nextInt();
            sc.nextLine();

            //Le data de validade do ingrediente
            System.out.println("Digite a data de validade do ingrediente: ");
            String dataValidade = sc.nextLine();
            LocalDate novadataValidade = LocalDate.parse(dataValidade, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            listaIngredientes.add(new Ingrediente(nome, quantidade, unidadeMedida, quantMinima, novadataValidade));
            System.out.println("Ingrediente registado com sucesso!");
        }
    }

    public String getNome() {
        return this.nome;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public String getUnidadeMedida() {
        return this.unidadeMedida;
    }

    public int getQuantMinima() {
        return this.quantMinima;
    }

    public DateTimeFormatter getFormatter() {
        return this.formatter;
    }

    public static boolean verificarNomeExistente(String nome) {
        for(Ingrediente ingrediente : listaIngredientes) {
            if (ingrediente.getNome().equals(nome)) {
                return true;
            }
        }

        return false;
    }

    public static void listarIngredientes() {
        if (listaIngredientes.isEmpty()) {
            System.out.println("Nenhum ingrediente encontrado");
        } else {
            System.out.println("Lista de ingredientes cadastrados:");

            for(Ingrediente ingrediente : listaIngredientes) {
                ingrediente.exibirDados();
                System.out.println("-----------------------");
            }
        }

    }

    public static void main(String[] args) {
        criarIngrediente();
        criarIngrediente();
        criarIngrediente();
        listarIngredientes();
    }
}
