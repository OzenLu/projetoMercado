import java.util.ArrayList;
import java.util.Scanner;

public class Sistema_de_controle_de_estoque {

    // Códigos cores no terminal
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    static void menu(ArrayList<Produto> lista, int codigo) {
        System.out.println("""
                [1] Cadastrar item
                
                [2] Acessar relatórios
                
                [3] Registrar venda
                
                [4] Sair""");

        Scanner sc = new Scanner(System.in);
        int scMenu = sc.nextInt();

        switch (scMenu) {
            case 1:
                System.out.println("Iniciando cadastro\n");
                cadastrar(lista, codigo);
                break;
            case 2:
                escolherRelatorio(lista, codigo);
                break;
            case 3:
                registrarVenda(lista, codigo);
                break;
            default:
                if (scMenu == 4) {
                    System.out.println("Saindo");
                    sc.close();
                    return;
                }
                System.out.println("Opção inválida");
                menu(lista, codigo);
        }
    }

    static void cadastrar(ArrayList<Produto> lista, int codigo) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            codigo++;

            System.out.println("Nome:");
            String nome = sc.nextLine();

            System.out.println("Quantidade:");
            int quantidade = sc.nextInt();
            sc.nextLine();

            System.out.println("Categoria:");
            String categoria = sc.nextLine();

            System.out.println("Preço unitário:");
            double precoUnitario = sc.nextDouble();
            sc.nextLine();

            Produto produto = new Produto(codigo, nome, quantidade, categoria, precoUnitario);
            lista.add(produto);

            System.out.println("Continuar cadastrando? (Y/n)");
            String sc_continuar = sc.nextLine();
            if (!sc_continuar.equalsIgnoreCase("Y")) {
                break;
            }
        }
        menu(lista, codigo);
    }

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

    static void registrarVenda(ArrayList<Produto> lista, int codigo) {
        Scanner sc = new Scanner(System.in);

        gerarRelatorioGeral(lista, codigo);

        
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

     
        menu(lista, codigo);
    }

    public static void main(String[] args) {
        int codigo = 0;

        ArrayList<Produto> listaDeProdutos = new ArrayList<>();
        listaDeProdutos.add(new Produto(1, "Arroz", 10, "Alimentos", 5.50));
        listaDeProdutos.add(new Produto(2, "Feijão", 25, "Alimentos", 7.80));
        listaDeProdutos.add(new Produto(3, "Detergente", 15, "Limpeza", 2.30));

        menu(listaDeProdutos, codigo);
    }
}
