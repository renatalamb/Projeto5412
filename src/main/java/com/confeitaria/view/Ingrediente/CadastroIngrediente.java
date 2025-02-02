package main.java.com.confeitaria.view.Ingrediente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class CadastroIngrediente {
    private JFrame frame;
    private JTextField txtNome;
    private JTextField txtQuantidade;
    private JTextField txtQuantidadeMinima;
    private JTextField txtDataValidade;

    // Matriz para armazenar os dados dos ingredientes
    private Object[][] dadosIngredientes = new Object[0][4];  // Inicializando com 0 ingredientes
    private String[] colunas = {"Nome", "Quantidade", "Quantidade Minima", "Data de Validade"};

    private DefaultTableModel tableModel;
    private JTable tabelaIngredientes;

    // Cores personalizadas para sua marca (azul bebê e rosa pastel)
    private Color corFundo = new Color(252, 231, 231);  // Cor de fundo clara (cinza claro)
    private Color corPrimaria = new Color(173, 216, 230); // Azul bebê (exemplo)
    private Color corSecundaria = new Color(255, 182, 193); // Rosa pastel (exemplo)

    public CadastroIngrediente() {
        frame = new JFrame("Cadastro de Ingredientes");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Painel principal com cor de fundo personalizada
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBackground(corFundo);

        // Definindo fontes modernas (Segoe UI)
        Font fontLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontText = new Font("Segoe UI", Font.PLAIN, 16);

        // Labels e campos de texto com cores e fontes personalizadas
        JLabel lblNome = new JLabel("Nome do Ingrediente:");
        lblNome.setFont(fontLabel);
        txtNome = new JTextField();
        txtNome.setFont(fontText);
        txtNome.setBorder(BorderFactory.createLineBorder(corPrimaria, 2));
        txtNome.setBackground(Color.WHITE);
        txtNome.setMargin(new Insets(10, 10, 10, 10)); // Afastando o texto da borda

        JLabel lblQuantidade = new JLabel("Quantidade no Estoque:");
        lblQuantidade.setFont(fontLabel);
        txtQuantidade = new JTextField();
        txtQuantidade.setFont(fontText);
        txtQuantidade.setBorder(BorderFactory.createLineBorder(corPrimaria, 2));
        txtQuantidade.setBackground(Color.WHITE);
        txtQuantidade.setMargin(new Insets(10, 10, 10, 10)); // Afastando o texto da borda

        JLabel lblQuantidadeMinima = new JLabel("Quantidade Mínima:");
        lblQuantidadeMinima.setFont(fontLabel);
        txtQuantidadeMinima = new JTextField();
        txtQuantidadeMinima.setFont(fontText);
        txtQuantidadeMinima.setBorder(BorderFactory.createLineBorder(corPrimaria, 2));
        txtQuantidadeMinima.setBackground(Color.WHITE);
        txtQuantidadeMinima.setMargin(new Insets(10, 10, 10, 10)); // Afastando o texto da borda

        JLabel lblDataValidade = new JLabel("Data de Validade (dd/MM/yyyy):");
        lblDataValidade.setFont(fontLabel);
        txtDataValidade = new JTextField();
        txtDataValidade.setFont(fontText);
        txtDataValidade.setBorder(BorderFactory.createLineBorder(corPrimaria, 2));
        txtDataValidade.setBackground(Color.WHITE);
        txtDataValidade.setMargin(new Insets(10, 10, 10, 10)); // Afastando o texto da borda

        // Botão para cadastrar ingrediente
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(fontText);
        btnCadastrar.setBackground(corSecundaria);
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setBorder(BorderFactory.createLineBorder(corSecundaria, 2));
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarIngrediente();
            }
        });

        // Adicionando os componentes ao painel
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblQuantidade);
        panel.add(txtQuantidade);
        panel.add(lblQuantidadeMinima);
        panel.add(txtQuantidadeMinima);
        panel.add(lblDataValidade);
        panel.add(txtDataValidade);
        panel.add(new JLabel()); // Espaço vazio
        panel.add(btnCadastrar);

        // Inicializando o modelo da tabela
        tableModel = new DefaultTableModel(dadosIngredientes, colunas);
        tabelaIngredientes = new JTable(tableModel);

        // Definindo a fonte para os títulos da tabela em negrito
        tabelaIngredientes.getTableHeader().setFont(new Font("Deco", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(tabelaIngredientes);

        // Adicionando o painel e a tabela no JFrame
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Definindo cor de fundo da janela
        frame.getContentPane().setBackground(corFundo);
        frame.setVisible(true);
    }

    private void cadastrarIngrediente() {
        String nome = txtNome.getText();
        String quantidadeStr = txtQuantidade.getText();
        String quantidadeMinimaStr = txtQuantidadeMinima.getText();
        String dataValidadeStr = txtDataValidade.getText();

        if (!nome.isEmpty() && !quantidadeStr.isEmpty() && !quantidadeMinimaStr.isEmpty() && !dataValidadeStr.isEmpty()) {
            // Verificar se o nome do ingrediente já existe
            if (ingredienteExistente(nome)) {
                JOptionPane.showMessageDialog(frame, "Erro: Ingrediente com o mesmo nome já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return; // Impede o cadastro de ingredientes com o mesmo nome
            }

            try {
                int quantidade = Integer.parseInt(quantidadeStr);
                int quantidadeMinima = Integer.parseInt(quantidadeMinimaStr);
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date dataValidade = format.parse(dataValidadeStr);

                // Adicionar os dados na matriz
                Object[] novaLinha = { nome, quantidade, quantidadeMinima, format.format(dataValidade) };
                // Atualizando a matriz de ingredientes
                Object[][] novosDados = new Object[dadosIngredientes.length + 1][4];
                System.arraycopy(dadosIngredientes, 0, novosDados, 0, dadosIngredientes.length);
                novosDados[dadosIngredientes.length] = novaLinha;

                dadosIngredientes = novosDados; // Atualizando a matriz original

                // Atualizar o modelo da tabela com os novos dados
                tableModel.setDataVector(dadosIngredientes, colunas);

                // Limpar campos após cadastro
                txtNome.setText("");
                txtQuantidade.setText("");
                txtQuantidadeMinima.setText("");
                txtDataValidade.setText("");

            } catch (NumberFormatException | ParseException e) {
                JOptionPane.showMessageDialog(frame, "Erro: Verifique os campos de quantidade e data.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean ingredienteExistente(String nome) {
        // Percorrer todos os ingredientes e verificar se o nome já existe
        for (Object[] ingrediente : dadosIngredientes) {
            if (ingrediente[0].toString().equalsIgnoreCase(nome)) {
                return true; // Ingrediente já existe
            }
        }
        return false; // Ingrediente não encontrado
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroIngrediente();
            }
        });
    }
}
