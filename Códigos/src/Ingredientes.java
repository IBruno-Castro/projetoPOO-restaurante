import java.io.Serial;
import java.io.Serializable;

public class Ingredientes implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String nome;
    private String quantidade;

    public Ingredientes () {
    }

    public Ingredientes (String nome, String quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void mostrarIngrediente() {
        System.out.println("\nInformações do ingrediente: \n");
        System.out.println("Nome: " + getNome());
        System.out.println("Quantidade: " + getQuantidade());
    }
}