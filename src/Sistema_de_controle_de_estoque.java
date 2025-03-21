import java.util.ArrayList;
import java.util.Scanner;

public class Sistema_de_controle_de_estoque {

    static void menu(ArrayList<Produto> lista, int codigo){
        System.out.println("""
                [1] Cadastrar item\
                
                [2] Acessar relatorios\
                
                [3] Registrar venda\
                
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
                System.out.println("venda");
                break;
            default:
                if(scMenu == 4){
                    System.out.println("Saindo");
                    sc.close();
                    break;
                }
                System.out.println("opção invalida");
                menu(lista, codigo);
        }
    }

    static void cadastrar(ArrayList<Produto> lista, int codigo){
        Scanner sc = new Scanner(System.in);

        while(true){
            codigo++;

            /*
            System.out.println("Codigo");
            int codigo = sc.nextInt();
            sc.nextLine();
            */

            System.out.println("Nome");
            String nome = sc.nextLine();

            System.out.println("Quantidade");
            int quantidade = sc.nextInt();
            sc.nextLine();

            System.out.println("Categoria");
            String categoria = sc.nextLine();

            System.out.println("Preço unitário");
            double precoUnitario = sc.nextDouble();
            sc.nextLine();

            Produto produto = new Produto(codigo, nome, quantidade, categoria, precoUnitario);
            lista.add(produto);

            System.out.println("Continuar cadastrando?(Y/n)");
            String sc_continuar = sc.nextLine();
            if(!sc_continuar.equalsIgnoreCase("Y")){
                break;
            }
        }
        menu(lista, codigo);
    }

    static void escolherRelatorio(ArrayList<Produto> lista, int codigo){
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Relatório  geral");
        System.out.println("2 - Relatório de produtos com baixo estoque");
        int scRelatorio = sc.nextInt();

        switch (scRelatorio){
            case 1: gerarRelatorioGeral(lista, codigo);
                break;
            case 2: gerarRelatorioBaixoEstoque(lista, codigo);
                break;
            default: menu(lista, codigo);
        }
    }

    static void gerarRelatorioGeral(ArrayList<Produto> lista, int codigo){
        System.out.println("Código | Nome | Quantidade | Categoria | Preço unitário");
        for (Produto listaDeProdutos : lista) {
            System.out.println(listaDeProdutos);
        }
        menu(lista, codigo);
    }

    static void gerarRelatorioBaixoEstoque(ArrayList<Produto> lista, int codigo){
        System.out.println("Código | Nome | Quantidade | Categoria | Preço unitário");
        for (Produto listaDeProdutos : lista) {
            if(listaDeProdutos.getQuantidade() <= 20){
                System.out.println(listaDeProdutos);
            }
        }
        menu(lista, codigo);
    }

    public static void main(String[] args) {
        int codigo = 0;
        Scanner sc = new Scanner(System.in);

        Produto produto = new Produto(codigo, "item", 2, "Alimento", 12.2);

        ArrayList<Produto> listaDeProdutos = new ArrayList<>();
        listaDeProdutos.add(produto);
        menu(listaDeProdutos, codigo);

        sc.close();
    }
}