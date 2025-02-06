package main.java.com.confeitaria.service;

import main.java.com.confeitaria.model.Bebida;
import main.java.com.confeitaria.model.Ingrediente;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BebidaService {
    private static final List<Bebida> listaBebidas = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private List<Bebida> bebidas;
    private static final String BEBIDAS_FILE = "./src/main/java/com/confeitaria/data/bebidas.csv";

    // Construtor
    public BebidaService() {
        bebidas = new ArrayList<>();
    }

    // Adiciona uma nova bebida à lista
    public void adicionarBebida(Bebida bebida) {
        bebidas.add(bebida);
    }

    // Busca uma bebida pelo nome
    public Bebida buscarBebidaPorNome(String nome) {
        for (Bebida bebida : bebidas) {
            if (bebida.getNome().equalsIgnoreCase(nome)) {
                return bebida;
            }
        }
        return null; // Caso não encontre
    }

    // Atualiza o estoque de uma bebida
    public boolean atualizarEstoqueBebida(Bebida bebida, int quantidade, boolean adicionar) {
        if (adicionar) {
            bebida.setQuantidade(bebida.getQuantidade() + quantidade);
        } else {
            if (bebida.getQuantidade() >= quantidade) {
                bebida.setQuantidade(bebida.getQuantidade() - quantidade);
            } else {
                JOptionPane.showMessageDialog(null, "Erro: Estoque insuficiente para a venda!");
            }
        }
        return adicionar;
    }


    // Registra uma venda de uma bebida
    public void registrarVendaBebida(Bebida bebida, int quantidadeVendida) {
        if (quantidadeVendida > 0 && quantidadeVendida <= bebida.getQuantidade()) {
            bebida.setQuantidade(bebida.getQuantidade() - quantidadeVendida);
            registrarMovimentacao(bebida, quantidadeVendida, "Saída");
            verificarLimiteMinimo(bebida);
        } else {
            System.out.println("Erro: Quantidade inválida ou insuficiente.");
        }
    }

    // Verifica se o estoque da bebida está abaixo do mínimo
    private void verificarLimiteMinimo(Bebida bebida) {
        if (bebida.getQuantidade() < bebida.getQuantidadeMin()) {
            System.out.println("Atenção: Estoque de bebida '" + bebida.getNome() + "' abaixo do mínimo!");
        }
    }

    // Registra uma movimentação (entrada ou saída)
    private void registrarMovimentacao(Bebida bebida, int quantidade, String tipo) {
        if (bebida.getHistoricoMovimentacoesBebidas() == null) {
            bebida.setHistoricoMovimentacoesBebidas(new ArrayList<>());
        }
        String registro = "Tipo: " + tipo + " | Quantidade: " + quantidade + " | Data/Hora: " + LocalDateTime.now();
        bebida.getHistoricoMovimentacoesBebidas().add(registro);
    }

    // Exibe o histórico de movimentações de uma bebida
    public void exibirHistoricoMovimentacoesBebidas(Bebida bebida) {
        List<String> historico = bebida.getHistoricoMovimentacoesBebidas();
        if (historico == null || historico.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            for (String movimentacao : historico) {
                System.out.println(movimentacao);
            }
        }
    }

    // Exibe os dados de uma bebida
    public void exibirBebida(Bebida bebida) {
        System.out.println("Bebida: " + bebida.getNome());
        System.out.println("Estoque: " + bebida.getQuantidade());
        System.out.println("Mínimo: " + bebida.getQuantidadeMin());
    }

    public Bebida buscarBebida(String nome) {
        return listaBebidas.stream()
                .filter(i -> i.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public List<Bebida> listarBebidas() {
        return new ArrayList<>(listaBebidas);
    }

    public void atualizarIngredienteNoCSV(Ingrediente ingrediente) {
        try {
            // Lê todas as linhas do arquivo CSV
            List<String> linhas = Files.readAllLines(Paths.get(BEBIDAS_FILE));

            // Cria um StringBuilder para armazenar o conteúdo atualizado
            StringBuilder novoConteudo = new StringBuilder();
            boolean ingredienteEncontrado = false;

            for (String linha : linhas) {
                String[] dados = linha.split(";");
                String nomeIngrediente = dados[0];

                if (nomeIngrediente.equals(ingrediente.getNome())) {
                    // Se o nome do ingrediente for encontrado, atualiza a quantidade
                    novoConteudo.append(String.format("%s;%d;%s;%d;%s\n",
                            ingrediente.getNome(),
                            ingrediente.getQuantidade(),   // Aqui já usamos a nova quantidade
                            ingrediente.getUnidadeMedida(),
                            ingrediente.getQuantMinima(),
                            ingrediente.formatarDataValidade()));
                    ingredienteEncontrado = true;
                } else {
                    // Senão, mantém a linha original
                    novoConteudo.append(linha).append("\n");
                }
            }

            // Se o ingrediente não foi encontrado, adiciona uma nova linha ao CSV
            if (!ingredienteEncontrado) {
                novoConteudo.append(String.format("%s;%d;%s;%d;%s\n",
                        ingrediente.getNome(),
                        ingrediente.getQuantidade(),   // Nova quantidade
                        ingrediente.getUnidadeMedida(),
                        ingrediente.getQuantMinima(),
                        ingrediente.formatarDataValidade()));
            }

            // Escreve as mudanças de volta no arquivo CSV
            Files.write(Paths.get(BEBIDAS_FILE), novoConteudo.toString().getBytes());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o arquivo CSV!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarEstoque(Bebida bebidaSelecionada, int quantidade, boolean b) {
    }

    public void exibirHistoricoMovimentacoes(Bebida bebidaSelecionada) {
    }
}