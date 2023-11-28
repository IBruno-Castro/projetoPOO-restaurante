import java.util.ArrayList;

public abstract class Prato extends Itens {
    protected ArrayList<Ingredientes> listaIngredientes = new ArrayList<>();
    protected String descricao;
    protected double tempoPreparo;

    public Prato () {
    }

    public Prato(String nome, String descricao, double tempoPreparo, double precoUnitario, double precoDeCusto, ArrayList<Itens> listaDeItens) throws ErroCodigoException {
        super(nome, precoUnitario, precoDeCusto, listaDeItens);
        this.descricao = descricao;
        this.tempoPreparo = tempoPreparo;
    }

    public void adicionarIngredientes (String nome, String quantidade) {
        Ingredientes temp = new Ingredientes(nome, quantidade);
        listaIngredientes.add(temp);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(double tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public ArrayList<Ingredientes> getListaIngredientes() {
        return listaIngredientes;
    }

    public void mostrarIngredientes() {
        System.out.println("\nQuantidade de ingredientes: " + getListaIngredientes().size());
        for (Ingredientes ingred : getListaIngredientes()) {
            ingred.mostrarIngrediente();
        }
    }

    public void mostrarItem (){
        super.mostrarItem();
        System.out.println("Descrição: " + descricao);
        System.out.println("Tempo de preparo: " + tempoPreparo);
    }
}
