package sistema.confeitaria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario {


    // Atributos (ou variáveis de instância)
    String nome;
    String morada;
    String email;
    LocalDate data_de_nascimento; // Vamos usar apenas o ano de nascimento, por exemplo.
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    double salario;
    int telemovel;  // Alterado para String, já que é um número de telefone.

    // Construtor
    public Funcionario(String nome, String morada, LocalDate data_de_nascimento, double salario, String email, int
            telemovel){
        this.nome = nome;
        this.morada = morada;
        this.data_de_nascimento = data_de_nascimento;
        this.salario = salario;
        this.email = email;
        this.telemovel = telemovel;
    }

    // Metodo
    public void exibirInformacoes () {
        System.out.println("Nome: " + nome);
        System.out.println("Morada: " + morada);
        System.out.println("Ano de Nascimento: " + data_de_nascimento.format(formatter));
        System.out.println("Salário: " + salario);
        System.out.println("Email: " + email);
        System.out.println("Telemóvel: " + telemovel);
    }

    public static void main(String[] args) {

        String data="20/07/2000";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data_nasc = LocalDate.parse(data, formatter);

        Funcionario funcionario= new Funcionario("Branca", "rua xpto", data_nasc, 1000, "123@gmail.com", 911119191);

        funcionario.exibirInformacoes();

    }

}