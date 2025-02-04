

package main.java.com.confeitaria.controller;

import main.java.com.confeitaria.model.Ingrediente;
import main.java.com.confeitaria.service.IngredienteService;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class IngredienteController {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public IngredienteService service;  // Referência para o serviço de ingredientes

    public IngredienteController() {
        this.service = new IngredienteService(); // Instanciando o serviço
    }

    // Criar ingrediente via Swing
    public void criarIngrediente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do ingrediente:");
        if (nome == null || nome.trim().isEmpty()) return;

        Ingrediente existente = service.buscarIngrediente(nome);
        if (existente != null) {
            String quantidadeStr = JOptionPane.showInputDialog("Ingrediente já cadastrado!\nQuantidade a adicionar:");
            if (quantidadeStr == null) return;

            try {
                int quantidade = Integer.parseInt(quantidadeStr);
                existente.adicionarQuantidade(quantidade);
                JOptionPane.showMessageDialog(null, "✅ Estoque atualizado! Nova quantidade: " + existente.getQuantidade());
                // Salvar toda a lista no arquivo CSV após atualizar a quantidade
                service.salvarTodosNoCSV();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade:"));
                String unidadeMedida = JOptionPane.showInputDialog("Unidade de medida (kg, ml ou und):");
                int quantMinima = Integer.parseInt(JOptionPane.showInputDialog("Quantidade mínima em estoque:"));
                String dataValidadeStr = JOptionPane.showInputDialog("Digite a data de validade (dd/MM/yyyy):");

                LocalDate dataValidade;
                try {
                    dataValidade = LocalDate.parse(dataValidadeStr, formatter);
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(null, "Erro: Formato de data inválido! Use dd/MM/yyyy", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Adiciona o ingrediente e já salva toda a lista no arquivo CSV
                Ingrediente novoIngrediente = new Ingrediente(nome, quantidade, unidadeMedida, quantMinima, dataValidade);
                service.adicionarIngrediente(novoIngrediente);
                service.salvarTodosNoCSV();  // Salva toda a lista no CSV
                JOptionPane.showMessageDialog(null, "✅ Ingrediente registrado com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: Entrada inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Alteração no método que adiciona ou retira ingredientes
    public void adicionarOuRetirarEstoque(boolean adicionar) {
        String nome = JOptionPane.showInputDialog("Digite o nome do ingrediente:");
        if (nome == null || nome.trim().isEmpty()) return;

        Ingrediente ingrediente = service.buscarIngrediente(nome);
        if (ingrediente != null) {
            String quantidadeStr = JOptionPane.showInputDialog("Quantidade:");
            if (quantidadeStr == null) return;

            try {
                int quantidade = Integer.parseInt(quantidadeStr);
                service.modificarEstoque(nome, quantidade, adicionar);  // Atualiza o estoque no serviço
                service.salvarTodosNoCSV();  // Agora salva corretamente todas as alterações

                // Atualiza a interface gráfica
                service.listarIngredientes();  // Exibe a lista de ingredientes após a modificação

                JOptionPane.showMessageDialog(null, "✅ Estoque atualizado!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrediente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
