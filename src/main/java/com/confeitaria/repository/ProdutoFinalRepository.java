package main.java.com.confeitaria.repository;

import main.java.com.confeitaria.model.ProdutoFinal;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProdutoFinalRepository {

    private static final String PRODUTOS_FILE = "./src/main/java/com/confeitaria/data/produtosFinais.csv";
    private static final String HISTORICO_FILE = "./src/main/java/com/confeitaria/data/historicoMovimentacoesProduto.csv";    private static final String BIN_FILE = "./src/main/java/com/confeitaria/data/produtos.dat";
    //private static final String BIN_FILE = "./src/main/java/com/confeitaria/data/produtos.dat";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Salvar produto no CSV
    public void salvarProdutoCSV(ProdutoFinal produto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUTOS_FILE, true))) {
            writer.write(produto.getNome() + ";" + produto.getQtdEstoque() + ";" + produto.getQtdMinima() + ";" + produto.getDataProducao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao salvar produto: " + e.getMessage());
        }
    }

    // Registrar movimentação no histórico CSV
    public void registrarMovimentacaoCSV(ProdutoFinal produto, String descricao) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORICO_FILE, true))) {
            writer.write(produto.getNome() + ";" + descricao + "\n");
        } catch (IOException e) {
            System.err.println("Erro ao salvar movimentação: " + e.getMessage());
        }
    }

    // Salvar lista de produtos no CSV
    public void salvarProdutosCSV(List<ProdutoFinal> produtos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUTOS_FILE))) {
            writer.write("Nome;Quantidade Estoque;Quantidade Mínima;Data Produção\n"); // Cabeçalho
            for (ProdutoFinal produto : produtos) {
                writer.write(produto.getNome() + ";" + produto.getQtdEstoque() + ";" + produto.getQtdMinima() + ";" + produto.getDataProducao().format(FORMATTER) + "\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    // Carregar produtos do CSV
    public List<ProdutoFinal> carregarProdutosCSV() {
        List<ProdutoFinal> produtos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUTOS_FILE))) {
            String linha;
            reader.readLine(); // Ignorar o cabeçalho
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                String nome = dados[0];
                int qtdEstoque = Integer.parseInt(dados[1]);  // Corrigido para int
                int qtdMinima = Integer.parseInt(dados[2]);  // Corrigido para int
                ProdutoFinal produto = new ProdutoFinal(nome, qtdEstoque, qtdMinima);
                produtos.add(produto);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar produtos: " + e.getMessage());
        }
        return produtos;
    }


    // Exportar lista de produtos para CSV
    public void exportarParaCSV(List<ProdutoFinal> produtos) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(PRODUTOS_FILE))) {  // Corrigido caminho de arquivo
            writer.write("Nome;Quantidade Estoque;Quantidade Mínima;Data de Produção\n");

            for (ProdutoFinal produto : produtos) {
                writer.write(String.format("%s;%d;%d;%s\n",  // Corrigido para int
                        produto.getNome(),
                        produto.getQtdEstoque(),
                        produto.getQtdMinima(),
                        produto.getDataProducao().format(FORMATTER)));
            }
            System.out.println("Produtos exportados para CSV com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao exportar para CSV: " + e.getMessage());
        }
    }

    // Importar lista de produtos de um arquivo CSV
    public List<ProdutoFinal> importarDeCSV() {
        List<ProdutoFinal> produtos = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(PRODUTOS_FILE))) {
            String linha;
            reader.readLine(); // Ignora o cabeçalho
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 4) {
                    String nome = dados[0];
                    int qtdEstoque = Integer.parseInt(dados[1]);  // Corrigido para int
                    int qtdMinima = Integer.parseInt(dados[2]);  // Corrigido para int
                    LocalDate dataProducao = LocalDate.parse(dados[3], FORMATTER);

                    ProdutoFinal produto = new ProdutoFinal(nome, qtdEstoque, qtdMinima);
                    produto.setDataProducao(dataProducao);
                    produtos.add(produto);
                }
            }
            System.out.println("Produtos importados do CSV com sucesso!");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao importar do CSV: " + e.getMessage());
        }
        return produtos;
    }

    // Exportar lista de produtos para arquivo binário
    public void exportarParaBinario(List<ProdutoFinal> produtos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BIN_FILE))) {
            oos.writeObject(produtos);
            System.out.println("Produtos exportados para arquivo binário com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao exportar para binário: " + e.getMessage());
        }
    }

    // Importar lista de produtos de um arquivo binário
    public List<ProdutoFinal> importarDeBinario() {
        List<ProdutoFinal> produtos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BIN_FILE))) {
            produtos = (List<ProdutoFinal>) ois.readObject();
            System.out.println("Produtos importados do arquivo binário com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao importar do binário: " + e.getMessage());
        }
        return produtos;
    }


}
