import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Tribo {
    String currDir = System.getProperty("user.dir");
    String relativo = "\\src\\teste.txt";
    String completo = currDir + relativo;

    public Map<String, Node> armazenaNodos() {
        Map<String, Node> nodes = new HashMap<>();

        Path path = Paths.get(completo);

        try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("utf8"))) {
            String line;
            // int numTerrasPai = Integer.parseInt(reader.readLine());

            while ((line = reader.readLine()) != null) {
                String[] partes = line.split("\\s+");
                if (partes.length == 3) {
                    String pai = partes[0];
                    String filho = partes[1];
                    int terrasFilho = Integer.parseInt(partes[2]);

                    Node paiNode = nodes.computeIfAbsent(pai, k -> new Node(pai, terrasFilho));
                    Node filhoNode = nodes.computeIfAbsent(filho, k -> new Node(filho, terrasFilho));

                    paiNode.adicionarFilho(filho, filhoNode);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
        }

        return nodes;
    }

    public int terrasPai() {

        int numTerrasPai = -1;
        Path path = Paths.get(currDir + relativo);

        try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("utf8"))) {

            numTerrasPai = Integer.parseInt(reader.readLine());

        } catch (IOException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
        }
        return numTerrasPai;
    }

    public void calculaHerancaFilho(Map<String, Node> nodes) {
        Tree calc = new Tree();
        calc.montarArvore(nodes);
        Node ultimaGeraMaiorTerras = null;
        int ultimoMaisTerras = 0;
        int maiorCamada = 0;
        Node raiz = calc.encontraRaiz(nodes);

        Queue<Node> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            int tamanhoNivel = fila.size();

            for (int i = 0; i < tamanhoNivel; i++) {
                Node node = fila.poll();
                node.setCamada(maiorCamada);

                if (!node.getFilhos().isEmpty()) {
                    int herancaPorFilho = node.getTerras() / node.getFilhos().size();
                    int resto = node.getTerras() % node.getFilhos().size();

                    for (Node filho : node.getFilhos().values()) {
                        int herancaIndividual = herancaPorFilho;
                        filho.setTerras(filho.getTerras() + herancaIndividual);

                        if (resto > 0) {
                            filho.setTerras(filho.getTerras() + 1);
                            herancaIndividual += 1;
                            resto--;
                        }

                        System.out.println(filho.getGuerreiro() + " herda: " + herancaIndividual
                                + " /depois da herança: " + filho.getTerras());

                        fila.add(filho);
                    }
                }
            }

            maiorCamada++;
        }

        for (Node node : nodes.values()) {
            if (node.getCamada() == maiorCamada - 1 && node.getTerras() > ultimoMaisTerras) {
                ultimaGeraMaiorTerras = node;
                ultimoMaisTerras = node.getTerras();
            }
        }

        System.out.println("\nO guerreiro com o maior numero de terras na ultima geração: ");
        if (ultimaGeraMaiorTerras != null) {
            System.out.println(ultimaGeraMaiorTerras.getGuerreiro() + "\nTerras conquistadas mais a herança: "
                    + ultimaGeraMaiorTerras.getTerras());
        }
    }

}
