package sitema.confeitaria;

import javax.xml.crypto.Data;

// Classe para gerente fazer registo com suas informações
public class Gerente {
    private String nome;
    private Data dataNascimento;
    private String email;
    private String funçao;
    private int telemovel;
    private String morada;
    private int salario;
    private Data datacontrato;
    private int senhaHash;
    private boolean ativo;
    // Ativo ou inativo tem que acrescentar

    public boolean validarSenha(String senha) {
        //Comparar senha fornecida com a senha hash armazenada
        // Utilizar uma função de hash armazenada
        return senha.equals(senhaHash);
    }

    /*
    // O Gerente vai poder editar os dados do funcionario
    // Essa funçao ele podera editar o nome
    public void editarnomeFuncionario (String nome){
        Funcionario funcionario = funcionarios.get(nome);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }
     }
     */
}





