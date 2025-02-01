package main.java.com.confeitaria.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {

    // Declaração dos componentes da interface gráfica
    private JButton registerButton;
    private JTextField tf_Nome;
    private JTextField tf_Email;
    private JTextField tf_Data_de_Nascimento;
    private JTextField tf_Morada;
    private JTextField tf_Telemovel;
    private JPasswordField pf_Password;
    private JProgressBar progressBar1;

    // Construtor para criar a interface
    public Register() {
        setTitle("Cadastro de Usuário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela

        // Criar e configurar os componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2)); // Grid com 8 linhas e 2 colunas

        // Inicializar os campos
        tf_Nome = new JTextField();
        tf_Email = new JTextField();
        tf_Data_de_Nascimento = new JTextField();
        tf_Morada = new JTextField();
        tf_Telemovel = new JTextField();
        pf_Password = new JPasswordField();
        registerButton = new JButton("Registrar");
        progressBar1 = new JProgressBar();

        progressBar1.setIndeterminate(true);
        progressBar1.setVisible(false); // Inicialmente escondida

        // Criar labels
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
        JLabel lblMorada = new JLabel("Morada:");
        JLabel lblTelemovel = new JLabel("Telemóvel:");
        JLabel lblPassword = new JLabel("Senha:");

        // Adicionar os componentes ao painel
        panel.add(lblNome);
        panel.add(tf_Nome);
        panel.add(lblEmail);
        panel.add(tf_Email);
        panel.add(lblDataNascimento);
        panel.add(tf_Data_de_Nascimento);
        panel.add(lblMorada);
        panel.add(tf_Morada);
        panel.add(lblTelemovel);
        panel.add(tf_Telemovel);
        panel.add(lblPassword);
        panel.add(pf_Password);
        panel.add(registerButton);
        panel.add(progressBar1);

        add(panel); // Adiciona o painel ao JFrame

        // Ação do botão de registro
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });

        setVisible(true);
    }

    // Método para cadastrar o usuário
    private void cadastrarUsuario() {
        String nome = tf_Nome.getText();
        String email = tf_Email.getText();
        String dataNascimento = tf_Data_de_Nascimento.getText();
        String morada = tf_Morada.getText();
        String telemovel = tf_Telemovel.getText();
        String senha = new String(pf_Password.getPassword());

        // Verifica se todos os campos estão preenchidos
        if (nome.isEmpty() || email.isEmpty() || dataNascimento.isEmpty() || morada.isEmpty() || telemovel.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            // Simula um cadastro bem-sucedido
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Nome: " + nome);
            System.out.println("Email: " + email);
            System.out.println("Data de Nascimento: " + dataNascimento);
            System.out.println("Morada: " + morada);
            System.out.println("Telemóvel: " + telemovel);
            System.out.println("Senha: " + senha);
        }
    }

    // Método principal para executar o programa
    public static void main(String[] args) {
        new Register();
    }
}

