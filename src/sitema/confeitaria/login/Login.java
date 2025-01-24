package sitema.confeitaria.login;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Login {
    public static void main(String[] args) {
        // Criando um HashMap para armazenar os usuários
        Map<String, String> usuarios = new HashMap<>();

        // Adicionando usuários (chave = nome, valor = senha)
        usuarios.put("usuario1", "senha123");
        usuarios.put("usuario2", "senha456");

        // Lendo dados do usuário
        Scanner scanner = new Scanner(System.in);
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Verificando as credenciais
        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(senha)) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }
    }
}



