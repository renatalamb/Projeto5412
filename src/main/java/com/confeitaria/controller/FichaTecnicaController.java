package main.java.com.confeitaria.controller;

import main.java.com.confeitaria.model.FichaTecnica;
import main.java.com.confeitaria.model.Ingrediente;
import main.java.com.confeitaria.service.FichaTecnicaService;

import java.io.*;
import java.util.List;

public class FichaTecnicaController {

    private FichaTecnicaService service;
    private static final String FILE_NAME = "fichas_tecnicas.csv";

    public FichaTecnicaController(FichaTecnicaService service) {
        this.service = service;
    }

    // Método para criar e salvar ficha técnica em CSV
    public void criarFicha(String nomeProduto, List<String> ingredientes, List<Double> quantidades, List<String> unidades, String modoPreparo, int rendimento) {
        FichaTecnica ficha = new FichaTecnica(nomeProduto, "", 0);

        // Adicionando os ingredientes à ficha técnica
        for (int i = 0; i < ingredientes.size(); i++) {
            ficha.adicionarIngrediente(ingredientes.get(i), quantidades.get(i), unidades.get(i));  // Mantendo o tipo double para quantidade
        }

        // Adicionando o modo de preparo e rendimento
        ficha.setModoPreparo(modoPreparo);
        ficha.setRendimento(rendimento);

        // Salvando a ficha técnica no arquivo CSV
        salvarFichaCSV(ficha);
    }

    // Método para salvar a ficha técnica em um arquivo CSV
    private void salvarFichaCSV(FichaTecnica ficha) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true); PrintWriter pw = new PrintWriter(fw)) {
            // Adiciona os dados da ficha técnica ao arquivo CSV
            String ingredientesCSV = converterIngredientesParaCSV(ficha.getIngredientes());

            pw.println(ficha.getNomeProduto() + "," +
                    ficha.getModoPreparo() + "," +
                    ficha.getRendimento() + "," +
                    ingredientesCSV);  // Usando o método de conversão de ingredientes

            System.out.println("Ficha Técnica salva com sucesso no arquivo CSV.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a ficha técnica no CSV: " + e.getMessage());
        }
    }

    // Método para converter a lista de ingredientes para o formato CSV
    private String converterIngredientesParaCSV(List<Ingrediente> ingredientes) {
        StringBuilder sb = new StringBuilder();
        for (Ingrediente ingrediente : ingredientes) {
            sb.append(ingrediente.getNome())
                    .append(":")
                    .append(ingrediente.getQuantidade())  // A quantidade agora é double
                    .append(ingrediente.getUnidadeMedida())
                    .append(";");  // Separando ingredientes por ";"
        }
        return sb.toString();
    }

    // Método para carregar as fichas técnicas do CSV (caso necessário)
    public void carregarFichasCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Aqui você pode processar as linhas do CSV para carregar as fichas técnicas
                System.out.println("Ficha Técnica: " + linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }
}