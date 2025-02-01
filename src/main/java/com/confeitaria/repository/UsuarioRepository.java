package main.java.com.confeitaria.repository;

import main.java.com.confeitaria.model.Usuario;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UsuarioRepository {
    // Caminho do arquivo
    private static final String CSV_FILE = "./src/main/java/com/confeitaria/data/usuarios.csv";
    private static final String BIN_FILE = "./src/main/java/com/confeitaria/data/usuarios.dat";

    // Exportar dados de usuario para arquivo
    public static void adicionarDadosLogin(List<Usuario> usuarios) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE))) {
            writer.write("Nome; PalavraPasse; Cargo; Morada; DataNasc; Salario; Email; Telemóvel\n");
            for (Usuario usuario : usuarios) {
                writer.write(String.format("%s; %s; %s; %s; %s; %.2f; %s; %s\n",
                        usuario.getNome(),
                        usuario.getPalPasse(),
                        usuario.getCargo(),
                        usuario.getMorada(),
                        usuario.getDataDeNascimento(),
                        usuario.getSalario(),
                        usuario.getEmail(),
                        usuario.getTelemovel()));
            }
            System.out.println("Dados exportados para CSV com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao exportar para CSV: " + e.getMessage());
        }
    }

}
