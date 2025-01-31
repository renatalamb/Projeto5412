package main.java.com.confeitaria.service;

import main.java.com.confeitaria.model.FichaTecnica;
import main.java.com.confeitaria.model.Ingrediente;

import java.util.ArrayList;
import java.util.List;



public class FichaTecnicaService {
    private static List<FichaTecnica> fichasTecnicas = new ArrayList<>();

    public void salvarFicha(FichaTecnica ficha) {
        if (validarFicha(ficha)) {
            fichasTecnicas.add(ficha);
            System.out.println("Ficha Técnica cadastrada com sucesso!");
        } else {
            System.out.println("Erro: Todos os campos obrigatórios devem ser preenchidos!");
        }
    }

    private boolean validarFicha(FichaTecnica ficha) {
        return ficha.getNomeProduto() != null && !ficha.getNomeProduto().isEmpty()
                && !ficha.getIngredientes().isEmpty()
                && ficha.getModoPreparo() != null && !ficha.getModoPreparo().isEmpty()
                && ficha.getRendimento() > 0;
    }

    public void imprimirFicha(FichaTecnica ficha) {
        System.out.println("\nFicha Técnica: " + ficha.getNomeProduto());
        System.out.println("Ingredientes:");
        for (Ingrediente ingrediente : ficha.getIngredientes()) {
            System.out.println("- " + ingrediente.getNome() + ": " + ingrediente.getQuantidade() + " " + ingrediente.getUnidadeMedida()); // Puxa de ingrediente, ver no codigo da mary
        }
        System.out.println("Modo de Preparo: " + ficha.getModoPreparo());
        System.out.println("Rendimento: " + ficha.getRendimento());
    }
}
