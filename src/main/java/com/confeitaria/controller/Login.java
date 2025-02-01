package main.java.com.confeitaria.controller;
import main.java.com.confeitaria.model.Usuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Login {
    private Map<String, String> usuarios = new HashMap<>();
    private Scanner sc;


    // Construtor
    public Login() {
    // Inicia o HashMap com dados dos usuarios cadastrados
        carregarUsuariosNoMap();
    }

    public void carregarUsuariosNoMap() {
        //ler do arquivo csv
    }

    public void criarUsuario() {

    }




    //Lê dados do usuário para login
    public void fazerLogin() {
        if ()
    }

    //private boolean estaLogado = false;



    // Adiciona novos usuários com validação
    public void verificaUsuarioExiste (String email, String palPasse) {
        if (usuarios.containsKey(email)) {
            System.out.println("Erro: Email já cadastrado!");
        } else {
            usuarios.put(email, palPasse);
            System.out.println("Usuário adicionado com sucesso!");
        }
    }


    // Menu principal
    public void menu() {
        int opcao = 0;
        do {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Registar funcionário");
            System.out.println("2. Fazer Login");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            //opcao = sc.nextInt();
            //sc.nextLine(); // Limpar o buffer do teclado

            String input = sc.nextLine();

            try {
                opcao = Integer.parseInt(input); // Converte para inteiro
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                continue;
            }

            switch (opcao) {
                case 1:
                    Usuario.criarUsuario(); //Chama metodo de criação de usuario
                    carregarUsuariosNoMap();
                    break;
                case 2:
                    fazerLogin();
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);
    }

}



