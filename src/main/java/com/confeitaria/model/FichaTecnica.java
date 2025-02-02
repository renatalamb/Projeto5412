package main.java.com.confeitaria.model;

import java.util.ArrayList;
import java.util.List;

public class FichaTecnica {
    private String nomeProduto;
    private String modoPreparo;
    private int rendimento;
    private List<Ingrediente> ingredientes;

    public FichaTecnica() {
        ingredientes = new ArrayList<>();
    }

    public FichaTecnica(String nomeProduto, String modoPreparo, int rendimento) {
        this.nomeProduto = nomeProduto;
        this.modoPreparo = modoPreparo;
        this.rendimento = rendimento;
        ingredientes = new ArrayList<>();
    }

    public void adicionarIngrediente(String nome, double quantidade, String unidade) {
        ingredientes.add(new Ingrediente(nome, quantidade, unidade));
    }

    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(nomeProduto).append(",")
                .append(modoPreparo).append(",")
                .append(rendimento);

        for (Ingrediente ingrediente : ingredientes) {
            sb.append(",").append(ingrediente.toString());
        }
        return sb.toString();
    }

    // Getters e Setters
    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
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

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
