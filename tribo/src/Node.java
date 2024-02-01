import java.util.HashMap;
import java.util.Map;

public class Node {

    private String guerreiro;
    private int terras;
    private Map<String, Node> filhos;
    private Node pai;
    private int camada;

    public Node(String guerreiro, int terras) {
        this.guerreiro = guerreiro;
        this.terras = terras;
        this.filhos = new HashMap<>();
        this.pai = null;
        this.camada = 0;
    }

    public String getGuerreiro() {
        return guerreiro;
    }

    public void setTerras(int terras) {
        this.terras = terras;
    }

    public int getTerras() {
        return terras;
    }

    public Map<String, Node> getFilhos() {
        return filhos;
    }

    public Node getPai() {
        return pai;
    }

    public void setPai(Node pai) {
        this.pai = pai;
    }

    public void adicionarFilho(String nome, Node filho) {
        filhos.put(nome, filho);
        filho.setPai(this);
    }

    public int getCamada() {
        return camada;
    }

    public void setCamada(int camada) {
        this.camada = camada;
    }

}
