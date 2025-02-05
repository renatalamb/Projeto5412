package main.java.com.confeitaria.controller;

import main.java.com.confeitaria.model.Bebida;
import main.java.com.confeitaria.model.ProdutoFinal;
import main.java.com.confeitaria.repository.BebidaRepository;
import main.java.com.confeitaria.service.BebidaService;
import main.java.com.confeitaria.service.ProdutoFinalService;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

public class BebidaController {

    public BebidaService service;
    private BebidaService bebidaService;
    private BebidaRepository bebidaRepository;
    private Map<String, Bebida> bebidasMap; // Mapa para armazenar bebidas por nome

    // Construtor do Controller com dependências
    public BebidaController(BebidaService bebidaService, BebidaRepository bebidaRepository) {
        this.bebidaService = bebidaService;
        this.bebidaRepository = bebidaRepository;
        this.bebidasMap = new HashMap<>(); // Inicializando o mapa de bebidas
    }

    // Método para criar uma nova bebida
    public Bebida criarBebida(String nomeBebida, int quantidade, int quantidadeMin, LocalDate dataValidade) {
        Bebida bebida = new Bebida(nomeBebida, quantidade, quantidadeMin, dataValidade);
        bebidasMap.put(nomeBebida, bebida); // Adiciona bebida no map
        bebidaRepository.salvarBebidasCSV(bebida); // Salva a bebida no CSV
        System.out.println("Bebida registrada e salva com sucesso!");
        return bebida;
    }

    // Atualizar estoque e registrar movimentação
    public void atualizarEstoque(String nomeBebida, int quantidade, boolean entrada) {
        Bebida bebida = bebidasMap.get(nomeBebida); // Busca a bebida pelo nome
        if (bebida != null) {
            boolean sucesso = bebidaService.atualizarEstoqueBebida(bebida, quantidade, entrada);
            if (sucesso) {
                bebidaRepository.registrarMovimentacaoCSV(bebida, (entrada ? "Entrada de " : "Saída de ") + quantidade);
                bebidaRepository.salvarBebidasCSV(bebidaRepository.carregarBebidasCSV()); // Atualiza as bebidas no CSV
            } else {
                System.out.println("Erro: Estoque insuficiente para saída de " + quantidade);
            }
        } else {
            System.out.println("Bebida não encontrada!");
        }
    }

    // Mostrar histórico de movimentações
    public void mostrarHistorico(String nomeBebida) {
        Bebida bebida = bebidasMap.get(nomeBebida); // Busca a bebida pelo nome
        if (bebida != null) {
            bebidaService.exibirHistoricoMovimentacoesBebidas(bebida);
        } else {
            System.out.println("Bebida não encontrado!");
        }
    }

    // Mostrar dados do produto
    public void mostrarDados(String nomeBebida) {
        Bebida bebida = bebidasMap.get(nomeBebida); // Busca a bebida pelo nome
        if (bebida != null) {
            bebidaService.exibirBebida(bebida);
        } else {
            System.out.println("Bebida não encontrado!");
        }
    }

    // Método para listar todos os produtos cadastrados
    public void listarBebidas() {
        if (bebidasMap.isEmpty()) {
            System.out.println("Não há bebidas cadastradas.");
        } else {
            System.out.println("Bebidas cadastradas:");
            for (Bebida bebida : bebidasMap.values()) {
                bebidaService.exibirBebida(bebida); // Exibe todos as bebidas
            }
        }
    }






}
