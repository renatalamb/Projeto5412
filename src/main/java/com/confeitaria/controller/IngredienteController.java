

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
    private final IngredienteService service = new IngredienteService();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

                service.adicionarIngrediente(new Ingrediente(nome, quantidade, unidadeMedida, quantMinima, dataValidade));
                JOptionPane.showMessageDialog(null, "✅ Ingrediente registrado com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: Entrada inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Listar ingredientes
    public void listarIngredientes() {
        List<Ingrediente> ingredientes = service.listarIngredientes();
        if (ingredientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum ingrediente encontrado.");
        } else {
            StringBuilder lista = new StringBuilder("📋 Lista de Ingredientes:\n");
            for (Ingrediente ingrediente : ingredientes) {
                lista.append(String.format("Nome: %s | Quantidade: %d %s | Validade: %s\n",
                        ingrediente.getNome(), ingrediente.getQuantidade(), ingrediente.getUnidadeMedida(), ingrediente.formatarDataValidade()));
            }
            JOptionPane.showMessageDialog(null, lista.toString());
        }
    }

    // Verificar validade dos ingredientes
    public void verificarValidade() {
        List<Ingrediente> vencendo = service.verificarValidade();
        if (vencendo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "✅ Nenhum ingrediente próximo da validade!");
        } else {
            StringBuilder alerta = new StringBuilder("⚠ Ingredientes próximos da validade:\n");
            for (Ingrediente ingrediente : vencendo) {
                alerta.append("⚠ ").append(ingrediente.getNome()).append(" vence em ").append(ingrediente.formatarDataValidade()).append("\n");
            }
            JOptionPane.showMessageDialog(null, alerta.toString(), "Alerta de Validade", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Exportar ingredientes para CSV
    public void exportarIngredientesCSV(String caminhoArquivo) {
        // Verifique se o diretório onde o arquivo será salvo existe
        try {
            File file = new File(caminhoArquivo);
            if (!file.exists()) {
                file.getParentFile().mkdirs();  // Cria o diretório pai, se necessário
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar caminho: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Ingrediente> ingredientes = service.listarIngredientes();

        if (ingredientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "❌ Nenhum ingrediente cadastrado para exportar.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            writer.write("Nome;Quantidade;Unidade;Quantidade Mínima;Data de Validade");
            writer.newLine();

            for (Ingrediente ingrediente : ingredientes) {
                String dataValidade = ingrediente.formatarDataValidade();  // Formatação direta da data usando método do ingrediente
                String linha = String.format("%s;%d;%s;%d;%s",
                        ingrediente.getNome(),
                        ingrediente.getQuantidade(),
                        ingrediente.getUnidadeMedida(),
                        ingrediente.getQuantMinima(),
                        dataValidade
                );
                writer.write(linha);
                writer.newLine();
            }

            JOptionPane.showMessageDialog(null, "✅ Ingredientes exportados com sucesso para: " + caminhoArquivo);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "❌ Erro ao exportar ingredientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}


