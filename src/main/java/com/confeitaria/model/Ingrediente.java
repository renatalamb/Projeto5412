//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.java.com.confeitaria.model;

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
    public LocalDate dataValidade;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static List<Ingrediente> listaIngredientes = new ArrayList();

    //Contrutor
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
        Ingrediente ingredienteExistente = buscarIngrediente(nome);

        if (ingredienteExistente != null) {
            System.out.println("Ingrediente já cadastrado! Atualizando estoque...");

            System.out.println("Quantidade a adicionar ao estoque: ");
            int quantidadeAdicional = sc.nextInt();
            sc.nextLine();

            // Atualiza quantidade do estoque
            ingredienteExistente.quantidade += quantidadeAdicional;
            System.out.println("✅ Estoque atualizado! Nova quantidade: " + ingredienteExistente.quantidade);

        } else {
            //Le quantidade que será adicionada ao estoque
            System.out.println("Quantidade: ");
            int quantidade = sc.nextInt();
            sc.nextLine();

            //Le unidade de medida (kg ou unidade)
            //****** fazer enum
            System.out.println("Unidade de medida (kg, ml ou und): ");
            String unidadeMedida = sc.nextLine();

            //Le quantidade minima necessaria em estoque
            System.out.println("Quantidade mínima em estoque: ");
            int quantMinima = sc.nextInt();
            sc.nextLine();

            //Le data de validade do ingrediente
            System.out.println("Digite a data de validade do ingrediente: ");
            String dataValidade = sc.nextLine();
            LocalDate novadataValidade = LocalDate.parse(dataValidade, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            //Adiciona ingrediente a lista
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

    public int getQuantMinima(int quantidade) {
        return this.quantMinima;
    }

    public DateTimeFormatter getFormatter() {
        return this.formatter;
    }

    // Buscar ingrediente pelo nome
    public static Ingrediente buscarIngrediente(String nome) {
        for (Ingrediente ingrediente : listaIngredientes) {
            if (ingrediente.getNome().equalsIgnoreCase(nome)) {
                return ingrediente;
            }
        }
        return null;
    }


    // Exibir matriz de ingredientes
    public static void listarIngredientes() {
        if (listaIngredientes.isEmpty()) {
            System.out.println("Nenhum ingrediente encontrado.");
            return;
        }

        System.out.println("\n📋 Lista de Ingredientes:");
        System.out.println("------------------------------------------------------");
        System.out.printf("| %-15s | %-10s | %-5s | %-12s |\n", "Nome", "Quantidade", "Unid", "Validade");
        System.out.println("------------------------------------------------------");

        for (Ingrediente ingrediente : listaIngredientes) {
            ingrediente.exibirDados();
        }
        System.out.println("------------------------------------------------------");

        verificarValidade();
    }



    // Verificar ingredientes próximos do vencimento
    public static void verificarValidade() {
        LocalDate hoje = LocalDate.now();
        System.out.println("\n⚠ Ingredientes próximos da validade:");

        boolean temVencimentos = false;
        for (Ingrediente ingrediente : listaIngredientes) {
            if (ingrediente.dataValidade.isBefore(hoje.plusDays(7))) {
                System.out.println("⚠ " + ingrediente.nome + " vence em " + ingrediente.dataValidade.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                temVencimentos = true;
            }
        }

        if (!temVencimentos) {
            System.out.println("✅ Nenhum ingrediente próximo da validade!");
        }
    }


    public Object formatarDataValidade() {
        return DateTimeFormatter; formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }
}
