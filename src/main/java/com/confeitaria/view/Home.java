package main.java.com.confeitaria.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.com.confeitaria.controller.Login;
import javax.swing.*;

public class Home extends JFrame{
    private JButton bt_login;
    private JTextField tf_email;
    private JLabel lb_email;
    private JLabel lb_pass;
    private JTextField tf_password;

    public Home() {
        // Inicialização dos componentes
      /*  bt_login = new JButton("Login");
        tf_email = new JTextField(20);
        tf_password = new JTextField(20);
        lb_email = new JLabel("Email:");
        lb_pass = new JLabel("Password:"); */

        // Configuração do layout
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(lb_email);
        add(tf_email);
        add(lb_pass);
        add(tf_password);
        add(bt_login);

        // Ação do botão de login
        bt_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pega os valores dos campos de texto
                String email = tf_email.getText();
                String password = tf_password.getText();
                Login login = new Login();

                login.fazerLogin(email,password);


            }
        });

        // Configurações da janela
        setTitle("Tela de Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Home();  // Criar uma instância da tela Home
    }
}
