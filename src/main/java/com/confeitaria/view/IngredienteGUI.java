package main.java.com.confeitaria.view;

import main.java.com.confeitaria.controller.IngredienteController;
import main.java.com.confeitaria.model.Ingrediente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

class IngredienteGUI extends JFrame {
    private IngredienteController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String CSV_PATH = "./src/main/java/com/confeitaria/data/ingredientes.csv";
    private static final String HISTORICO_PATH = "./src/main/java/com/confeitaria/data/historico_estoque_ingredientes.csv";

    public IngredienteGUI() {
        controller = new IngredienteController();

        setTitle("Gestão de Ingredientes");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new String[]{"Nome", "Quantidade", "Unidade", "Mínima", "Validade"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnAtualizar = new JButton("Atualizar Tabela");
        JButton btnAdicionarEstoque = new JButton("Adicionar ao Estoque");
        JButton btnRetirarEstoque = new JButton("Retirar do Estoque");
        JButton btnHistorico = new JButton("Histórico do Estoque");
        JButton btnVoltar = new JButton("Voltar");

        btnRegistrar.addActionListener(e -> registrarIngrediente());
        btnAtualizar.addActionListener(e -> atualizarTabela());
        btnAdicionarEstoque.addActionListener(e -> modificarEstoque(true));
        btnRetirarEstoque.addActionListener(e -> modificarEstoque(false));
        btnHistorico.addActionListener(e -> exibirHistorico());
        btnVoltar.addActionListener(e -> {
            dispose(); // Fecha a janela atual
            new MenuGUI().setVisible(true); // Abre o menu principal
        });

        JPanel panel = new JPanel();
        panel.add(btnRegistrar);
        panel.add(btnAtualizar);
        panel.add(btnAdicionarEstoque);
        panel.add(btnRetirarEstoque);
        panel.add(btnHistorico);
        panel.add(btnVoltar);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        atualizarTabela();
    }

    private void registrarIngrediente() {
        try {
            String nome = JOptionPane.showInputDialog("Nome do ingrediente:");
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade:"));
            String[] unidades = {"ml", "unidade", "gramas"};
            String unidade = (String) JOptionPane.showInputDialog(
                    null, "Escolha a unidade de medida:", "Unidade de Medida",
                    JOptionPane.QUESTION_MESSAGE, null, unidades, unidades[0]
            );
            int quantMinima = Integer.parseInt(JOptionPane.showInputDialog("Quantidade Mínima:"));
            LocalDate validade = LocalDate.parse(JOptionPane.showInputDialog("Data de Validade (dd/MM/yyyy):"), formatter);

            Ingrediente ingrediente = new Ingrediente(nome, quantidade, unidade, quantMinima, validade);
            controller.service.adicionarIngrediente(ingrediente);
            salvarCSV(ingrediente);
            atualizarTabela();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar ingrediente!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarCSV(Ingrediente ingrediente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_PATH, true))) {
            writer.write(String.format("%s;%d;%s;%d;%s\n", ingrediente.getNome(), ingrediente.getQuantidade(), ingrediente.getUnidadeMedida(), ingrediente.getQuantMinima(), ingrediente.formatarDataValidade()));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar no CSV!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarEstoque(boolean adicionar) {
        String nome = JOptionPane.showInputDialog("Nome do ingrediente:");
        Ingrediente ingrediente = controller.service.buscarIngrediente(nome);

        if (ingrediente == null) {
            JOptionPane.showMessageDialog(this, "Ingrediente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade:"));
            ingrediente.adicionarQuantidade(adicionar ? quantidade : -quantidade);
            controller.service.atualizarIngredienteNoCSV(ingrediente);
            controller.service.registrarMovimentacao(nome, quantidade, adicionar);
            atualizarTabela();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        controller.service.carregarIngredientesDoCSV(CSV_PATH);
        List<Ingrediente> ingredientes = controller.service.listarIngredientes();
        for (Ingrediente ing : ingredientes) {
            tableModel.addRow(new Object[]{ing.getNome(), ing.getQuantidade(), ing.getUnidadeMedida(), ing.getQuantMinima(), ing.formatarDataValidade()});
        }
    }

    private void exibirHistorico() {
        StringBuilder historico = new StringBuilder("📜 Histórico de Movimentações:\n");
        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORICO_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                historico.append(linha).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar histórico!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(this, historico.toString(), "Histórico de Estoque", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IngredienteGUI().setVisible(true));
    }
}
