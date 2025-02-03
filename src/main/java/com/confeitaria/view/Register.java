package main.java.com.confeitaria.view;

import main.java.com.confeitaria.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Register extends JFrame {

    private JButton registerButton;
    private JTextField tf_Nome, tf_Email, tf_Data_de_Nascimento, tf_Morada, tf_Telemovel;
    private JPasswordField pf_Password;
    private JProgressBar progressBar1;
    private static final String FILE_NAME = "usuarios.csv";
    private static List<Usuario> usuarios = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Register() {
        setTitle("Cadastro de Usuário");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        tf_Nome = new JTextField();
        tf_Email = new JTextField();
        tf_Data_de_Nascimento = new JTextField();
        tf_Morada = new JTextField();
        tf_Telemovel = new JTextField();
        pf_Password = new JPasswordField();
        registerButton = new JButton("Registrar");
        progressBar1 = new JProgressBar();
        progressBar1.setIndeterminate(true);
        progressBar1.setVisible(false);

        panel.add(new JLabel("Nome:"));
        panel.add(tf_Nome);
        panel.add(new JLabel("Email:"));
        panel.add(tf_Email);
        panel.add(new JLabel("Data de Nascimento (dd/MM/yyyy):"));
        panel.add(tf_Data_de_Nascimento);
        panel.add(new JLabel("Morada:"));
        panel.add(tf_Morada);
        panel.add(new JLabel("Telemóvel:"));
        panel.add(tf_Telemovel);
        panel.add(new JLabel("Senha:"));
        panel.add(pf_Password);
        panel.add(registerButton);
        panel.add(progressBar1);

        add(panel);

        registerButton.addActionListener(e -> cadastrarUsuario());

        setVisible(true);
    }

    private void cadastrarUsuario() {
        String nome = tf_Nome.getText();
        String email = tf_Email.getText();
        String dataNascimento = tf_Data_de_Nascimento.getText();
        String morada = tf_Morada.getText();
        String telemovel = tf_Telemovel.getText();
        String senha = new String(pf_Password.getPassword());

        if (nome.isEmpty() || email.isEmpty() || dataNascimento.isEmpty() || morada.isEmpty() || telemovel.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalDate dataFormatada = LocalDate.parse(dataNascimento, FORMATTER);
            Usuario usuario = new Usuario(nome, Usuario.Cargo.FUNCIONARIO, morada, dataFormatada, 0, telemovel, email, senha);
            usuarios.add(usuario);

            salvarUsuariosCSV();
            JOptionPane.showMessageDialog(this, "Cadastro realizado e salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            abrirTelaLogin();
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido! Use dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarUsuariosCSV() throws IOException {
        try (FileWriter fw = new FileWriter(FILE_NAME); PrintWriter pw = new PrintWriter(fw)) {
            pw.println("Nome,Email,Data de Nascimento,Morada,Telemóvel,Senha");
            for (Usuario usuario : usuarios) {
                pw.println(usuario.getNome() + "," + usuario.getEmail() + "," + usuario.getDataDeNascimento().format(FORMATTER) + "," + usuario.getMorada() + "," + usuario.getTelemovel() + "," + usuario.getPalPasse());
            }
        }
    }

    private void abrirTelaLogin() {
        dispose(); // Fecha a tela de registro
        new LoginRegister(); // Abre a tela de login
    }

    public static void main(String[] args) {
        new Register();
    }
}


