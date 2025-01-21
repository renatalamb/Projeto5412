package sitema.confeitaria;

import java.time.LocalDate;
import java.util.Scanner;

public class Ingrediente {
    //Atributos
    private static int cont = 1;
    private int codIngrediente;
    private String NomeIngrediente;
    private LocalDate dataValidade;
    private int quantidade;

    //Método para cadastrar um ingrediente (lê dados do usuário)
    public void cadastroIngrediente(){
        this.codIngrediente = cont++; //Gera código automaticamente

        Scanner sc = new Scanner(System.in);

        //Lê nome do ingrediente
        System.out.print("Nome do ingrediente: ");
        this.NomeIngrediente = sc.nextLine();

        //Lê data de validade
        System.out.print("Data de validade (yyyy-MM-dd): ");
        String dataInput = sc.nextLine();
        this.dataValidade = LocalDate.parse(dataInput); //Converte String para LocalDate

        //Lê quantidade
        System.out.print("Quantidade estoque (un ou kg): ");
        this.quantidade = sc.nextInt();

        System.out.println("Ingrediente cadastrado com sucesso!");

        sc.close();
    }


}
