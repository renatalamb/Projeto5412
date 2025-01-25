package sistema.confeitaria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    //Atributos
    private String nome;
    private String palPasse;
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
        System.out.printf("Digite o nome do usuario: ");
        String nome = sc.nextLine();

        // Lê email
        System.out.printf("Digite o email do usuario: ");
        String email = sc.nextLine();

        // Usando a função para verificar se o já é email existente
        verificarEmailExistente(email);

        // Lê email
        System.out.printf("Cargo: ");
        String cargo = sc.nextLine();
        CargoUsuario.Cargo novocargo = CargoUsuario.Cargo.valueOf(cargo.toUpperCase());

        /*
        try {
            CargoUsuario.Cargo novocargo = CargoUsuario.Cargo.valueOf(cargo.toUpperCase());
            System.out.println("Você digitou: " + cargo);
        } catch (IllegalArgumentException e) {
            System.out.println("Cargo inválido!");
        }

         */

        // Lê data de nascimento
        System.out.printf("Data de nascimento: ");
        String dataNascimento = sc.nextLine();
        LocalDate novadataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

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

        // Lê palavra passe
        System.out.printf("Digite palPasse do usuario: ");
        String palPasse = sc.nextLine();

        Usuario novoUsuario = new Usuario(nome, email, novocargo, novadataNascimento, morada, telemovel, salario, palPasse);
        usuarios.add(novoUsuario);

        System.out.println("Registo de utilizador feito com sucesso!");

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
    public static void main (String [] args) {
        criarUsuario();

    }

}