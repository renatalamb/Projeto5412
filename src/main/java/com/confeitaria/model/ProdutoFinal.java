package main.java.com.confeitaria.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutoFinal {

    //Atributos da classe ProdutoFinal
    private String nome;
    private LocalDate dataProducao;
    private int qtdEstoque; //Quantidade atual no estoque
    private int qtdMinima; //Quantidade mínima permitida no estoque
    private Categoria categoria; //Relacionamento com a classe Categoria

    // Lista para armazenar o histórico de movimentações de estoque
    private List<String> historicoMovimentacoes;

    //Construtor
    public ProdutoFinal(String nome, int qtdEstoque, int qtdMinima, Categoria categoria) {
        this.nome = nome;
        this.qtdEstoque = qtdEstoque;
        this.qtdMinima = qtdMinima;
        this.categoria = categoria; //Atribui a categoria ao produto
        this.historicoMovimentacoes = new ArrayList<>(); // Inicializa o histórico como uma lista vazia
    }

    // Método para criar um novo produto
    public static ProdutoFinal criarProduto() {
        Scanner sc = new Scanner(System.in);

        //Chama metodo para escolher categoria
        Categoria categoriaEscolhida = selecionarCategoria();

        //Se categoria for inválida, não cria o produto
        if (categoriaEscolhida == null) {
            return null;
        }

        // Lê os dados do produto
        System.out.print("Nome do produto: ");
        String nomeProduto = sc.nextLine();

        // Usar DateTimeFormatter para converter a data para o formato correto
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //System.out.print("Data de produção (dd/MM/yyyy): ");
        //String dataProducaoStr = sc.nextLine();
        //LocalDate dataProducao = LocalDate.parse(dataProducaoStr, formatter);

        //System.out.print("Data de validade (dd/MM/yyyy): ");
        //String dataValidadeStr = sc.nextLine();
        //LocalDate dataValidade = LocalDate.parse(dataValidadeStr, formatter);

        System.out.print("Quantidade em estoque: ");
        int qtdEstoque = sc.nextInt();

        System.out.print("Quantidade mínima: ");
        int qtdMinima = sc.nextInt();

        // Cria um novo produto final
        ProdutoFinal produto = new ProdutoFinal(nomeProduto, qtdEstoque, qtdMinima, categoriaEscolhida);

        // Chama metodo para lançar o produto no sistema
        produto.lancarProdutoNoSistema();

        return produto;
    }

    //Método para selecionar uma categoria
    public static Categoria selecionarCategoria() {
        Scanner sc = new Scanner(System.in);

        // Exibe as categorias existentes
        System.out.println("Categorias existentes:");
        List<Categoria> categorias = Categoria.getCategorias(); // Obtém a lista de categorias existentes

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada. Impossível criar um produto.");
            return null;
        }

        // Exibe todas as categorias disponíveis
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i).getNome());
        }

        // Solicita ao usuário a escolha da categoria
        System.out.print("Digite o número da categoria desejada: ");
        int escolhaCategoria = sc.nextInt();
        sc.nextLine();

        // Verifica se a escolha é válida
        if (escolhaCategoria < 1 || escolhaCategoria > categorias.size()) {
            System.out.println("Categoria inválida. Produto não será criado.");
            return null;
        }

        // Obtém e retorna a categoria escolhida
        return categorias.get(escolhaCategoria - 1);
    }


    // Metodo para lançar o produto no sistema(registra a data de produção)
    public void lancarProdutoNoSistema() {
        // Atribui a data de produção como a data atual
        this.dataProducao = LocalDate.now();

        // Exibe msg de confirmação com a data de produção
        System.out.println("\nProduto registrado com sucesso!");
        System.out.println("Nome do produto: " + nome);
        System.out.println("Categoria: " + categoria.getNome());
        System.out.println("Data de produção: " + dataProducao);
    }

    // Método para atualizar a quantidade em estoque e verificar o limite mínimo
    public void atualizarEstoque(int quantidade) {
        if (quantidade > 0) {
            this.qtdEstoque += quantidade; // Adiciona a quantidade ao estoque
            registrarMovimentacao(quantidade, "Entrada"); // Registra a movimentação como entrada

            // Mensagem de confirmação da entrada
            System.out.println("\nEntrada registrada com sucesso!");
            System.out.println("Produto: " + nome);
            System.out.println("Categoria: " + categoria.getNome());
            System.out.println("Quantidade adicionada: " + quantidade);
            System.out.println("Quantidade total em estoque: " + qtdEstoque);

            // Verifica automaticamente se a quantidade atingiu o limite mínimo
            verificarLimiteMinimo();
        } else {
            System.out.println("Erro: A quantidade deve ser maior que zero.");
        }
    }

    // Método para verificar se o estoque está abaixo ou igual ao limite mínimo
    private void verificarLimiteMinimo() {
        if (this.qtdEstoque < this.qtdMinima) {
            System.out.println("Atenção: A quantidade em estoque do produto '" + nome
                    + "'  está abaixo do limite mínimo!");
        } else if (this.qtdEstoque == this.qtdMinima) {
            System.out.println("Atenção: A quantidade em estoque do produto '" + nome
                    + "'  atingiu o limite mínimo!");
        } else if (this.qtdEstoque > this.qtdMinima) {
            System.out.println("A quantidade em estoque do produto '" + nome
                    + "'  está acima do limite mínimo!");
        }
    }

    // Método para registrar uma movimentação no histórico
    private void registrarMovimentacao(int quantidade, String tipo) {
        String registro = String.format("Tipo: %s | Quantidade: %d | Data/Hora: %s",
                tipo, quantidade, LocalDateTime.now());
        historicoMovimentacoes.add(registro); // Adiciona o registro ao histórico
    }


    // Método para registrar a saída do estoque (venda)
    public void registrarVenda(int quantidadeVendida) {
        if (quantidadeVendida > 0) {
            if (quantidadeVendida <= qtdEstoque) {
                this.qtdEstoque -= quantidadeVendida; // Subtrai a quantidade vendida do estoque
                registrarMovimentacao(quantidadeVendida, "Saída"); // Registra a movimentação como saída

                // Mensagem de confirmação da venda
                System.out.println("\nVenda registrada com sucesso!");
                System.out.println("Produto: " + nome);
                System.out.println("Categoria: " + categoria.getNome());
                System.out.println("Quantidade vendida: " + quantidadeVendida);
                System.out.println("Quantidade restante em estoque: " + qtdEstoque);

                // Verifica automaticamente se o estoque atingiu o limite mínimo
                verificarLimiteMinimo();
            } else {
                System.out.println("Erro: Quantidade vendida excede a quantidade disponível em estoque.");
            }
        } else {
            System.out.println("Erro: A quantidade vendida deve ser maior que zero.");
        }
    }


    // Método para exibir o histórico de movimentações
    public void exibirHistoricoMovimentacoes() {
        System.out.println("\nHistórico de movimentações de estoque:");
        if (historicoMovimentacoes.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            for (String movimentacao : historicoMovimentacoes) {
                System.out.println(movimentacao);
            }
        }
    }


    //Função para exbir os dados
    public void exibirDados() {
        System.out.println("Dados deste produto final: ");
        System.out.println("Nome: " + nome);
        System.out.println("Categoria " + categoria.getNome());
        System.out.println("Data de producao: " + dataProducao);
        System.out.println("Quantidade: " + qtdEstoque);
        System.out.println("Quantidade: " + qtdMinima);
    }


    //Funções getter e setter dos atributos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataProducao() {
        return dataProducao;
    }

    public void setDataProducao(LocalDate dataProducao) {
        this.dataProducao = dataProducao;
    }


    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public static void main(String[] args) {
        // Passo 1: Criar categorias
        Categoria categoriaBolos = new Categoria("Bolos", "Categoria de bolos");
        Categoria categoriaDoces = new Categoria("Doces", "Categoria de doces");

        // Adicionar as categorias à lista
        Categoria.getCategorias().add(categoriaBolos);
        Categoria.getCategorias().add(categoriaDoces);

        // Passo 2: Criar um novo produto
        ProdutoFinal produto = ProdutoFinal.criarProduto();

        // Passo 3: Exibir dados do produto
        if (produto != null) {
            produto.exibirDados();

            // Passo 4: Testar a atualização do estoque
            produto.atualizarEstoque(10); // Adicionando 10 unidades ao estoque

            // Passo 5: Testar a venda de produtos
            produto.registrarVenda(5); // Vendendo 5 unidades

            // Passo 6: Exibir o histórico de movimentações
            produto.exibirHistoricoMovimentacoes();
        }
    }


}

