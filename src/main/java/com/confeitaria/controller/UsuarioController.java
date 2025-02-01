package main.java.com.confeitaria.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import main.java.com.confeitaria.model.Usuario;

public class UsuarioController {
    private static final String CSV_FILE = "./src/main/java/com/confeitaria/data/usuarios.csv";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    // Método para salvar usuário no "usuarios.csv"
    public void salvarUsuario(Usuario usuario) {
        try {
            File arquivo = new File(CSV_FILE);
            boolean arquivoExiste = arquivo.exists();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE, true))) {
                // Se o arquivo não existe, escreve o cabeçalho
                if (!arquivoExiste) {
                    writer.write("Nome;Cargo;Morada;DataNascimento;Salario;Telemovel;Email;PalavraPasse\n");
                }
                // Salva os dados do usuário no arquivo CSV
                writer.write(String.format("%s;%s;%s;%s;%.2f;%s;%s;%s\n",
                        usuario.getNome(),
                        usuario.getCargo(),
                        usuario.getMorada(),
                        usuario.getDataDeNascimento().format(FORMATTER),
                        usuario.getSalario(),
                        usuario.getTelemovel(),
                        usuario.getEmail(),
                        usuario.getPalPasse()));
            }
            System.out.println("Usuário salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    // Método para listar todos os usuários
    public static List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(CSV_FILE))) {
            String linha;
            reader.readLine(); // Ignora cabeçalho
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 8) {
                    // Converte os tipos corretamente antes de criar o objeto
                    String nome = dados[0];
                    Usuario.Cargo cargo = Usuario.Cargo.valueOf(dados[1]); // Converte String para Enum
                    String morada = dados[2];
                    LocalDate dataDeNascimento = LocalDate.parse(dados[3], FORMATTER);
                    double salario = Double.parseDouble(dados[4]);
                    String telemovel = dados[5];
                    String email = dados[6];
                    String palPasse = dados[7];

                    // Adiciona o usuário à lista
                    usuarios.add(new Usuario(nome, cargo, morada, dataDeNascimento, salario, telemovel, email, palPasse));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler usuários: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar usuários: " + e.getMessage());
        }
        return usuarios;
    }
}
