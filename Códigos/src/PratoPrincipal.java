import java.io.Serial;
import java.util.ArrayList;

public class PratoPrincipal extends Prato {

    @Serial
    private static final long serialVersionUID = 1L;
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
