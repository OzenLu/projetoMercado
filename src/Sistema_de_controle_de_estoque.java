import java.util.ArrayList;
import java.util.Scanner;

public class Sistema_de_controle_de_estoque {

    static void menu(ArrayList<Produto> lista, int codigo){
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Registrar venda");
        System.out.println("4 - Sair");
        Scanner sc = new Scanner(System.in);
        int sc_menu = sc.nextInt();

        switch (sc_menu) {
            case 1:
                System.out.println("cadastro");
                cadastrar(lista, codigo);
                break;
            case 2:
                System.out.println("Listar");
                listar(lista);
                break;
            case 3:
                System.out.println("venda");
                break;
            default:
                if(sc_menu == 4){
                    System.out.println("Saindo");
                    sc.close();
                    break;
                }
                System.out.println("opção invalida");
                menu(lista, codigo);
                break;
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

    static void listar(ArrayList<Produto> lista){
        System.out.println("Código | Nome | Quantidade | Categoria | Preço unitário");
        for (Object listaProduto : lista) {
            System.out.println(listaProduto);

        }
    }

    public static void main(String[] args) {
        int codigo = 0;
        Scanner sc = new Scanner(System.in);

        Produto produto = new Produto(codigo, "item", 2, "Alimento", 12.2);

        ArrayList<Produto> listaProdutos = new ArrayList<>();
        listaProdutos.add(produto);
        menu(listaProdutos, codigo);

        sc.close();
    }
}