package main.java.com.confeitaria.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import main.java.com.confeitaria.model.Usuario;

public class Login {
    private static final  String LOGIN_CSV = "src/main/java/com/confeitaria/data/dadosLogin.csv"; // Endereço do arquivo csv
    private Map<String, String> dadosLogin = new HashMap<>();


    // Construtor: carrega os usuários do CSV ao iniciar
    public Login() {
        carregarDadosLoginDoCSV();
    }

    // Lê os dados do Login armazenados no CSV (email e senha) e salva no HashMap
    public void carregarDadosLoginDoCSV() {
        try {
            File arquivo = new File(LOGIN_CSV);
            if (!arquivo.exists()) {
                arquivo.getParentFile().mkdirs();
                arquivo.createNewFile();
            }

            // Lê arquivo CSV
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(LOGIN_CSV))) {
                String linha;
                reader.readLine(); // Ignora cabeçalho
                while ((linha = reader.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if (dados.length >= 2) {
                        dadosLogin.put(dados[0].trim(), dados[1].trim());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados de login: " + e.getMessage());
        }
    }


    // Verifica se o usuário já existe
    public boolean usuarioExiste(String email) {
        return dadosLogin.containsKey(email);
    }

    // Método para autenticar login
    public boolean fazerLogin(String email, String senha) {
        return dadosLogin.containsKey(email) && dadosLogin.get(email).equals(senha);
    }

    // Adiciona novo usuário ao HashMap e salva no CSV
    public boolean registrarUsuario(String email,String palPasse) {
        if (usuarioExiste(email)) {
            return false; // Já existe, não pode registrar novamente
        } else {
            dadosLogin.put(email, palPasse);
            salvarDadosLoginNoCSV(email, palPasse);
            System.out.println("Usuário cadastrado com sucesso.");
            return true; // Usuário cadastrado com sucesso
        }
    }


    // Salva novo usuário no CSV
    private void salvarDadosLoginNoCSV(String email, String senha) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGIN_CSV, true))) {
            writer.write(email + ";" + senha);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }


    // Obtém todos os usuários cadastrados (somente email para segurança)
    public Map<String, String> listarUsuarios() {
        return new HashMap<>(dadosLogin);
    }

}



