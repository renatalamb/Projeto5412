//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.java.com.confeitaria.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    // Samara implementou para testar ficha tecnica, se nao de certo tirar
    public Ingrediente(String nome, Double quantidade, String unidade) {
    }


    public void exibirDados() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Quantidade: " + this.quantidade);
        System.out.println("UnidadeMedida: " + this.unidadeMedida);
        System.out.println("QuantMinima: " + this.quantMinima);
        System.out.println("Data de Validade: " + this.dataValidade.format(this.formatter));
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


    public String formatarDataValidade() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataValidade != null ? dataValidade.format(formatter) : "Sem validade";
    }

}
