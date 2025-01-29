package main.java.com.confeitaria.view.src;

import javax.swing.*;

class Main {
    public static void main(String[] args) {
        // Cria uma nova instância da classe Teste
        new Teste();
    }
}

class Teste {
    // Construtor da classe Teste
    Teste() {
        // Cria uma nova janela JFrame com o título "Teste"
        JFrame jframe = new JFrame("Teste");
        jframe.setSize(800, 500); // Define o tamanho da janela
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        jframe.setResizable(false); // Impede que a janela seja redimensionada
        jframe.setLocationRelativeTo(null); // Centraliza a janela na tela
        jframe.setVisible(true); // Torna a janela visível
    }
}


