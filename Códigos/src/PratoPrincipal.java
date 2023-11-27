import java.util.ArrayList;

public class PratoPrincipal extends Prato {
    public PratoPrincipal() {
    }

    public PratoPrincipal(String nome, String descricao, double tempoPreparo, double precoUnitario, double precoDeCusto, ArrayList<Itens> listaDeItens) {
        super(nome, descricao, tempoPreparo, precoUnitario, precoDeCusto, listaDeItens);
    }

    public void mostrarIngredientes () {
        System.out.println("\nQuantidade de ingredientes: " + getListaIngredientes().size());
        for (Ingredientes ingred : getListaIngredientes()) {
            System.out.println("\nNome: " + ingred.getNome());
            System.out.println("Quantidade: " + ingred.getQuantidade());
        }
    }

    public void mostrarPrato () {
        System.out.println("\nNome: " + getNome());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Código: " + getCodigo());
        System.out.println("Tempo de preparo: " + getTempoPreparo());
        System.out.println("Preço unitário: " + getPrecoUnitario());
        System.out.println("Preço de custo: " + getPrecoCusto());
        mostrarIngredientes();
    }
}
