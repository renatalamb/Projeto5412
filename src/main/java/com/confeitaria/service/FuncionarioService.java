package main.java.com.confeitaria.service;

import main.java.com.confeitaria.model.Funcionario;
import main.java.com.confeitaria.repository.FuncionarioRepository;

import java.util.List;

public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService() {
        this.repository = new FuncionarioRepository();
    }

    // Método para validar e cadastrar funcionário
    public void cadastrarFuncionario(Funcionario funcionario) {
        if (validarFuncionario(funcionario)) {
            repository.salvar(funcionario);
        } else {
            System.out.println("Erro: Todos os campos obrigatórios devem ser preenchidos!");
        }
    }

    // Método para validar os dados do funcionário
    private boolean validarFuncionario(Funcionario funcionario) {
        return funcionario.getNome() != null && !funcionario.getNome().isEmpty()
                && funcionario.getEmail() != null && !funcionario.getEmail().isEmpty()
                && funcionario.getDataDeNascimento() != null
                && funcionario.getSalario() > 0
                && funcionario.getTelemovel() > 0;
    }

    // Método para listar funcionários
    public List<Funcionario> listarFuncionarios() {
        return repository.listarTodos();
    }
}
