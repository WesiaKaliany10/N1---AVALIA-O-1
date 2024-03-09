/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package avaliação.n1;

/**
 * 
 *@08/03/2024
 * @author Wésia Kaliany <@kalianywesia2005@gmail.com>
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Prova1 {

    // Declaração das variáveis globais
    private static final Map<String, Double> produtos = new HashMap<>();
    private static final Map<String, Integer> quantidadeProdutos = new HashMap<>();
    private static double valorTotalConta = 0.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicialização dos produtos
        inicializarProdutos();

        int opcao;
        do {
            // Exibe o menu de opções
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Adicionar produto");
            System.out.println("2 - Remover produto");
            System.out.println("3 - Pagar parcialmente");
            System.out.println("4 - Pagar totalmente");
            System.out.println("5 - Sair");

            // Lê a opção escolhida pelo usuário
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> adicionarProduto(scanner);
                case 2 -> removerProduto(scanner);
                case 3 -> pagarParcialmente(scanner);
                case 4 -> pagarTotalmente(scanner);
                case 5 -> {
                    if (valorTotalConta > 0) {
                        System.out.println("Conta em aberto! Favor efetuar o pagamento.");
                        opcao = 0; // Volta ao menu principal
                    } else {
                        System.out.println("Encerrando o programa.");
                    }
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 5);
    }

    private static void inicializarProdutos() {
        // Inicialização dos produtos e seus respectivos valores
        produtos.put("Café Expresso", 0.75);
        produtos.put("Café Capuccino", 1.00);
        produtos.put("Leite com Café", 1.25);
        produtos.put("Café com Chocolate", 1.50); // Novo produto
        produtos.put("Pão de Queijo", 2.00); // Novo produto
        produtos.put("Misto Quente", 3.00); // Novo produto
        // Inclua mais produtos aqui conforme necessário
    }

    private static void adicionarProduto(Scanner scanner) {
        // Exibe os produtos disponíveis
        System.out.println("\nProdutos disponíveis:");
        for (String produto : produtos.keySet()) {
            System.out.println(produto + " - R$" + produtos.get(produto));
        }

        // Solicita ao usuário o produto a ser adicionado
        System.out.print("Digite o nome do produto que deseja adicionar: ");
        String produtoSelecionado = scanner.next();

        // Verifica se o produto existe na lista de produtos disponíveis
        if (produtos.containsKey(produtoSelecionado)) {
            // Incrementa a quantidade do produto adicionado
            quantidadeProdutos.put(produtoSelecionado, quantidadeProdutos.getOrDefault(produtoSelecionado, 0) + 1);
            // Atualiza o valor total da conta
            valorTotalConta += produtos.get(produtoSelecionado);
            System.out.println("Produto adicionado com sucesso!");
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    private static void removerProduto(Scanner scanner) {
        if (!quantidadeProdutos.isEmpty()) {
            // Exibe os produtos presentes na lista de compras
            System.out.println("\nProdutos na lista de compras:");
            for (String produto : quantidadeProdutos.keySet()) {
                System.out.println(produto + " - Quantidade: " + quantidadeProdutos.get(produto));
            }

            // Solicita ao usuário o produto a ser removido
            System.out.print("Digite o nome do produto que deseja remover: ");
            String produtoSelecionado = scanner.next();

            // Verifica se o produto existe na lista de compras
            if (quantidadeProdutos.containsKey(produtoSelecionado)) {
                // Decrementa a quantidade do produto removido
                int quantidadeAtual = quantidadeProdutos.get(produtoSelecionado);
                if (quantidadeAtual > 1) {
                    quantidadeProdutos.put(produtoSelecionado, quantidadeAtual - 1);
                } else {
                    quantidadeProdutos.remove(produtoSelecionado);
                }
                // Atualiza o valor total da conta
                valorTotalConta -= produtos.get(produtoSelecionado);
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Produto não encontrado na lista de compras!");
            }
        } else {
            System.out.println("Não há produtos na lista de compras!");
        }
    }

    private static void pagarParcialmente(Scanner scanner) {
        if (valorTotalConta > 0) {
            System.out.println("\nValor total da conta: R$" + valorTotalConta);
            System.out.print("Digite o valor que deseja pagar parcialmente: ");
            double valorPago = scanner.nextDouble();

            if (valorPago <= valorTotalConta) {
                // Calcula o troco, se houver
                double troco = valorPago - valorTotalConta;
                if (troco > 0) {
                    System.out.println("Troco: R$" + troco);
                }
                valorTotalConta -= valorPago; // Atualiza o valor total da conta
                System.out.println("Pagamento parcial realizado com sucesso!");
            } else {
                System.out.println("O valor pago é maior que o valor total da conta!");
            }
        } else {
            System.out.println("Não há valor pendente para pagar parcialmente!");
        }
    }

    private static void pagarTotalmente(Scanner scanner) {
        if (valorTotalConta > 0) {
            System.out.println("\nValor total da conta: R$" + valorTotalConta);
            System.out.print("Digite o valor total que deseja pagar: ");
            double valorPago = scanner.nextDouble();

            if (valorPago >= valorTotalConta) {
                // Calcula o troco, se houver
                double troco = valorPago - valorTotalConta;
                if (troco > 0) {
                    System.out.println("Troco: R$" + troco);
                }
                valorTotalConta = 0; // Zera o valor total da conta
                System.out.println("Pagamento total realizado com sucesso!");
            } else {
                System.out.println("O valor pago é menor que o valor total da conta!");
            }
        } else {
            System.out.println("Não há valor pendente para pagar!");
        }
    }
}