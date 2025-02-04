package main.java.com.confeitaria.view;

import main.java.com.confeitaria.controller.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Definindo variáveis
public class LoginRegister extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoLogin, botaoRegistrar;
    private Login loginController;

    // Controlador responsável por gerenciar a autenticação dos usuários via CSV
    public LoginRegister() {
        loginController = new Login();

        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 2, 5, 5));

        JLabel labelUsuario = new JLabel("Usuário:");
        campoUsuario = new JTextField();

        JLabel labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();

        // Criação dos botões
        botaoLogin = new JButton("Entrar");
        botaoRegistrar = new JButton("Registrar");

        painel.add(labelUsuario);
        painel.add(campoUsuario);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(new JLabel());
        painel.add(botaoLogin);
        painel.add(new JLabel());
        painel.add(botaoRegistrar);

        add(painel);

        // Definir o comportamento do botão de login
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });

        // Definir o comportamento do botão de registro
        botaoRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        setVisible(true);
    }

    // Esse método verifica as credenciais do usuário e exibe mensagens de sucesso ou erro.
    private void autenticarUsuario() {
        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        if (loginController.fazerLogin(usuario, senha)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            abrirTelaDashboard(); // Agora chama a tela correta
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para abrir a tela de registro
    private void registrarUsuario() {
        dispose(); // Fecha a janela de login
        new Register(); // Abre a tela de registro
    }

    // Esse método fecha a tela de login e abre a tela Dashboard
    private void abrirTelaDashboard() {
        dispose(); // Fecha a tela de login
        new MenuGUI().setVisible(true); // Abre o menu principal
    }

    public static void main(String[] args) {
        new LoginRegister();
    }
}
