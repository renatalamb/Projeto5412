package main.java.com.confeitaria.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario {

    private String nome;
    private String morada;
    private String email;
    private LocalDate dataDeNascimento;
    private double salario;
    private int telemovel;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Construtor
    public Funcionario(String nome, String morada, LocalDate dataDeNascimento, double salario, String email, int telemovel) {
        this.nome = nome;
        this.morada = morada;
        this.dataDeNascimento = dataDeNascimento;
        this.salario = salario;
        this.email = email;
        this.telemovel = telemovel;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMorada() { return morada; }
    public void setMorada(String morada) { this.morada = morada; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getDataDeNascimento() { return dataDeNascimento; }
    public void setDataDeNascimento(LocalDate dataDeNascimento) { this.dataDeNascimento = dataDeNascimento; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public int getTelemovel() { return telemovel; }
    public void setTelemovel(int telemovel) { this.telemovel = telemovel; }

    // Método para exibir informações
    public void exibirInformacoes() {
        System.out.println("\n--- Informações do Funcionário ---");
        System.out.println("Nome: " + nome);
        System.out.println("Morada: " + morada);
        System.out.println("Data de Nascimento: " + dataDeNascimento.format(FORMATTER));
        System.out.println("Salário: " + salario);
        System.out.println("Email: " + email);
        System.out.println("Telemóvel: " + telemovel);
    }
}