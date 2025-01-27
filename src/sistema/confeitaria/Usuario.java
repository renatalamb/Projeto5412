package sistema.confeitaria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    //Atributos
    private String nome;
    private static String palPasse;
    private String cargo;
    private String morada;
    private String email;
    private LocalDate data_de_nascimento; // Vamos usar apenas o ano de nascimento, por exemplo.
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private double salario;
    private int telemovel;  // Alterado para String, já que é um número de telefone.

    //Cria TipoUsuario como um tipo de dado
    public class CargoUsuario {
        enum Cargo {
            GERENTE, FUNCIONARIO
        }
    }

    // Lista para armazenar todas os usuarios criados
    private static List<Usuario> usuarios = new ArrayList<>();

    //Construtor
    public Usuario(String nome, String email, CargoUsuario.Cargo cargo,  LocalDate data_de_nascimento, String morada, int telemovel, double salario,String palPasse){
        this.nome = nome;
        this.palPasse = palPasse;
        this.cargo = cargo.toString();
        this.morada = morada;
        this.data_de_nascimento = data_de_nascimento;
        this.salario = salario;
        this.email = email;
        this.telemovel = telemovel;
    }

    //Metodo
    public static void criarUsuario() {
        Scanner sc = new Scanner(System.in);

        // Entrada de dados usuario: nome
        System.out.printf("Nome: ");
        String nome = sc.nextLine();

        // Lê email
        System.out.printf("Email: ");
        String email = sc.nextLine();

        // Usando a função para verificar se o já é email existente
        if (verificarEmailExistente(email)) {
            System.out.println("Erro: Email já cadastrado. Tente novamente com outro email.");
            return;
        }

        //Inicializa novocargo como nulo
        CargoUsuario.Cargo novocargo = null;

        // Lê cargo
        do {
            System.out.printf("Cargo (Gerente ou Funcionario): ");
            String cargo = sc.nextLine().trim(); // Remove espaços extras da entrada
            try {
                // Converte para maiúsculas para evitar erros de case e tenta converter para enum
                novocargo = CargoUsuario.Cargo.valueOf(cargo.toUpperCase());
                break; // Se a conversão foi bem-sucedida, sai do loop
            } catch (IllegalArgumentException e) {
                // Mensagem de erro se o cargo digitado for inválido
                System.out.println("Cargo inválido! Digite 'Gerente' ou 'Funcionario'.");
            }
        } while (true);

        // Lê data de nascimento
        LocalDate novadataNascimento;
        do {
            System.out.printf("Data de nascimento (dd/MM/yyyy): ");
            String dataNascimento = sc.nextLine();

            // Verifica se a data é válida
            if (verificarDataValida(dataNascimento)) {
                novadataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break; // Sai do loop se a data for válida
            } else {
                System.out.println("Por favor, insira uma data válida.");
            }
        } while (true);

        // Lê morada
        System.out.printf("Morada: ");
        String morada = sc.nextLine();

        // Lê telemovel
        System.out.printf("Telemovel: ");
        int telemovel = sc.nextInt();
        sc.nextLine();

        //Lê Salário
        System.out.printf("Salário: ");
        double salario = sc.nextDouble();
        sc.nextLine();

        // Lê palavra passe e confirma
        String palPasseConf;
        String palPasseTemp;
        do {
            System.out.printf("Digite palavra passe: ");
            palPasseTemp = sc.nextLine();
            System.out.print("Confirme palavra passe: ");
            palPasseConf = sc.nextLine();

            if (palPasseTemp.equals(palPasseConf)) {
                palPasse = palPasseTemp;
                System.out.println("Palavra passe registada com sucesso.");
            } else {
                System.out.println("Palavra passe não confirmada. Tente novamente.");
            }
        } while (!palPasseTemp.equals(palPasseConf));

        Usuario novoUsuario = new Usuario(nome, email, novocargo, novadataNascimento, morada, telemovel, salario, palPasse);
        usuarios.add(novoUsuario);

        System.out.println("Registo de utilizador feito com sucesso!");

        sc.close();
    }

    //Obter nome do usuario
    public String obterEmail() {
        return email;
    }

    //Metodo para veirifcar se o Usuario já existe
    public static boolean verificarEmailExistente(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.obterEmail().equalsIgnoreCase(email)) {
                return true; // Nome já existe
            }
        }
        return false; // Nome não existe
    }

    public static boolean verificarDataValida(String data) {
        try {
            // Formato esperado da data (dd/MM/yyyy)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Tenta converter a string para um objeto LocalDate
            LocalDate dataValida = LocalDate.parse(data, formatter);

            // Se a conversão for bem-sucedida, significa que a data é válida
            return true;

        } catch (DateTimeParseException e) {
            // Se não conseguir converter a data, significa que ela é inválida
            System.out.println("Erro: A data inserida não existe. Use dd/MM/yyyy.");
            return false;
        }
    }

    public static void main (String [] args) {
        criarUsuario();

    }

}