import java.time.LocalDate;

public class ProdutoFinalGUI extends JFrame {

    private ProdutoFinalService produtoService;
    private ProdutoFinal produto;
    private JTextField nomeField, estoqueField, minimoField;
    private JTextArea historicoArea;

    public ProdutoFinalGUI() {
        produtoService = new ProdutoFinalService();
        // Corrigido: Instanciando o ProdutoFinal com todos os parâmetros necessários
        produto = new ProdutoFinal("Produto 1", 50, 10);  // Produto inicial (exemplo)

        // Verifique a criação do objeto Ingrediente também
        Ingrediente ingrediente = new Ingrediente("Farinha", 100, "kg", 10, LocalDate.now());
        inicializarComponentes();
    }


    private void inicializarComponentes() {
        setTitle("Gestão de Estoque - Produto Final");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel nomeLabel = new JLabel("Nome do Produto:");
        nomeField = new JTextField(produto.getNome());

        JLabel estoqueLabel = new JLabel("Quantidade em Estoque:");
        estoqueField = new JTextField(String.valueOf(produto.getQtdEstoque()));

        JLabel minimoLabel = new JLabel("Quantidade Mínima:");
        minimoField = new JTextField(String.valueOf(produto.getQtdMinima()));

        JButton atualizarEstoqueButton = new JButton("Atualizar Estoque");
        JButton registrarVendaButton = new JButton("Registrar Venda");
        JButton exibirHistoricoButton = new JButton("Exibir Histórico");

        historicoArea = new JTextArea(10, 30);
        historicoArea.setEditable(false);

        // Adiciona os componentes ao painel
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(estoqueLabel);
        panel.add(estoqueField);
        panel.add(minimoLabel);
        panel.add(minimoField);
        panel.add(atualizarEstoqueButton);
        panel.add(registrarVendaButton);
        panel.add(exibirHistoricoButton);

        JScrollPane scrollPane = new JScrollPane(historicoArea);
        panel.add(new JLabel("Histórico de Movimentações:"));
        panel.add(scrollPane);

        add(panel);

        // Ações para os botões
        atualizarEstoqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade para entrada no estoque:"));
                    produtoService.atualizarEstoque(produto, quantidade);
                    estoqueField.setText(String.valueOf(produto.getQtdEstoque()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Quantidade inválida!");
                }
            }
        });

        registrarVendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int quantidadeVendida = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade para venda:"));
                    produtoService.registrarVenda(produto, quantidadeVendida);
                    estoqueField.setText(String.valueOf(produto.getQtdEstoque()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Quantidade inválida!");
                }
            }
        });

        exibirHistoricoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                produtoService.exibirHistoricoMovimentacoes(produto);
                historicoArea.setText(""); // Limpa a área antes de atualizar
                for (String movimentacao : produto.getHistoricoMovimentacoes()) {
                    historicoArea.append(movimentacao + "\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProdutoFinalGUI janela = new ProdutoFinalGUI();
                janela.setVisible(true);
            }
        });
    }
}
