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
    private LocalDate data_de_nascimento;
    private double salario;
    private String telemovel;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    //Cria TipoUsuario como um tipo de dado
    public enum Cargo {
        GERENTE, FUNCIONARIO
    }

    // Lista para armazenar todas os usuarios criados
    private static List<Usuario> usuarios = new ArrayList<>();

    // Construtor sem parâmetros
    public Usuario(){
        this.nome = "";
        this.cargo = "";
        this.morada = "";
        this.data_de_nascimento = null;
        this.salario = 0;
        this.telemovel = "";
        this.email = "";
        this.palPasse = "";
    }

    // Contrutor com parâmetros
    public Usuario(String nome, Cargo cargo, String morada, LocalDate data_de_nascimento,
                   double salario, String telemovel, String email, String palPasse) {
        this.nome = nome;
        this.cargo = cargo.name();
        this.morada = morada;
        this.data_de_nascimento = data_de_nascimento;
        this.salario = salario;
        this.telemovel = telemovel;
        this.email = email;
        this.palPasse = palPasse;
    }

    // Métodos Get e Set
    public String getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo.name();
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

    public String getEmail() {
        return email;
    }

    public void setDataDeNascimento(String data) {
        this.data_de_nascimento = LocalDate.parse(data, FORMATTER);
    }

    public LocalDate getDataDeNascimento() {
        return data_de_nascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setData_de_nascimento(LocalDate data_de_nascimento) {
        this.data_de_nascimento = data_de_nascimento;
    }
}