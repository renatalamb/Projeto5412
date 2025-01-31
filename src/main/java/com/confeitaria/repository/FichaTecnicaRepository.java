package main.java.com.confeitaria.repository;

import main.java.com.confeitaria.model.FichaTecnica;
import main.java.com.confeitaria.model.Ingrediente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FichaTecnicaRepository {
    private static final String CSV_FILE = "fichas_tecnicas.csv";

    // Método para exportar fichas técnicas para um arquivo CSV
    public void exportarParaCSV(List<FichaTecnica> fichas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            writer.write("NomeProduto,ModoPreparo,Rendimento,Ingredientes\n");

            for (FichaTecnica ficha : fichas) {
                StringBuilder ingredientesStr = new StringBuilder();
                for (Ingrediente ingrediente : ficha.getIngredientes()) {
                    ingredientesStr.append(ingrediente.getNome()).append(":")
                            .append(ingrediente.getQuantidade()).append(":")
                            .append(ingrediente.getUnidadeMedida()).append(";");
                }

                writer.write(ficha.getNomeProduto() + "," +
                        ficha.getModoPreparo() + "," +
                        ficha.getRendimento() + "," +
                        ingredientesStr + "\n");
            }

            System.out.println("Fichas exportadas com sucesso para " + CSV_FILE);
        } catch (IOException e) {
            System.err.println("Erro ao exportar para CSV: " + e.getMessage());
        }
    }

    // Método para importar fichas técnicas de um arquivo CSV
    public List<FichaTecnica> importarDeCSV() {
        List<FichaTecnica> fichas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String linha;
            reader.readLine(); // Pular cabeçalho

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",", 4);

                if (partes.length < 4) continue;

                String nomeProduto = partes[0];
                String modoPreparo = partes[1];
                int rendimento = Integer.parseInt(partes[2]);
                String ingredientesStr = partes[3];

                FichaTecnica ficha = new FichaTecnica(nomeProduto, modoPreparo, rendimento);

                if (!ingredientesStr.isEmpty()) {
                    String[] ingredientesArray = ingredientesStr.split(";");
                    for (String ingredienteData : ingredientesArray) {
                        String[] dados = ingredienteData.split(":");
                        if (dados.length == 3) {
                            String nome = dados[0];
                            double quantidade = Double.parseDouble(dados[1]);
                            String unidade = dados[2];
                            ficha.adicionarIngrediente(new Ingrediente(nome, quantidade, unidade));
                        }
                    }
                }

                fichas.add(ficha);
            }

            System.out.println("Fichas importadas com sucesso do arquivo " + CSV_FILE);
        } catch (IOException e) {
            System.err.println("Erro ao importar do CSV: " + e.getMessage());
        }

        return fichas;
    }
}
