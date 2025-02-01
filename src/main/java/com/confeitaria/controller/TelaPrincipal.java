package main.java.com.confeitaria.controller;

import javax.swing.*;

public class TelaPrincipal extends JFrame {
    public TelaPrincipal() {
        setTitle("Tela Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel mensagem = new JLabel("Bem-vindo ao sistema!", SwingConstants.CENTER);
        add(mensagem);

        setVisible(true);
    }
}