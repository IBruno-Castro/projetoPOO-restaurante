import java.io.Serial;
import java.util.ArrayList;

public class Sobremesa extends Prato {

    @Serial
    private static final long serialVersionUID = 1L;
    private double nroCalorias;

    public Sobremesa () {
    }

    public Sobremesa (String nome, String descricao, double tempoPreparo, double precoUnitario, double precoDeCusto, double nroCalorias, ArrayList<Itens> listaDeItens) throws ErroCodigoException {
        super(nome, descricao, tempoPreparo, precoUnitario, precoDeCusto, listaDeItens);
        this.nroCalorias = nroCalorias;
    }

    public void mostrarItem () {
        super.mostrarItem();
        System.out.println("Número de calorias: " + nroCalorias);
        mostrarIngredientes();
    }

    public double getNroCalorias() {
        return nroCalorias;
    }

    public void setNroCalorias(double nroCalorias) {
        this.nroCalorias = nroCalorias;
    }
}
