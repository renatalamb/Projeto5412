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
        // Criação do botão de login e registo, que será utilizado para autenticar o usuário.
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
        // Aqui é definido o comportamento do botão de login ao ser clicado.
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });
        // Aqui é definido o comportamento do registar de login ao ser clicado.

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
            abrirTelaPrincipal();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
    // Esse método regista usuario e e exibe mensagens de erro, sucesso ou úsuario já existe
    private void registrarUsuario() {
        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (loginController.registrarUsuario(usuario, senha)) {
            JOptionPane.showMessageDialog(this, "Usuário registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Usuário já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

     */


        private void registrarUsuario() {
            dispose(); // Fecha a janela de login
            new Register(); // Abre a tela de registro
        }


        // Esse método fecha a tela de login e abre a tela principal do sistema
    private void abrirTelaPrincipal() {
        dispose(); // Fecha a tela de login
        new TelaPrincipal(); // Abre a tela principal
    }

    public static void main(String[] args) {
        new LoginRegister();
    }
}
