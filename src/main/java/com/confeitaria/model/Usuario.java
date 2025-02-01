package main.java.com.confeitaria.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    //Atributos
    private String nome;
    private String palPasse;
    private String cargo;
    private String morada;
    private String email;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate data_de_nascimento;
    private double salario;
    private String telemovel;

    //Cria TipoUsuario como um tipo de dado
    public class CargoUsuario {
        enum Cargo {
            GERENTE, FUNCIONARIO
        }
    }

    // Lista para armazenar todas os usuarios criados
    private static List<Usuario> usuarios = new ArrayList<>();

    // Construtor sem parâmetros
    public Usuario(){
        this.nome = "";
        this.palPasse = "";
        this.cargo = "";
        this.morada = "";
        this.data_de_nascimento = null;
        this.salario = 0;
        this.email = "";
        this.telemovel = "";
    }

    // Contrutor com parâmetros
    public Usuario(String nome, String palPasse, CargoUsuario Cargo, String morada,
                   LocalDate data_de_nascimento, double salario, String email, String telemovel) {
        this.nome = nome;
        this.palPasse = palPasse;
        this.cargo = cargo.toString();
        this.morada = morada;
        this.data_de_nascimento = data_de_nascimento;
        this.salario = salario;
        this.email = email;
        this.telemovel = telemovel;
    }

    // Métodos Get e Set
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(List<Usuario> usuarios) {
        Usuario.usuarios = usuarios;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getPalPasse() {
        return palPasse;
    }

    public void setPalPasse(String palPasse) {
        this.palPasse = palPasse;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public String getEmail() {
        return email;
    }

    public void setDataDeNascimento(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data_de_nascimento = LocalDate.parse(data, formatter);
    }

    public LocalDate getDataDeNascimento() {
        return data_de_nascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getData_de_nascimento() {
        return data_de_nascimento;
    }

    public void setData_de_nascimento(LocalDate data_de_nascimento) {
        this.data_de_nascimento = data_de_nascimento;
    }
}