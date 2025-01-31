package main.java.com.confeitaria.model;

import java.util.ArrayList;
import java.util.List;


public class FichaTecnica {
    // Variaveis
    private String nomeProduto;
    private String nomeIngrediente;
    private int indice;
    private List<Ingrediente> ingredientes;
    private String modoPreparo;
    private int rendimento;
    private String unidade;
    private double quantidade;

    // Construtor
    public FichaTecnica() {
        this.nomeProduto = "";
        this.nomeIngrediente = "";
        this.indice = 0;
        this.ingredientes = new ArrayList<>();
        this.modoPreparo = "";
        this.rendimento = 0;
        this.unidade = "";
        this.quantidade = 0;
    }

    public FichaTecnica(String nomeProduto, String modoPreparo, int rendimento) {
        this.nomeProduto = nomeProduto;
        this.nomeIngrediente = nomeIngrediente;
        this.indice = 0;
        this.ingredientes = new ArrayList<>();
        this.modoPreparo = modoPreparo;
        this.rendimento = rendimento;
        this.unidade = unidade;
        this.quantidade = 0;
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

    public double getQuantidade(double quantidade) {return quantidade;}

    public String getNomeIngrediente() {
        return nomeIngrediente;
    }
    public void adicionarIngrediente(String nome, double quantidade, String unidade) {
    }
}

