package main.java.com.confeitaria.service;

import main.java.com.confeitaria.model.ProdutoFinal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProdutoFinalService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Método para atualizar a quantidade em estoque e verificar o limite mínimo
    public void atualizarEstoque(ProdutoFinal produto, int quantidade) {
        if (quantidade > 0) {
            produto.setQtdEstoque(produto.getQtdEstoque() + quantidade); // Adiciona ao estoque
            registrarMovimentacao(produto, quantidade, "Entrada");
            System.out.println("Estoque atualizado: " + produto.getQtdEstoque());
            verificarLimiteMinimo(produto);
        } else {
            System.out.println("Erro: A quantidade deve ser maior que zero.");
        }
    }

    // Método para registrar a saída do estoque (venda)
    public void registrarVenda(ProdutoFinal produto, int quantidadeVendida) {
        if (quantidadeVendida > 0 && quantidadeVendida <= produto.getQtdEstoque()) {
            produto.setQtdEstoque(produto.getQtdEstoque() - quantidadeVendida);
            registrarMovimentacao(produto, quantidadeVendida, "Saída");
            System.out.println("Venda realizada com sucesso! Estoque restante: " + produto.getQtdEstoque());
            verificarLimiteMinimo(produto);
        } else {
            System.out.println("Erro: Quantidade inválida ou insuficiente.");
        }
    }

    // Método para verificar se o estoque está abaixo ou igual ao limite mínimo
    private void verificarLimiteMinimo(ProdutoFinal produto) {
        if (produto.getQtdEstoque() < produto.getQtdMinima()) {
            System.out.println("Atenção: Estoque do produto '" + produto.getNome() + "' abaixo do mínimo!");
        }
    }

    // Método para registrar uma movimentação no histórico
    private void registrarMovimentacao(ProdutoFinal produto, int quantidade, String tipo) {
        if (produto.getHistoricoMovimentacoes() == null) {
            produto.setHistoricoMovimentacoes(new ArrayList<>()); // Garante lista não nula
        }
        String registro = "Tipo: " + tipo + " | Quantidade: " + quantidade + " | Data/Hora: " + LocalDateTime.now();
        produto.getHistoricoMovimentacoes().add(registro);
    }

    // Método para lançar o produto no sistema (registra a data de produção)
    public void lancarProdutoNoSistema(ProdutoFinal produto) {
        produto.setDataProducao(LocalDate.now());
        System.out.println("\nProduto registrado com sucesso!");
        System.out.println("Nome do produto: " + produto.getNome());
        System.out.println("Data de produção: " + produto.getDataProducao());
    }

    // Método para exibir o histórico de movimentações
    public void exibirHistoricoMovimentacoes(ProdutoFinal produto) {
        System.out.println("\nHistórico de movimentações de estoque:");
        List<String> historico = produto.getHistoricoMovimentacoes();
        if (historico == null || historico.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            for (String movimentacao : historico) {
                System.out.println(movimentacao);
            }
        }
    }

    // Método para exibir os dados do produto
    public void exibirDados(ProdutoFinal produto) {
        System.out.println("\nDados do produto:");
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Data de produção: " + produto.getDataProducao());
        System.out.println("Quantidade em estoque: " + produto.getQtdEstoque());
        System.out.println("Quantidade mínima: " + produto.getQtdMinima());
    }
}
