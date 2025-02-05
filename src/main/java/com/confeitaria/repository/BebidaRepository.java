package main.java.com.confeitaria.repository;

import main.java.com.confeitaria.model.Bebida;
import main.java.com.confeitaria.model.ProdutoFinal;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BebidaRepository {

    private static final String BEBIDAS_FILE = "./src/main/java/com/confeitaria/data/bebidas.csv";
    private static final String HISTORICO_FILE = "./src/main/java/com/confeitaria/data/historicoMovimentacoesBebida.csv";
    private static final String BIN_FILE = "./src/main/java/com/confeitaria/data/bebidas.dat";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Salvar bebida no CSV
    public void salvarBebidasCSV(Bebida bebida) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BEBIDAS_FILE, true))) {
            writer.write(bebida.getNome() + ";" + bebida.getQuantidade() + ";" + bebida.getQuantidadeMin() + ";" + bebida.getDataValidadeFormatada() + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao salvar bebida: " + e.getMessage());
        }
    }

    // Registrar movimentação no histórico CSV
    public void registrarMovimentacaoCSV(Bebida bebida, String descricao) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORICO_FILE, true))) {
            writer.write(bebida.getNome() + ";" + descricao + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao salvar movimentação: " + e.getMessage());
        }
    }

    // Salvar lista de bebidas no CSV
    public void salvarBebidasCSV(List<Bebida> bebidas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BEBIDAS_FILE))) {
            writer.write("Nome;Quantidade Estoque;Quantidade Mínima;Data Validade\n"); // Cabeçalho
            for (Bebida bebida : bebidas) {
                writer.write(bebida.getNome() + ";" + bebida.getQuantidade() + ";" + bebida.getQuantidadeMin() + ";" + bebida.getDataValidadeFormatada() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar bebidas: " + e.getMessage());
        }
    }

    // Carregar bebidas do CSV
    public List<Bebida> carregarBebidasCSV() {
        List<Bebida> bebidas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BEBIDAS_FILE))) {
            String linha;
            reader.readLine(); // Ignorar o cabeçalho

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length < 4) { // Garante que há todas as colunas necessárias
                    System.err.println("Linha inválida no CSV: " + linha);
                    continue;
                }

                try {
                    String nome = dados[0].trim();
                    int quantidade = Integer.parseInt(dados[1].trim());
                    int quantidadeMin = Integer.parseInt(dados[2].trim());
                    LocalDate dataValidade = LocalDate.parse(dados[3].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    Bebida bebida = new Bebida(nome, quantidade, quantidadeMin, dataValidade);
                    bebidas.add(bebida);
                } catch (NumberFormatException | DateTimeParseException e) {
                    System.err.println("Erro ao processar linha: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar bebidas: " + e.getMessage());
        }
        return bebidas;
    }



    // Exportar lista de bebidas para CSV
    public void exportarParaCSV(List<Bebida> bebidas) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(BEBIDAS_FILE))) {  // Corrigido caminho de arquivo
            writer.write("Nome;Quantidade Estoque;Quantidade Mínima;Data Validade\n");

            for (Bebida bebida : bebidas) {
                writer.write(String.format("%s;%d;%d;%s\n",
                        bebida.getNome(),
                        bebida.getQuantidade(),
                        bebida.getQuantidadeMin(),
                        bebida.getDataValidadeFormatada()));
            }
            System.out.println("Bebidas exportados para CSV com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao exportar para CSV: " + e.getMessage());
        }
    }

    // Importar lista de bebidas de um arquivo CSV
    public List<Bebida> importarDeCSV() {
        List<Bebida> bebidas = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(BEBIDAS_FILE))) {
            String linha;
            reader.readLine(); // Ignora o cabeçalho
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 4) {
                    String nome = dados[0];
                    int quantidade = Integer.parseInt(dados[1]);  // Corrigido para int
                    int quantidadeMin = Integer.parseInt(dados[2]);  // Corrigido para int
                    LocalDate dataValidade = LocalDate.parse(dados[3], FORMATTER);

                    Bebida bebida = new Bebida(nome, quantidade, quantidadeMin, dataValidade);
                    bebida.setDataValidade(dataValidade);
                    bebidas.add(bebida);
                }
            }
            System.out.println("Bebidas importados do CSV com sucesso!");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao importar do CSV: " + e.getMessage());
        }
        return bebidas;
    }

    // Exportar lista de bebidas para arquivo binário
    public void exportarParaBinario(List<Bebida> bebidas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BIN_FILE))) {
            oos.writeObject(bebidas);
            System.out.println("Bebidas exportados para arquivo binário com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao exportar para binário: " + e.getMessage());
        }
    }

    // Importar lista de bebidas de um arquivo binário
    public List<Bebida> importarDeBinario() {
        List<Bebida> bebidas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BIN_FILE))) {
            bebidas = (List<Bebida>) ois.readObject();
            System.out.println("Bebidas importados do arquivo binário com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao importar do binário: " + e.getMessage());
        }
        return bebidas;
    }


}

