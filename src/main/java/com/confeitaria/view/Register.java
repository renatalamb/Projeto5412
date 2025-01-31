package main.java.com.confeitaria.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {

    // Declaração dos componentes da interface gráfica
    private JButton registerButton;
    private JTextField tf_Nome;
    private JTextField tf_Email;
    private JTextField tf_Data_de_Nascimento;
    private JTextField tf_Morada;
    private JTextField tf_Telemovel;
    private JPasswordField pf_Password;
    private JProgressBar progressBar1;

    // Método principal para inicializar a interface
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cadastro de Usuário");
        Register register = new Register();
        frame.setContentPane(register.createUIComponents());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // Criação e organização dos componentes no painel
    private JPanel createUIComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2)); // Layout em Grid com 8 linhas e 2 colunas

        // Inicialização dos componentes
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
        JLabel lblMorada = new JLabel("Morada:");
        JLabel lblTelemovel = new JLabel("Telemóvel:");
        JLabel lblPassword = new JLabel("Senha:");

        progressBar1.setIndeterminate(true);
        progressBar1.setVisible(false); // Inicialmente invisível

        // Adicionando os componentes ao painel
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

        // Ação do botão de registro
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                        // Coletando os dados dos campos
                        String nome = tf_Nome.getText();
                        String email = tf_Email.getText();
                        String dataNascimento = tf_Data_de_Nascimento.getText();
                        String morada = tf_Morada.getText();
                        String telemovel = tf_Telemovel.getText();
                        String senha = new String(pf_Password.getPassword());

                        // Verificando se todos os campos foram preenchidos
                        if (nome.isEmpty() || email.isEmpty() || dataNascimento.isEmpty() || morada.isEmpty() || telemovel.isEmpty() || senha.isEmpty()) {
                            JOptionPane.showMessageDialog(panel, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Cadastro realizado com sucesso
                            JOptionPane.showMessageDialog(panel, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("Nome: " + nome);
                            System.out.println("Email: " + email);
                            System.out.println("Data de Nascimento: " + dataNascimento);
                            System.out.println("Morada: " + morada);
                            System.out.println("Telemóvel: " + telemovel);
                            System.out.println("Senha: " + senha);
                        }
                    }


        });

        return panel;
    }
}

