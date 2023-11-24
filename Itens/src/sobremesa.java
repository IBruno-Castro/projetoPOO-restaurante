import java.util.ArrayList;

public class sobremesa extends prato {
    private double nroCalorias;

    public sobremesa () {
    }

    public sobremesa (String nome, String descricao, double tempoPreparo, double precoUnitario, double precoDeCusto, double nroCalorias, ArrayList<itens> listaDeItens) {
        super(nome, descricao, tempoPreparo, precoUnitario, precoDeCusto, listaDeItens);
        this.nroCalorias = nroCalorias;
    }

    public void mostrarIngredientes () {
        System.out.println("\nQuantidade de ingredientes: " + getListaIngredientes().size());
        for (ingredientes ingred : getListaIngredientes()) {
            System.out.println("\nNome: " + ingred.getNome());
            System.out.println("Quantidade: " + ingred.getQuantidade());
        }
    }

    public void mostrarPrato () {
        System.out.println("\nNome: " + getNome());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Código: " + getCodigo());
        System.out.println("Número de calorias: " + nroCalorias);
        System.out.println("Tempo de preparo: " + getTempoPreparo());
        System.out.println("Preço unitário: " + getPrecoUnitario());
        System.out.println("Preço de custo: " + getPrecoCusto());
        mostrarIngredientes();
    }

    public double getNroCalorias() {
        return nroCalorias;
    }

    public void setNroCalorias(double nroCalorias) {
        this.nroCalorias = nroCalorias;
    }
}
