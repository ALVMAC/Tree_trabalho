
import java.util.Map;

public class Tree {
    Node root;

    public Tree() {
        this.root = null;
    }

    boolean isEmpty() {
        return this.root == null;
    }

    public Node encontraRaiz(Map<String, Node> nodes) {
        for (Node node : nodes.values()) {
            if (node.getPai() == null) {
                return node;
            }
        }
        return null;
    }

    public void montarArvore(Map<String, Node> nodes) {
        Tribo terrasRoot = new Tribo();
        for (Node node : nodes.values()) {
            if (node.getPai() == null) {
                this.root = encontraRaiz(nodes);
                root.setTerras(terrasRoot.terrasPai());
            } else {
                Node pai = nodes.get(node.getPai().getGuerreiro());
                if (pai != null) {
                    pai.adicionarFilho(node.getGuerreiro(), node);
                } else {
                    System.out.println("Erro: Nó pai não encontrado para " + node.getGuerreiro());
                }
            }
        }

    }
    // ai o recursivo dos guri

    private void imprimirArvore(Node node, StringBuilder r, String prefix) {
        if (node != null) {
            r.append(prefix).append("Guerreiro: ").append(node.getGuerreiro())
                    .append(", Terras conquistadas(não é a finall): ").append(node.getTerras());

            Map<String, Node> filhos = node.getFilhos();
            if (!filhos.isEmpty()) {
                r.append(" Filhos: ");
                for (Node filho : filhos.values()) {
                    r.append(filho.getGuerreiro()).append(", ");
                }
            }

            r.append("\n");

            // recursivooo
            for (Node filho : filhos.values()) {
                imprimirArvore(filho, r, prefix + "  ");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        imprimirArvore(this.root, r, "");
        return r.toString();
    }

}
