package main.java.com.confeitaria.view;

import main.java.com.confeitaria.model.FichaTecnica;
import main.java.com.confeitaria.service.FichaTecnicaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistoFichaTecnica {
    private JFrame frame;
    private JTextField txtNomeProduto;
    private JTextArea txtModoPreparo;
    private JTextField txtRendimento;
    private JTextField txtIngredienteNome;
    private JTextField txtIngredienteQuantidade;
    private JTextField txtIngredienteUnidade;
    private DefaultListModel<String> ingredienteListModel;
    private JList<String> ingredienteList;
    private FichaTecnicaService fichaService;
    private FichaTecnica fichaAtual;

    public RegistoFichaTecnica() {
        fichaService = new FichaTecnicaService();
        frame = new JFrame("Cadastro de Ficha Técnica");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 2));

        frame.add(new JLabel("Nome do Produto:"));
        txtNomeProduto = new JTextField();
        frame.add(txtNomeProduto);

        frame.add(new JLabel("Modo de Preparo:"));
        txtModoPreparo = new JTextArea(3, 20);
        frame.add(new JScrollPane(txtModoPreparo));

        frame.add(new JLabel("Rendimento:"));
        txtRendimento = new JTextField();
        frame.add(txtRendimento);

        frame.add(new JLabel("Ingrediente:"));
        txtIngredienteNome = new JTextField();
        frame.add(txtIngredienteNome);

        frame.add(new JLabel("Quantidade:"));
        txtIngredienteQuantidade = new JTextField();
        frame.add(txtIngredienteQuantidade);

        frame.add(new JLabel("Unidade:"));
        txtIngredienteUnidade = new JTextField();
        frame.add(txtIngredienteUnidade);

        JButton btnAdicionarIngrediente = new JButton("Adicionar Ingrediente");
        frame.add(btnAdicionarIngrediente);

        ingredienteListModel = new DefaultListModel<>();
        ingredienteList = new JList<>(ingredienteListModel);
        frame.add(new JScrollPane(ingredienteList));

        JButton btnSalvar = new JButton("Salvar Ficha Técnica");
        frame.add(btnSalvar);

        btnAdicionarIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarIngrediente();
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarFicha();
            }
        });

        frame.setVisible(true);
    }

    private void adicionarIngrediente() {
        String nome = txtIngredienteNome.getText();
        double quantidade;
        String unidade = txtIngredienteUnidade.getText();

        try {
            quantidade = Double.parseDouble(txtIngredienteQuantidade.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Quantidade inválida!");
            return;
        }

        if (fichaAtual == null) {
            fichaAtual = new FichaTecnica(txtNomeProduto.getText(), txtModoPreparo.getText(), Integer.parseInt(txtRendimento.getText()));
        }

        fichaAtual.adicionarIngrediente(nome, (int) quantidade, unidade);
        ingredienteListModel.addElement(nome + " - " + quantidade + " " + unidade);

        txtIngredienteNome.setText("");
        txtIngredienteQuantidade.setText("");
        txtIngredienteUnidade.setText("");
    }

    private void salvarFicha() {
        if (fichaAtual == null) {
            JOptionPane.showMessageDialog(frame, "Nenhuma ficha técnica para salvar.");
            return;
        }

        fichaService.salvarFicha(fichaAtual);
        JOptionPane.showMessageDialog(frame, "Ficha Técnica salva com sucesso!");

        txtNomeProduto.setText("");
        txtModoPreparo.setText("");
        txtRendimento.setText("");
        ingredienteListModel.clear();
        fichaAtual = null;
    }

    public static void main(String[] args) {
        new RegistoFichaTecnica();
    }
}