package main.java.com.confeitaria.service;

import main.java.com.confeitaria.model.Ingrediente;
import main.java.com.confeitaria.model.ProdutoFinal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class IngredienteService {

    private static final List<Ingrediente> listaIngredientes = new ArrayList<>();

    public void adicionarIngrediente(Ingrediente ingrediente) {
        Ingrediente existente = buscarIngrediente(ingrediente.getNome());
        if (existente != null) {
            existente.getQuantMinima();
        } else {
            listaIngredientes.add(ingrediente);
        }
    }



    public Ingrediente buscarIngrediente(String nome) {
        return listaIngredientes.stream()
                .filter(i -> i.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public List<Ingrediente> listarIngredientes() {
        return new ArrayList<>(listaIngredientes);
    }


    // Verificar ingredientes próximos do vencimento
    public static List<Ingrediente> verificarValidade() {
        LocalDate hoje = LocalDate.now();
        System.out.println("\n⚠ Ingredientes próximos da validade:");

        boolean temVencimentos = false;
        for (Ingrediente ingrediente : listaIngredientes) {
            if (ingrediente.dataValidade.isBefore(hoje.plusDays(7))) {
                System.out.println("⚠ " + ingrediente.getNome() + " vence em " + ingrediente.dataValidade.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                temVencimentos = true;
            }
        }

        if (!temVencimentos) {
            System.out.println("✅ Nenhum ingrediente próximo da validade!");
        }
        return null;
    }

    public static class ProdutoFinalService {

        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        private List<ProdutoFinal> produtos;

        // Construtor
        public ProdutoFinalService() {
            produtos = new ArrayList<>();
        }

        // Adiciona um novo produto à lista
        public void adicionarProduto(ProdutoFinal produto) {
            produtos.add(produto);
        }

        // Busca um produto pelo nome
        public ProdutoFinal buscarProdutoPorNome(String nome) {
            for (ProdutoFinal produto : produtos) {
                if (produto.getNome().equalsIgnoreCase(nome)) {
                    return produto;
                }
            }
            return null; // Caso não encontre
        }

        // Atualiza o estoque de um produto
        public boolean atualizarEstoque(ProdutoFinal produto, int quantidade, boolean flag) {
            if (quantidade > 0) {
                produto.setQtdEstoque(produto.getQtdEstoque() + quantidade); // Adiciona ao estoque
                registrarMovimentacao(produto, quantidade, "Entrada");
                verificarLimiteMinimo(produto);
            } else {
                System.out.println("Erro: A quantidade deve ser maior que zero.");
            }
            return flag;
        }

        // Registra uma venda de um produto
        public void registrarVenda(ProdutoFinal produto, int quantidadeVendida) {
            if (quantidadeVendida > 0 && quantidadeVendida <= produto.getQtdEstoque()) {
                produto.setQtdEstoque(produto.getQtdEstoque() - quantidadeVendida);
                registrarMovimentacao(produto, quantidadeVendida, "Saída");
                verificarLimiteMinimo(produto);
            } else {
                System.out.println("Erro: Quantidade inválida ou insuficiente.");
            }
        }

        // Verifica se o estoque do produto está abaixo do mínimo
        private void verificarLimiteMinimo(ProdutoFinal produto) {
            if (produto.getQtdEstoque() < produto.getQtdMinima()) {
                System.out.println("Atenção: Estoque do produto '" + produto.getNome() + "' abaixo do mínimo!");
            }
        }

        // Registra uma movimentação (entrada ou saída)
        private void registrarMovimentacao(ProdutoFinal produto, int quantidade, String tipo) {
            if (produto.getHistoricoMovimentacoes() == null) {
                produto.setHistoricoMovimentacoes(new ArrayList<>());
            }
            String registro = "Tipo: " + tipo + " | Quantidade: " + quantidade + " | Data/Hora: " + LocalDateTime.now();
            produto.getHistoricoMovimentacoes().add(registro);
        }

        // Exibe o histórico de movimentações de um produto
        public void exibirHistoricoMovimentacoes(ProdutoFinal produto) {
            List<String> historico = produto.getHistoricoMovimentacoes();
            if (historico == null || historico.isEmpty()) {
                System.out.println("Nenhuma movimentação registrada.");
            } else {
                for (String movimentacao : historico) {
                    System.out.println(movimentacao);
                }
            }
        }

        // Exibe os dados de um produto
        public void exibirProduto(ProdutoFinal produto) {
            System.out.println("Produto: " + produto.getNome());
            System.out.println("Estoque: " + produto.getQtdEstoque());
            System.out.println("Mínimo: " + produto.getQtdMinima());
        }
    }
}
