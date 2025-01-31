package main.java.com.confeitaria.service;

import main.java.com.confeitaria.model.Ingrediente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class IngredienteService {

    private static final List<Ingrediente> listaIngredientes = new ArrayList<>();

    public void adicionarIngrediente(Ingrediente ingrediente) {
        Ingrediente existente = buscarIngrediente(ingrediente.getNome());
        if (existente != null) {
            existente.getQuantMinima(ingrediente.getQuantidade());
        } else {
            listaIngredientes.add(ingrediente);
        }
    }



    public Ingrediente buscarIngrediente(String nome) {
        return listaIngredientes.stream()
                .filter(i -> i.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public List<Ingrediente> listarIngredientes() {
        return new ArrayList<>(listaIngredientes);
    }


    // Verificar ingredientes próximos do vencimento
    public static List<Ingrediente> verificarValidade() {
        LocalDate hoje = LocalDate.now();
        System.out.println("\n⚠ Ingredientes próximos da validade:");

        boolean temVencimentos = false;
        for (Ingrediente ingrediente : listaIngredientes) {
            if (ingrediente.dataValidade.isBefore(hoje.plusDays(7))) {
                System.out.println("⚠ " + ingrediente.getNome() + " vence em " + ingrediente.dataValidade.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                temVencimentos = true;
            }
        }

        if (!temVencimentos) {
            System.out.println("✅ Nenhum ingrediente próximo da validade!");
        }
        return null;
    }
}
