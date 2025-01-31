package main.java.com.confeitaria.controller;

import main.java.com.confeitaria.model.Ingrediente;
import main.java.com.confeitaria.service.IngredienteService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IngredienteController {
    private final IngredienteService service = new IngredienteService();
    private final Scanner scanner = new Scanner(System.in);

    public void criarIngrediente() {
        System.out.println("Digite o nome do ingrediente: ");
        String nome = scanner.nextLine();

        Ingrediente existente = service.buscarIngrediente(nome);
        if (existente != null) {
            System.out.println("Ingrediente já cadastrado! Atualizando estoque...");
            System.out.println("Quantidade a adicionar ao estoque: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();
            existente.getQuantMinima(quantidade);
            System.out.println("✅ Estoque atualizado! Nova quantidade: " + existente.getQuantidade());
        } else {
            System.out.println("Quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Unidade de medida (kg, ml ou und): ");
            String unidadeMedida = scanner.nextLine();

            System.out.println("Quantidade mínima em estoque: ");
            int quantMinima = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Digite a data de validade (dd/MM/yyyy): ");
            String dataValidadeStr = scanner.nextLine();
            LocalDate dataValidade = LocalDate.parse(dataValidadeStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            service.adicionarIngrediente(new Ingrediente(nome, quantidade, unidadeMedida, quantMinima, dataValidade));
            System.out.println("Ingrediente registrado com sucesso!");
        }
    }

    public void listarIngredientes() {
        List<Ingrediente> ingredientes = service.listarIngredientes();
        if (ingredientes.isEmpty()) {
            System.out.println("Nenhum ingrediente encontrado.");
        } else {
            System.out.println("\n📋 Lista de Ingredientes:");
            for (Ingrediente ingrediente : ingredientes) {
                System.out.printf("Nome: %s | Quantidade: %d | Unidade: %s | Validade: %s\n",
                        ingrediente.getNome(), ingrediente.getQuantidade(), ingrediente.getUnidadeMedida(), ingrediente.formatarDataValidade());
            }
        }
    }

    public void verificarValidade() {
        List<Ingrediente> vencendo = service.verificarValidade();
        if (vencendo.isEmpty()) {
            System.out.println("✅ Nenhum ingrediente próximo da validade!");
        } else {
            System.out.println("⚠ Ingredientes próximos da validade:");
            for (Ingrediente ingrediente : vencendo) {
                System.out.println("⚠ " + ingrediente.getNome() + " vence em " + ingrediente.formatarDataValidade());
            }
        }


    }

}
