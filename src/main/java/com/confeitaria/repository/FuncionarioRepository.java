package main.java.com.confeitaria.repository;


import main.java.com.confeitaria.model.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {
    private final List<Funcionario> funcionarios = new ArrayList<>();

    // Método para salvar funcionário
    public void salvar(Funcionario funcionario) {
        funcionarios.add(funcionario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    // Método para listar todos os funcionários
    public List<Funcionario> listarTodos() {
        return funcionarios;
    }
}