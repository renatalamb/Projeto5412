package main.java.com.confeitaria.service;

import main.java.com.confeitaria.model.Ingrediente;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class IngredienteService {

    private static final List<Ingrediente> listaIngredientes = new ArrayList<>();
    private static final String CSV_PATH = "./src/main/java/com/confeitaria/data/ingredientes.csv";

    private static final String HISTORICO_PATH = "./src/main/java/com/confeitaria/data/historico_estoque_ingredientes.csv";

    // Testando
    public IngredienteService() {
        carregarIngredientesDoCSV(CSV_PATH);
    }

    // Salva a movimentação do estoque em um arquivo csv
    public void registrarMovimentacao(String nome, int quantidade, boolean adicao) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORICO_PATH, true))) {
            String tipo = adicao ? "Adicionado" : "Retirado";
            writer.write(String.format("%s;%s;%d;%s\n", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), nome, quantidade, tipo));
        } catch (IOException e) {
            System.out.println("Erro ao salvar histórico de estoque: " + e.getMessage());
        }
    }

    // Registra a movimentação no estoque
    // Testando
    public void modificarEstoque(String nome, int quantidade, boolean adicionar) {
        Ingrediente ingrediente = buscarIngrediente(nome);
        if (ingrediente != null) {
            if (!adicionar && ingrediente.getQuantidade() < quantidade) {
                System.out.println("Erro: Estoque insuficiente!");
                return;
            }
            // Atualiza a quantidade
            ingrediente.adicionarQuantidade(adicionar ? quantidade : -quantidade);

            // Registra a movimentação no histórico
            registrarMovimentacao(nome, quantidade, adicionar);

            // Salva a lista inteira no CSV
            salvarTodosNoCSV();
        }

    }

    public void adicionarIngrediente(Ingrediente ingrediente) {
        // Adiciona na lista de ingredientes
        listaIngredientes.add(ingrediente);

        // Atualiza o arquivo CSV
        atualizarIngredienteNoCSV(ingrediente);

        // Confirma que a adição foi realizada
        System.out.println("Ingrediente " + ingrediente.getNome() + " adicionado com sucesso.");
    }



    public Ingrediente buscarIngrediente(String nome) {
        return listaIngredientes.stream()
                .filter(i -> i.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public List<Ingrediente> listarIngredientes() {
        return new ArrayList<>(listaIngredientes);
    }


    // Verificar ingredientes próximos do vencimento
    public static List<Ingrediente> verificarValidade() {
        LocalDate hoje = LocalDate.now();
        System.out.println("\n⚠ Ingredientes próximos da validade:");

        boolean temVencimentos = false;
        for (Ingrediente ingrediente : listaIngredientes) {
            if (ingrediente.dataValidade.isBefore(hoje.plusDays(7))) {
                System.out.println("⚠ " + ingrediente.getNome() + " vence em " + ingrediente.dataValidade.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                temVencimentos = true;
            }
        }

        if (!temVencimentos) {
            System.out.println("✅ Nenhum ingrediente próximo da validade!");
        }
        return null;
    }

    public List<Ingrediente> carregarIngredientesDoCSV(String csvPath) {
        List<Ingrediente> ingredientes = new ArrayList<>();
        try {
            List<String> linhas = Files.readAllLines(Paths.get(CSV_PATH));
            for (String linha : linhas) {
                String[] dados = linha.split(";");
                String nome = dados[0];
                int quantidade = Integer.parseInt(dados[1]);
                String unidadeMedida = dados[2];
                int quantMinima = Integer.parseInt(dados[3]);
                LocalDate dataValidade = LocalDate.parse(dados[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                Ingrediente ingrediente = new Ingrediente(nome, quantidade, unidadeMedida, quantMinima, dataValidade);
                ingredientes.add(ingrediente);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar ingredientes do CSV.");
        }
        return ingredientes;
    }


    public void atualizarIngredienteNoCSV(Ingrediente ingrediente) {
        try {
            // Lê todas as linhas do arquivo CSV
            List<String> linhas = Files.readAllLines(Paths.get(CSV_PATH));

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
            Files.write(Paths.get(CSV_PATH), novoConteudo.toString().getBytes());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o arquivo CSV!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // testando
    public void salvarTodosNoCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_PATH))) {
            writer.write("Nome;Quantidade;Unidade;Quantidade Mínima;Data de Validade");
            writer.newLine();

            for (Ingrediente ingrediente : listaIngredientes) {
                writer.write(String.format("%s;%d;%s;%d;%s\n",
                        ingrediente.getNome(),
                        ingrediente.getQuantidade(),
                        ingrediente.getUnidadeMedida(),
                        ingrediente.getQuantMinima(),
                        ingrediente.formatarDataValidade()));
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os ingredientes no CSV: " + e.getMessage());
        }

    }
}
