import java.util.ArrayList;
import java.util.Scanner;

public class Sistema_de_controle_de_estoque {

    // Códigos cores no terminal
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    /*
        Função para selecionar uma opção do menu.
     */
    static void menu(ArrayList<Produto> lista, int codigo) {
        System.out.println("""
                \n[1] Adicionar produto
                
                [2] Acessar relatórios
                
                [3] Registrar venda
                
                [4] Finalizar Sistema""");

        Scanner sc = new Scanner(System.in);
        int scMenu = sc.nextInt();
        
        switch (scMenu) {
            case 1:
                System.out.println("Iniciando adição de produto\n");
                adicionar(lista, codigo);
                break;
            case 2:
                escolherRelatorio(lista, codigo);
                break;
            case 3:
                registrarVenda(lista, codigo);
                break;
            default:
                if (scMenu == 4) {
                    System.out.println("Sistema Finalizado");
                    return;
                }
                System.out.println("Opção inválida");
                menu(lista, codigo);
        }
    }
    /*
        Função para adicionar um produto novo ao estoque ou apenas aumentar a sua quantidade
     */
    static void adicionar(ArrayList<Produto> lista, int codigo) {
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("Nome:");
        String nome = sc.nextLine();

        // Verifica se o produto já existe
        boolean produtoExiste = false;
        for (Produto p : lista) {
            if (p.getNome().equalsIgnoreCase(nome)) { // Ignora maiúsculas e minúsculas
                System.out.println("Produto já cadastrado!\nAdicionando ao estoque existente.");

                System.out.println("Quantidade a adicionar:");
                int quantidadeAdicional = sc.nextInt();
                sc.nextLine();

                p.setQuantidade(p.getQuantidade() + quantidadeAdicional); // Soma a quantidade
                produtoExiste = true;
                break; // Sai do loop, pois já encontrou o produto
            }
        }

        // Se o produto não existe, cadastra um novo
        if (!produtoExiste) {
            codigo++; // Incrementa o código para o novo item

            System.out.println("Quantidade:");
            int quantidade = sc.nextInt();
            sc.nextLine();

            System.out.println("Categoria:");
            String categoria = sc.nextLine();

            System.out.println("Preço unitário:");
            double precoUnitario = sc.nextDouble();
            sc.nextLine();

            Produto novoProduto = new Produto(codigo, nome, quantidade, categoria, precoUnitario);
            lista.add(novoProduto);
            System.out.println("Produto cadastrado com sucesso!");
        }

        System.out.println("Continuar cadastrando? (Y/n)");
        String sc_continuar = sc.nextLine();
        if (!sc_continuar.equalsIgnoreCase("Y")) {
            break;
        }

    }

