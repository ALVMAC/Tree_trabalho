import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        /*
         * Tribo teste = new Tribo();
         * Map<String, Node> nodes = teste.armazenaNodos();
         * Tree arvore = new Tree();
         * Node raiz = arvore.encontraRaiz(nodes);
         * arvore.montarArvore(nodes);
         */// System.out.println(arvore.toString());

        // System.out.println(raiz);
        
        while (true) {
            System.out.println("Menu: \n1. Mostrar pai-filho\n2.mostrar as terras herdadas e o guerreiro da ultima geração que obteve a maior quantidade de terras finais\n3. Sair");
            int escolha = Integer.parseInt(sc.nextLine());
            if (escolha == 1) {
                Tribo teste = new Tribo();
                Map<String, Node> nodes = teste.armazenaNodos();
                Tree arvore = new Tree();
                arvore.montarArvore(nodes);
                System.out.println(arvore.toString());
            } else if (escolha == 2) {
                Tribo teste = new Tribo();
                Map<String, Node> nodes = teste.armazenaNodos();
                Tree arvore = new Tree();
                arvore.montarArvore(nodes);
                teste.calculaHerancaFilho(nodes);
            } else if (escolha == 3) {
                break;
            } else {
                System.out.println("Escolha uma opção valida");
            }
        }
        sc.close();
    }
}
