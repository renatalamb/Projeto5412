package main.java.com.confeitaria.view;


import main.java.com.confeitaria.model.ProdutoFinal;
import main.java.com.confeitaria.repository.ProdutoFinalRepository;
import main.java.com.confeitaria.service.ProdutoFinalService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProdutoFinalGUI extends JFrame {

    private ProdutoFinalService produtoService;
    private ProdutoFinalRepository produtoRepository; // Adicionando o repositório
    private ArrayList<ProdutoFinal> produtos;
    private JTextField nomeField, estoqueField, minimoField;
    private JTextArea historicoArea;
    private JTable tabelaProduto;
    private DefaultTableModel tabelaModelo;
    private MenuGUI menuGUI;

    public ProdutoFinalGUI() {
        produtoService = new ProdutoFinalService();
        produtoRepository = new ProdutoFinalRepository(); // Inicializando o repositório
        produtos = new ArrayList<>();  // Lista de produtos
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Gestão de Estoque - Produto Final");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(13, 2));

        JLabel nomeLabel = new JLabel("Nome do Produto:");
        nomeField = new JTextField();

        JLabel estoqueLabel = new JLabel("Quantidade em Estoque:");
        estoqueField = new JTextField();

        JLabel minimoLabel = new JLabel("Quantidade Mínima:");
        minimoField = new JTextField();

        JButton adicionarProdutoButton = new JButton("Adicionar Produto");
        JButton atualizarEstoqueButton = new JButton("Atualizar Estoque");
        JButton registrarVendaButton = new JButton("Registrar Venda");
        JButton exibirHistoricoButton = new JButton("Exibir Histórico");
        JButton voltarButton = new JButton("Voltar");


        historicoArea = new JTextArea(10, 30);
        historicoArea.setEditable(false);

        String[] colunas = {"Nome", "Estoque", "Mínimo"};
        tabelaModelo = new DefaultTableModel(null, colunas);
        tabelaProduto = new JTable(tabelaModelo);
        JScrollPane tabelaScrollPane = new JScrollPane(tabelaProduto);

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(estoqueLabel);
        panel.add(estoqueField);
        panel.add(minimoLabel);
        panel.add(minimoField);
        panel.add(adicionarProdutoButton);
        panel.add(atualizarEstoqueButton);
        panel.add(registrarVendaButton);
        panel.add(exibirHistoricoButton);
        panel.add(tabelaScrollPane);
        panel.add(new JScrollPane(historicoArea));
        panel.add(voltarButton);

        add(panel);

        adicionarProdutoButton.addActionListener(e -> {
            String nome = nomeField.getText();
            int estoque = Integer.parseInt(estoqueField.getText());
            int minimo = Integer.parseInt(minimoField.getText());

            ProdutoFinal novoProduto = new ProdutoFinal(nome, estoque, minimo);
            produtos.add(novoProduto);
            atualizarTabela();

            nomeField.setText("");
            estoqueField.setText("");
            minimoField.setText("");
            produtoRepository.exportarParaCSV(produtos);
            JOptionPane.showMessageDialog(null, "Dados exportados para CSV com sucesso!");

            JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
        });

        atualizarEstoqueButton.addActionListener(e -> {
            int linhaSelecionada = tabelaProduto.getSelectedRow();
            if (linhaSelecionada != -1) {
                ProdutoFinal produtoSelecionado = produtos.get(linhaSelecionada);
                try {
                    int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade para entrada no estoque:"));
                    produtoService.atualizarEstoque(produtoSelecionado, quantidade, true);
                    estoqueField.setText(String.valueOf(produtoSelecionado.getQtdEstoque()));
                    atualizarTabela();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Quantidade inválida!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um produto na tabela.");
            }
        });

        registrarVendaButton.addActionListener(e -> {
            int linhaSelecionada = tabelaProduto.getSelectedRow();
            if (linhaSelecionada != -1) {
                ProdutoFinal produtoSelecionado = produtos.get(linhaSelecionada);
                try {
                    int quantidadeVendida = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade para venda:"));
                    produtoService.registrarVenda(produtoSelecionado, quantidadeVendida);
                    estoqueField.setText(String.valueOf(produtoSelecionado.getQtdEstoque()));
                    atualizarTabela();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Quantidade inválida!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um produto na tabela.");
            }
        });

        exibirHistoricoButton.addActionListener(e -> {
            int linhaSelecionada = tabelaProduto.getSelectedRow();
            if (linhaSelecionada != -1) {
                ProdutoFinal produtoSelecionado = produtos.get(linhaSelecionada);
                produtoService.exibirHistoricoMovimentacoes(produtoSelecionado);
                historicoArea.setText("");
                for (String movimentacao : produtoSelecionado.getHistoricoMovimentacoes()) {
                    historicoArea.append(movimentacao + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um produto na tabela.");
            }
        });

     voltarButton.addActionListener(e -> {
            dispose(); // Fecha a janela atual
            new MenuGUI().setVisible(true); // Abre o menu principal
        });
     

    }

    private void atualizarTabela() {
        tabelaModelo.setRowCount(0);
        for (ProdutoFinal produto : produtos) {
            tabelaModelo.addRow(new Object[]{produto.getNome(), produto.getQtdEstoque(), produto.getQtdMinima()});
        }
    }
    // Testando
    public ProdutoFinalGUI(MenuGUI menuGUI) {
        this.menuGUI = menuGUI;
        setTitle("Cadastro de Produto Final");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Cadastro de Produto Final", SwingConstants.CENTER);
        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela atual
                menuGUI.setVisible(true); // Volta ao MenuGUI
            }
        });

        panel.add(label, BorderLayout.CENTER);
        panel.add(btnVoltar, BorderLayout.SOUTH);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProdutoFinalGUI janela = new ProdutoFinalGUI();
            janela.setVisible(true);
        });
    }
}