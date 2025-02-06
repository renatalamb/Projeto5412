package main.java.com.confeitaria.view;

import main.java.com.confeitaria.model.Bebida;
import main.java.com.confeitaria.repository.BebidaRepository;
import main.java.com.confeitaria.service.BebidaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;


public class BebidaGUI extends JFrame {

    private BebidaService bebidaService;
    private BebidaRepository bebidaRepository;
    private ArrayList<Bebida> bebidas;
    private JTextField nomeField, estoqueField, minimoField, validadeField;
    private JTextArea historicoArea;
    private JTable tabelaBebida;
    private DefaultTableModel tabelaModelo;
    private MenuGUI menuGUI;

    public BebidaGUI() {
        bebidaService = new BebidaService();
        bebidaRepository = new BebidaRepository();
        bebidas = new ArrayList<>();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Gestão de Estoque - Bebidas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(13, 2));

        JLabel nomeLabel = new JLabel("Nome da Bebida:");
        nomeField = new JTextField();

        JLabel estoqueLabel = new JLabel("Quantidade em Estoque:");
        estoqueField = new JTextField();

        JLabel minimoLabel = new JLabel("Quantidade Mínima:");
        minimoField = new JTextField();

        JLabel validadeLabel = new JLabel("Data de Validade:");
        validadeField = new JTextField();

        JButton adicionarBebidaButton = new JButton("Adicionar Bebida");
        JButton atualizarEstoqueButton = new JButton("Atualizar Estoque");
        JButton registrarVendaButton = new JButton("Registrar Venda");
        JButton exibirHistoricoButton = new JButton("Exibir Histórico");
        JButton voltarButton = new JButton("Voltar");

        historicoArea = new JTextArea(10, 30);
        historicoArea.setEditable(false);

        String[] colunas = {"Nome", "Estoque", "Mínimo", "Validade"};
        tabelaModelo = new DefaultTableModel(null, colunas);
        tabelaBebida = new JTable(tabelaModelo);
        JScrollPane tabelaScrollPane = new JScrollPane(tabelaBebida);

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(estoqueLabel);
        panel.add(estoqueField);
        panel.add(minimoLabel);
        panel.add(minimoField);
        panel.add(validadeLabel);
        panel.add(validadeField);
        panel.add(adicionarBebidaButton);
        panel.add(atualizarEstoqueButton);
        panel.add(registrarVendaButton);
        panel.add(exibirHistoricoButton);
        panel.add(tabelaScrollPane);
        panel.add(new JScrollPane(historicoArea));
        panel.add(voltarButton);

        add(panel);

        adicionarBebidaButton.addActionListener(e -> {
            try {
                String nome = nomeField.getText();
                int estoque = Integer.parseInt(estoqueField.getText());
                int minimo = Integer.parseInt(minimoField.getText());

                // Usando o formato de data correto
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate validade = LocalDate.parse(validadeField.getText(), formatter);

                Bebida novaBebida = new Bebida(nome, estoque, minimo, validade);
                bebidas.add(novaBebida);
                atualizarTabela();

                nomeField.setText("");
                estoqueField.setText("");
                minimoField.setText("");
                validadeField.setText("");
                bebidaRepository.salvarBebidasCSV(novaBebida);
                JOptionPane.showMessageDialog(null, "Bebida adicionada e salva com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erro: Certifique-se de inserir números válidos para estoque e quantidade mínima.");
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Erro: Formato de data inválido! Use o formato DD/MM/YYYY.");
            }
        });



        registrarVendaButton.addActionListener(e -> {
            int linhaSelecionada = tabelaBebida.getSelectedRow();
            if (linhaSelecionada != -1) {
                Bebida bebidaSelecionada = bebidas.get(linhaSelecionada);
                try {
                    int quantidadeVendida = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade para venda:"));
                    bebidaService.atualizarEstoqueBebida(bebidaSelecionada, quantidadeVendida, false);
                    atualizarTabela();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Quantidade inválida!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma bebida na tabela.");
            }
        });

        voltarButton.addActionListener(e -> {
            dispose();
            new MenuGUI().setVisible(true);
        });
    }

    private void atualizarTabela() {
        tabelaModelo.setRowCount(0);
        for (Bebida bebida : bebidas) {
            tabelaModelo.addRow(new Object[]{bebida.getNome(), bebida.getQuantidade(), bebida.getQuantidadeMin(), bebida.getDataValidade()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BebidaGUI janela = new BebidaGUI();
            janela.setVisible(true);
        });
    }
}
