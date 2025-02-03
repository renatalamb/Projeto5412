package main.java.com.confeitaria.view;

import main.java.com.confeitaria.controller.IngredienteController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class IngredienteInterface extends JFrame {
    private IngredienteController controller;
    private JButton botaoCriar, botaoListar, botaoVerificar, botaoExportar;

    public IngredienteInterface() {
        controller = new IngredienteController();

        setTitle("Gerenciamento de Ingredientes");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        botaoCriar = new JButton("Adicionar Ingrediente");
        botaoListar = new JButton("Listar Ingredientes");
        botaoVerificar = new JButton("Verificar Validade");
        botaoExportar = new JButton("Exportar para CSV");

        botaoCriar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.criarIngrediente();
            }
        });

        botaoListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.listarIngredientes();
            }
        });

        botaoVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.verificarValidade();
            }
        });

        botaoExportar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Usando JFileChooser para permitir ao usuário escolher o arquivo
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Escolha o local para salvar o arquivo CSV");

                // Defina o filtro de extensão para CSV
                fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos CSV", "csv"));

                int escolha = fileChooser.showSaveDialog(null);  // Exibe o diálogo

                if (escolha == JFileChooser.APPROVE_OPTION) {
                    // Obtém o caminho selecionado
                    String caminhoArquivo = fileChooser.getSelectedFile().getAbsolutePath();

                    // Se o usuário não especificou a extensão ".csv", adicionamos manualmente
                    if (!caminhoArquivo.endsWith(".csv")) {
                        caminhoArquivo += ".csv";
                    }

                    // Chama o método para exportar os ingredientes
                    controller.exportarIngredientesCSV(caminhoArquivo);
                }
            }
        });

        add(botaoCriar);
        add(botaoListar);
        add(botaoVerificar);
        add(botaoExportar);

        setVisible(true);
    }

    public static void main(String[] args) {
        new IngredienteInterface();
    }
}
