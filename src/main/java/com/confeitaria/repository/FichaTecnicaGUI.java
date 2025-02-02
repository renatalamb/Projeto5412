package main.java.com.confeitaria.repository;

import main.java.com.confeitaria.model.FichaTecnica;
import main.java.com.confeitaria.model.Ingrediente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class FichaTecnicaGUI extends JFrame {
    private JTextField tfNomeProduto, tfModoPreparo, tfRendimento;
    private JTextField tfNomeIngrediente, tfQuantidade, tfUnidade;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaIngredientes;
    private FichaTecnica fichaTecnica;
    private static final String FILE_NAME = "fichas_tecnicas.csv";  // Nome do arquivo CSV

    public FichaTecnicaGUI() {
        fichaTecnica = new FichaTecnica();

        setTitle("Cadastro de Ficha Técnica");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 5, 5));

        // Componentes
        panel.add(new JLabel("Nome do Produto:"));
        tfNomeProduto = new JTextField();
        panel.add(tfNomeProduto);

        panel.add(new JLabel("Modo de Preparo:"));
        tfModoPreparo = new JTextField();
        panel.add(tfModoPreparo);

        panel.add(new JLabel("Rendimento:"));
        tfRendimento = new JTextField();
        panel.add(tfRendimento);

        // Seção de ingredientes
        panel.add(new JLabel("Ingrediente:"));
        tfNomeIngrediente = new JTextField();
        panel.add(tfNomeIngrediente);

        panel.add(new JLabel("Quantidade:"));
        tfQuantidade = new JTextField();
        panel.add(tfQuantidade);

        panel.add(new JLabel("Unidade:"));
        tfUnidade = new JTextField();
        panel.add(tfUnidade);

        add(panel, BorderLayout.NORTH);

        // Lista de ingredientes
        modeloLista = new DefaultListModel<>();
        listaIngredientes = new JList<>(modeloLista);
        add(new JScrollPane(listaIngredientes), BorderLayout.CENTER);

        // Botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout());

        JButton btnAdicionarIngrediente = new JButton("Adicionar Ingrediente");
        JButton btnSalvarFicha = new JButton("Salvar Ficha Técnica");
        JButton btnCarregarFichas = new JButton("Carregar Fichas");

        panelBotoes.add(btnAdicionarIngrediente);
        panelBotoes.add(btnSalvarFicha);
        panelBotoes.add(btnCarregarFichas);

        add(panelBotoes, BorderLayout.SOUTH);

        // Ação do botão Adicionar Ingrediente
        btnAdicionarIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarIngrediente();
            }
        });

        // Ação do botão Salvar Ficha Técnica
        btnSalvarFicha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarFichaTecnica();
            }
        });

        // Ação do botão Carregar Fichas Técnicas
        btnCarregarFichas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarFichasTecnicas();
            }
        });

        setVisible(true);
    }

    // Método para adicionar ingrediente
    private void adicionarIngrediente() {
        String nome = tfNomeIngrediente.getText();
        double quantidade;
        String unidade = tfUnidade.getText();

        try {
            quantidade = Double.parseDouble(tfQuantidade.getText());

            if (nome.isEmpty() || unidade.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            fichaTecnica.adicionarIngrediente(nome, quantidade, unidade);
            modeloLista.addElement(nome + " - " + quantidade + " " + unidade);

            // Limpa os campos após adicionar
            tfNomeIngrediente.setText("");
            tfQuantidade.setText("");
            tfUnidade.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para salvar ficha técnica
    private void salvarFichaTecnica() {
        String nomeProduto = tfNomeProduto.getText();
        String modoPreparo = tfModoPreparo.getText();
        int rendimento;

        try {
            rendimento = Integer.parseInt(tfRendimento.getText());

            if (nomeProduto.isEmpty() || modoPreparo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            fichaTecnica.setNomeProduto(nomeProduto);
            fichaTecnica.setModoPreparo(modoPreparo);
            fichaTecnica.setRendimento(rendimento);

            // Salvando no CSV
            salvarNoCSV();

            JOptionPane.showMessageDialog(this, "Ficha Técnica salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Rendimento inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para salvar no arquivo CSV
    private void salvarNoCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(fichaTecnica.toCSV());
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar a ficha técnica!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para carregar as fichas técnicas do arquivo CSV
    private void carregarFichasTecnicas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println("Ficha Técnica carregada: " + linha);
                // Aqui você pode adicionar a lógica para processar e exibir as fichas
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar as fichas técnicas!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método principal para executar a interface gráfica
    public static void main(String[] args) {
        new FichaTecnicaGUI();
    }
}