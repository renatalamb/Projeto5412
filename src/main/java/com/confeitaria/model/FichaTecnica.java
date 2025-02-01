package main.java.com.confeitaria.model;

import java.util.ArrayList;
import java.util.List;

public class FichaTecnica {
    private String nomeProduto;
    private List<Ingrediente> ingredientes;
    private String modoPreparo;
    private int rendimento;

    public FichaTecnica(String nomeProduto, String modoPreparo, int rendimento) {
        this.nomeProduto = nomeProduto;
        this.ingredientes = new ArrayList<>();
        this.modoPreparo = modoPreparo;
        this.rendimento = rendimento;
    }

    public FichaTecnica() {

    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void adicionarIngrediente(String nome, int quantidade, String unidade) {
        ingredientes.add(new Ingrediente(nome, quantidade, unidade));
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
}