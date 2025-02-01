package main.java.com.confeitaria.service;

import main.java.com.confeitaria.model.ProdutoFinal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ProdutoFinalService {

    // Método para atualizar a quantidade em estoque e verificar o limite mínimo
    public static void atualizarEstoque(ProdutoFinal produto, int quantidade) {
        if (quantidade > 0) {
            produto.setQtdEstoque(produto.getQtdEstoque() + quantidade); // Adiciona a quantidade ao estoque
            registrarMovimentacao(produto, quantidade, "Entrada"); // Registra a movimentação como entrada
            System.out.println("Estoque atualizado: " + produto.getQtdEstoque());
            verificarLimiteMinimo(produto);
        } else {
            System.out.println("Erro: A quantidade deve ser maior que zero.");
        }
    }


    // Método para registrar a saída do estoque (venda)
    public static void registrarVenda(ProdutoFinal produto, int quantidadeVendida) {
        if (quantidadeVendida > 0 && quantidadeVendida <= produto.getQtdEstoque()) {
            produto.setQtdEstoque(produto.getQtdEstoque() - quantidadeVendida);
            registrarMovimentacao(produto, quantidadeVendida, "Saída");
            System.out.println("Venda realizada com sucesso! Estoque restante: " + produto.getQtdEstoque());
            verificarLimiteMinimo(produto);
        } else {
            System.out.println("Erro: Quantidade inválida.");
        }
    }


    // Método para verificar se o estoque está abaixo ou igual ao limite mínimo
    private static void verificarLimiteMinimo(ProdutoFinal produto) {
        if (produto.getQtdEstoque() < produto.getQtdMinima()) {
            System.out.println("Atenção: Estoque do produto: '" + produto.getNome() + "' abaixo do mínimo!");
        }
    }


    // Método para registrar uma movimentação no histórico
    private static void registrarMovimentacao(ProdutoFinal produto, int quantidade, String tipo) {
        String registro = "Tipo: " + tipo + " | Quantidade: " + quantidade + " | Data/Hora: " + LocalDateTime.now();
        produto.getHistoricoMovimentacoes().add(registro);
    }


    // Metodo para lançar o produto no sistema(registra a data de produção)
    public static void lancarProdutoNoSistema(ProdutoFinal produto) {
        // Atribui a data de produção como a data atual
        produto.setDataProdutocao(LocalDate.now());

        // Exibe msg de confirmação com a data de produção
        System.out.println("\nProduto registrado com sucesso!");
        System.out.println("Nome do produto: " + produto.getNome());
        System.out.println("Data de produção: " + produto.getDataProducao());
    }

    // Método para exibir o histórico de movimentações
    public static void exibirHistoricoMovimentacoes(ProdutoFinal produto) {
        System.out.println("\nHistórico de movimentações de estoque:");
        List<String> historico = produto.getHistoricoMovimentacoes();
        if (historico.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            for (String movimentacao : historico) {
                System.out.println(movimentacao);
            }
        }
    }


    //Função para exbir os dados
    public static void exibirDados(ProdutoFinal produto) {
        System.out.println("Dados deste produto final: ");
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Data de producao: " + produto.getDataProducao());
        System.out.println("Quantidade em estoque: " + produto.getQtdEstoque());
        System.out.println("Quantidade: " + produto.getQtdMinima());
    }
}