package main.java.com.confeitaria.repository;

import main.java.com.confeitaria.model.Usuario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LoginRepository {
    // Caminho do arquivo
    private static final String CSV_FILE = "./src/main/java/com/confeitaria/data/dadosLogin.csv";
    private static final String BIN_FILE = "./src/main/java/com/confeitaria/data/dadosLogin.dat";


    // Exportar senha e palavra passa para arquivo
    public static void adicionarDadosLogin(List<Usuario> usuarios) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE))) {
            writer.write("Email; Senha\n");
            for (Usuario usuario : usuarios) {
                writer.write(String.format("%s;%s\n",
                        usuario.getEmail(),
                        usuario.getPalPasse()));
            }
            System.out.println("Dados exportados para CSV com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao exportar para CSV: " + e.getMessage());
        }
    }



}
