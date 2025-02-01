package main.java.com.confeitaria.repository;

import main.java.com.confeitaria.model.ProdutoFinal;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ProdutoFinalRepository {

    private static final String CSV_FILE = "produtos.csv";
    private static final String BIN_FILE = "produtos.dat";

    // Exportar lista de produtos para CSV
    public static void exportarParaCSV(List<ProdutoFinal> produtos) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE))) {
            writer.write("Nome,Categoria,Data de Produção,Quantidade Estoque,Quantidade Mínima\n");
            for (ProdutoFinal produto : produtos) {
                writer.write(String.format("%s,%s,%s,%d,%d\n",
                        produto.getNome(),
                        produto.getDataProducao(),
                        produto.getQtdEstoque(),
                        produto.getQtdMinima()));
            }
            System.out.println("Dados exportados para CSV com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao exportar para CSV: " + e.getMessage());
        }
    }

    // Importar lista de produtos de um arquivo CSV
    public static List<ProdutoFinal> importarDeCSV() {
        List<ProdutoFinal> produtos = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(CSV_FILE))) {
            String linha;
            reader.readLine(); // Pula o cabeçalho
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                ProdutoFinal produto = new ProdutoFinal(dados[0], Integer.parseInt(dados[1]), Integer.parseInt(dados[2]));
                produto.setNome(dados[0]);
                produto.setDataProducao(LocalDate.parse(dados[2]));
                produto.setQtdEstoque(Integer.parseInt(dados[3]));
                produto.setQtdMinima(Integer.parseInt(dados[4]));
                produtos.add(produto);
            }
            System.out.println("Dados importados do CSV com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao importar do CSV: " + e.getMessage());
        }
        return produtos;
    }

    // Exportar lista de produtos para arquivo binário
    public static void exportarParaBinario(List<ProdutoFinal> produtos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BIN_FILE))) {
            oos.writeObject(produtos);
            System.out.println("Dados exportados para arquivo binário com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao exportar para binário: " + e.getMessage());
        }
    }

    // Importar lista de produtos de um arquivo binário
    @SuppressWarnings("unchecked")
    public static List<ProdutoFinal> importarDeBinario() {
        List<ProdutoFinal> produtos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BIN_FILE))) {
            produtos = (List<ProdutoFinal>) ois.readObject();
            System.out.println("Dados importados do arquivo binário com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao importar do binário: " + e.getMessage());
        }
        return produtos;
    }
}
