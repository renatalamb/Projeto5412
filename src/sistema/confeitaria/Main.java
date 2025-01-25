package sistema.confeitaria;

import javax.xml.crypto.Data;

public class Main {


    // Classe para gerente fazer registo com suas informações
    public static class Gerente {
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


    }
}
