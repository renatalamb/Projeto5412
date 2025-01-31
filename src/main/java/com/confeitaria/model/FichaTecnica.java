package main.java.com.confeitaria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FichaTecnica {
    // Variaveis
    private String nomeProduto;
    private int indice;
    private List<Ingrediente> ingredientes;
    private String modoPreparo;
    private int rendimento;
    private String unidade;

    // Construtor
    public FichaTecnica() {
        this.nomeProduto = "";
        this.indice = 0;
        this.ingredientes = new ArrayList<>();
        this.modoPreparo = "";
        this.rendimento = 0;
        this.unidade = "";
    }

    public FichaTecnica(String nomeProduto, String modoPreparo, int rendimento) {
        this.nomeProduto = nomeProduto;
        this.indice = 0;
        this.ingredientes = new ArrayList<>();
        this.modoPreparo = modoPreparo;
        this.rendimento = rendimento;
        this.unidade = unidade;
    }

    // Getters e Setters
    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getIndice() {
        return indice;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public int getRendimento() {
        return rendimento;
    }

    public void setRendimento(int rendimento) {
        this.rendimento = rendimento;
    }

    public void adicionarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    public void getUnidade(String unidade) {
        this.unidade = unidade;
    }

}

