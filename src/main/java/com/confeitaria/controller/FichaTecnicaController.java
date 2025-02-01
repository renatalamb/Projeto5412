package main.java.com.confeitaria.controller;

import main.java.com.confeitaria.model.FichaTecnica;
import main.java.com.confeitaria.model.Ingrediente;
import main.java.com.confeitaria.service.FichaTecnicaService;


import java.util.Scanner;

public class FichaTecnicaController {

    private FichaTecnicaService service;
    private Scanner scanner;

    public void FichaTecnicaController() {
        this.service = new FichaTecnicaService();
        this.scanner = new Scanner(System.in);
    }

    public FichaTecnicaController(FichaTecnicaService service) {
        this.service = service;
    }

    public void criarFicha() {
        System.out.print("Nome do Produto: ");
        String nomeProduto = scanner.nextLine();
        FichaTecnica ficha = new FichaTecnica(nomeProduto, "", 0);

        System.out.println("\nAdicione os ingredientes (digite 'fim' para sair):");
        while (true) {
            System.out.print("Nome do Ingrediente: ");
            String nomeIngrediente = scanner.nextLine();
            if (nomeIngrediente.equalsIgnoreCase("fim")) break;

            System.out.print("Quantidade: ");
            double quantidade = scanner.nextDouble();
            scanner.nextLine(); // Consumir quebra de linha

            System.out.print("Unidade de Medida: ");
            String unidade = scanner.nextLine();

            ficha.adicionarIngrediente(nomeIngrediente, (int) quantidade, unidade);
            
        }

        System.out.print("Modo de Preparo: ");
        String modoPreparo = scanner.nextLine();
        ficha.setModoPreparo(modoPreparo);

        System.out.print("Rendimento: ");
        ficha.setRendimento(scanner.nextInt());
        scanner.nextLine(); // Consumir quebra de linha

        service.salvarFicha(ficha);
    }

}
