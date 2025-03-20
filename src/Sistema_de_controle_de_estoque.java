import java.util.Scanner;

public class Sistema_de_controle_de_estoque {

    static void menu(){
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Registrar venda");
        System.out.println("4 - Sair");
        Scanner sc = new Scanner(System.in);
        int sc_menu = sc.nextInt();

        switch (sc_menu) {
            case 1:
                System.out.println("cadastro");
                cadastrar();
                break;
            case 2:
                System.out.println("Listar");
                break;
            case 3:
                System.out.println("venda");
                break;
            default:
                if(sc_menu == 4){
                    System.out.println("Saindo");
                    break;
                }
                System.out.println("opção invalida");
                menu();
                break;
        }
    }

    static void cadastrar(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Codigo");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.println("Nome");
        String nome = sc.nextLine();

        Produto produto = new Produto(codigo, nome);
        menu();
    }


    public static void main(String[] args) {
        Produto produto = new Produto(1, "item 1");
        System.out.println(produto.toString());
        Scanner sc = new Scanner(System.in);
        menu();

        //ArrayList<Produto> lista_produtos = new ArrayList<>();

        //lista_produtos.add(produto);
        //System.out.println(lista_produtos.get(0));
    }
}