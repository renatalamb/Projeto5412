package main.java.com.confeitaria.service;

import main.java.com.confeitaria.model.FichaTecnica;
import main.java.com.confeitaria.model.Ingrediente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FichaTecnicaService {

    private static final String FILE_NAME = "fichas_tecnicas.csv";

    // Método para salvar uma ficha técnica no CSV
    public void salvarFicha(FichaTecnica ficha) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true); PrintWriter pw = new PrintWriter(fw)) {
            StringBuilder sb = new StringBuilder();
            sb.append(ficha.getNomeProduto()).append(",");
            sb.append(ficha.getModoPreparo()).append(",");
            sb.append(ficha.getRendimento()).append(",");
            for (Ingrediente ingrediente : ficha.getIngredientes()) {
                sb.append(ingrediente.getNome()).append(":")
                        .append(ingrediente.getQuantidade()).append(" ")
                        .append(ingrediente.getUnidadeMedida()).append(";");
            }
            pw.println(sb.toString());
            System.out.println("Ficha Técnica salva com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a ficha técnica no CSV: " + e.getMessage());
        }
    }

    // Método para carregar todas as fichas técnicas do arquivo CSV
    public List<FichaTecnica> carregarFichas() {
        List<FichaTecnica> fichas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String nomeProduto = dados[0];
                String modoPreparo = dados[1];
                int rendimento = Integer.parseInt(dados[2]);

                FichaTecnica ficha = new FichaTecnica(nomeProduto, modoPreparo, rendimento);

                if (dados.length > 3) {
                    String[] ingredientes = dados[3].split(";");
                    for (String ingredienteStr : ingredientes) {
                        String[] ingredienteDados = ingredienteStr.split(":");
                        String nomeIngrediente = ingredienteDados[0];
                        String[] quantidadeUnidade = ingredienteDados[1].split(" ");
                        int quantidade = Integer.parseInt(quantidadeUnidade[0]);
                        String unidade = quantidadeUnidade[1];
                        ficha.adicionarIngrediente(nomeIngrediente, quantidade, unidade);
                    }
                }

                fichas.add(ficha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
        return fichas;
    }
}