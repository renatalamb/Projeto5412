package sistema.confeitaria.login;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Login {
    private Map<String, String> usuarios = new HashMap<>();
    // Construtor
    public Login() {
// Adiciona um usuário inicial ao sistema
        usuarios.put("admin", "senha123");
    }

    // Controlar estado da autenticação do usuario
    private boolean estaLogado = false;

    // Adiciona novos usuários com validação
    public void adicionarUsuario(String usuario, String palPasse) {
        if (usuarios.containsKey(usuario)) {
            System.out.println("Erro: O usuário já existe!");
        } else {
            usuarios.put(usuario, palPasse);
            System.out.println("Usuário adicionado com sucesso!");
        }
    }
    // Lê dados do usuário para login
    public void lerDadosEVerificar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String palPasse = scanner.nextLine();
// Verifica as credenciais
        if (usuarios.containsKey(usuario) &&
                usuarios.get(usuario).equals(palPasse)) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }
    }
    // Menu principal
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Fazer Login");
            System.out.println("2. Adicionar Usuário");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado
            switch (opcao) {
                case 1:
                    lerDadosEVerificar();
                    break;
                case 2:
                    System.out.print("Novo usuário: ");
                    String novoUsuario = scanner.nextLine();
                    System.out.print("Nova senha: ");
                    String novaSenha = scanner.nextLine();
                    adicionarUsuario(novoUsuario, novaSenha);
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);
    }
    // Método principal
    public static void main(String[] args) {
        Login login = new Login(); // Cria o objeto Login
        login.menu(); // Executa o menu
    }
}

