package main.java.com.confeitaria.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI extends JFrame {

    public MenuGUI() {
        setTitle("Menu Principal");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnCadastrarIngrediente = new JButton("Cadastrar Ingrediente");
        JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
        JButton btnCadastrarBebida = new JButton("Cadastrar Bebida");

        // Adicionar ações aos botões
        btnCadastrarIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new IngredienteGUI().setVisible(true);
            }
        });

        btnCadastrarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new ProdutoFinalGUI().setVisible(true);
            }
        });

        btnCadastrarBebida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new BebidaGUI().setVisible(true);
            }
        });


        panel.add(btnCadastrarIngrediente);
        panel.add(btnCadastrarProduto);
        panel.add(btnCadastrarBebida);

        add(panel);
    }
}
