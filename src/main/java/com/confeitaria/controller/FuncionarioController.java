package main.java.com.confeitaria.controller;


import main.java.com.confeitaria.service.FuncionarioService;
import main.java.com.confeitaria.model.Funcionario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FuncionarioController {
    private final FuncionarioService service;
    private final Scanner scanner;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public FuncionarioController() {
        this.service = new FuncionarioService();
        this.scanner = new Scanner(System.in);
    }

    public void cadastrarFuncionario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Morada: ");
        String morada = scanner.nextLine();

        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), FORMATTER);

        System.out.print("Salário: ");
        double salario = scanner.nextDouble();
        scanner.nextLine(); // Consumir quebra de linha

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telemóvel: ");
        int telemovel = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        Funcionario funcionario = new Funcionario(nome, morada, dataNascimento, salario, email, telemovel);
        service.cadastrarFuncionario(funcionario);
    }

    public void listarFuncionarios() {
        for (Funcionario funcionario : service.listarFuncionarios()) {
            funcionario.exibirInformacoes();
        }
    }
}