import java.util.ArrayList;

public class PratoPrincipal extends Prato {
    public PratoPrincipal() {
    }

    public PratoPrincipal(String nome, String descricao, double tempoPreparo, double precoUnitario, double precoDeCusto, ArrayList<Itens> listaDeItens) throws ErroCodigoException {
        super(nome, descricao, tempoPreparo, precoUnitario, precoDeCusto, listaDeItens);
    }

    public void mostrarItem () {
        super.mostrarItem();
        mostrarIngredientes();
    }
}