    menu(lista, codigo);
}
    /*
        Função para escolher entro os dois relatórios: produtos gerais ou produtos com baixo estoque.
     */
    static void escolherRelatorio(ArrayList<Produto> lista, int codigo) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Relatório geral");
        System.out.println("2 - Relatório de produtos com baixo estoque");
        int scRelatorio = sc.nextInt();

        switch (scRelatorio) {
            case 1:
                gerarRelatorioGeral(lista, codigo);
                break;
            case 2:
                gerarRelatorioBaixoEstoque(lista, codigo);
                break;
            default:
                menu(lista, codigo);
        }
    }
    /*
        Função que gera o relatório geral do estoque.
     */
    static void gerarRelatorioGeral(ArrayList<Produto> lista, int codigo) {
      
        System.out.printf("%-10s %-20s %-12s %-20s %-12s%n", 
                          "Código", "Nome", "Quantidade", "Categoria", "Preço");

        System.out.println("=".repeat(80)); // Linha separadora
        
        for (Produto produto : lista) {
            // Verificando se a quantidade é menor ou igual a 20 para aplicar a cor vermelha
            String quantidadeFormatada = (produto.getQuantidade() <= 20) 
                ? RED + produto.getQuantidade() + RESET 
                : String.valueOf(produto.getQuantidade());

            // Ajuste de formatação para garantir o alinhamento
            System.out.printf("%-10d %-20s %-12s %-20s %-12.2f%n", 
                              produto.getCodigo(), produto.getNome(), 
                              quantidadeFormatada, produto.getCategoria(), 
                              produto.getPrecoUnitario());
        }

       menu(lista, codigo);
    }

    /*
        Função que gera o relatório de produtos com baixo estoque.
    */
    static void gerarRelatorioBaixoEstoque(ArrayList<Produto> lista, int codigo) {
     
        System.out.printf("%-10s %-20s %-12s %-20s %-12s%n", 
                          "Código", "Nome", "Quantidade", "Categoria", "Preço");

        System.out.println("=".repeat(80)); // Linha separadora

        for (Produto produto : lista) {
            if (produto.getQuantidade() <= 20) {
                System.out.printf("%-10d %-20s " + RED + "%-12d" + RESET + " %-20s %-12.2f%n", 
                                  produto.getCodigo(), produto.getNome(), 
                                  produto.getQuantidade(), produto.getCategoria(), 
                                  produto.getPrecoUnitario());
            }
        }

        menu(lista, codigo);
    }

    /*
        Função utilizada em registrarVenda(), para exibir os produtos no estoque
     */
    static void gerarRelatorioVenda(ArrayList<Produto> lista, int codigo) {
      
        System.out.printf("%-10s %-20s %-12s %-20s %-12s%n", 
                          "Código", "Nome", "Quantidade", "Categoria", "Preço");

        System.out.println("=".repeat(80)); // Linha separadora
        
        for (Produto produto : lista) {
         
            String quantidadeFormatada = (produto.getQuantidade() <= 20) 
                ? RED + produto.getQuantidade() + RESET 
                : String.valueOf(produto.getQuantidade());

            System.out.printf("%-10d %-20s %-12s %-20s %-12.2f%n", 
                              produto.getCodigo(), produto.getNome(), 
                              quantidadeFormatada, produto.getCategoria(), 
                              produto.getPrecoUnitario());
        }
    }

    /*
        Função para registrar uma venda caso o produto exista e tenha estoque suficiente dele.
     */
    static void registrarVenda(ArrayList<Produto> lista, int codigo) {
        Scanner sc = new Scanner(System.in);

        gerarRelatorioVenda(lista, codigo);

        System.out.println("Informe o código do produto que deseja vender:");
        int codigoProduto = sc.nextInt();
        Produto produtoSelecionado = null;

        for (Produto produto : lista) {
            if (produto.getCodigo() == codigoProduto) {
                produtoSelecionado = produto;
                break;
            }
        }

        if (produtoSelecionado == null) {
            System.out.println("Produto não encontrado.");
            menu(lista, codigo);
            return;
        }

        System.out.println("Informe a quantidade a ser vendida:");
        int quantidadeVendida = sc.nextInt();

        if (quantidadeVendida > produtoSelecionado.getQuantidade()) {
            System.out.println("Quantidade insuficiente em estoque. Estoque disponível: " 
                               + produtoSelecionado.getQuantidade());
        } else {
       
            produtoSelecionado.setQuantidade(produtoSelecionado.getQuantidade() - quantidadeVendida);
            System.out.println("Venda registrada com sucesso!");
        }

        for (Produto produto : lista) {
            if (produto.getQuantidade() <= 20) {
                System.out.printf("Produto %d - %s com baixo estoque:" + RED + " %d \n" + RESET,
                                  produto.getCodigo(),
                                  produto.getNome(),
                                  produto.getQuantidade());
            }
        }

        menu(lista, codigo);
    }

    public static void main(String[] args) {
        ArrayList<Produto> listaDeProdutos = new ArrayList<>();

        listaDeProdutos.add(new Produto(1, "Arroz", 30, "Alimentos", 5.50));
        listaDeProdutos.add(new Produto(2, "Feijão", 25, "Alimentos", 7.80));
        listaDeProdutos.add(new Produto(3, "Detergente", 28, "Limpeza", 2.30));

        int codigo = listaDeProdutos.size();

        menu(listaDeProdutos, codigo);
    }
}
