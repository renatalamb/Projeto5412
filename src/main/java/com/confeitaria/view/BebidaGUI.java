package main.java.com.confeitaria.view;

import main.java.com.confeitaria.controller.BebidaController;
import main.java.com.confeitaria.model.Bebida;
import main.java.com.confeitaria.repository.BebidaRepository;
import main.java.com.confeitaria.repository.ProdutoFinalRepository;
import main.java.com.confeitaria.service.BebidaService;
import main.java.com.confeitaria.service.ProdutoFinalService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

class BebidaGUI extends JFrame {
    private BebidaService bebidaService;
    private BebidaRepository bebidaRepository;
    private BebidaController controller;
    private BebidaService service;
    private JTable table;
    private DefaultTableModel tableModel;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String CSV_PATH = "./src/main/java/com/confeitaria/data/bebidas.csv";
    private static final String HISTORICO_PATH = "./src/main/java/com/confeitaria/data/historico_estoque_bebidas.csv";

    public BebidaGUI() {
        controller = new BebidaController(bebidaService, bebidaRepository);

        setTitle("Gestão de Bebidas");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new String[]{"Nome", "Quantidade", "Quantidade Mínima", "Validade"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnAtualizar = new JButton("Atualizar Tabela");
        JButton btnAdicionarEstoque = new JButton("Adicionar ao Estoque");
        JButton btnRetirarEstoque = new JButton("Retirar do Estoque");
        JButton btnHistorico = new JButton("Histórico do Estoque");
        JButton btnVoltar = new JButton("Voltar");

        btnRegistrar.addActionListener(e -> registrarBebida());
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

    private void registrarBebida() {
        try {
            String nome = JOptionPane.showInputDialog("Nome da bebida:");
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade:"));
            String[] unidades = {"ml", "unidade", "gramas"};
            String unidade = (String) JOptionPane.showInputDialog(
                    null, "Escolha a unidade de medida:", "Unidade de Medida",
                    JOptionPane.QUESTION_MESSAGE, null, unidades, unidades[0]
            );
            int quantMinima = Integer.parseInt(JOptionPane.showInputDialog("Quantidade Mínima:"));
            LocalDate validade = LocalDate.parse(JOptionPane.showInputDialog("Data de Validade (dd/MM/yyyy):"), formatter);

            Bebida bebida = new Bebida(nome, quantidade, quantMinima, validade);
            controller.service.adicionarBebida(bebida);
            salvarCSV(bebida);
            atualizarTabela();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar bebida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarCSV(Bebida bebida) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_PATH, true))) {
            writer.write(String.format("%s;%d;%d;%s\n", bebida.getNome(), bebida.getQuantidade(), bebida.getQuantidadeMin(), bebida.getDataValidadeFormatada()));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar no CSV!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarEstoque(boolean adicionar) {
        String nome = JOptionPane.showInputDialog("Nome da bebida:");
        Bebida bebida = controller.service.buscarBebida(nome);

        if (bebida == null) {
            JOptionPane.showMessageDialog(this, "Bebida não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade:"));
            bebida.adicionarQuantidade(adicionar ? quantidade : -quantidade);
            controller.service.atualizarBebidaNoCSV(bebida);
            controller.service.registrarMovimentacao(nome, quantidade, adicionar);
            atualizarTabela();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        controller.service.carregarBebidasDoCSV(CSV_PATH);
        List<Bebida> bebidas = controller.service.listarBebidas();
        for (Bebida beb : bebidas) {
            tableModel.addRow(new Object[]{beb.getNome(), beb.getQuantidade(), beb.getUnidadeMedida(), beb.getQuantMinima(), beb.formatarDataValidade()});
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
        SwingUtilities.invokeLater(() -> new BebidaGUI().setVisible(true));
    }
}
