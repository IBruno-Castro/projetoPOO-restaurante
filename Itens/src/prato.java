import java.util.ArrayList;

public abstract class prato extends itens {
    protected ArrayList<ingredientes> listaIngredientes = new ArrayList<>();
    protected String descricao;
    protected double tempoPreparo;

    public prato () {
    }

    public prato(String nome, String descricao, double tempoPreparo, double precoUnitario, double precoDeCusto, ArrayList<itens> listaDeItens) {
        super(nome, precoUnitario, precoDeCusto, listaDeItens);
        this.descricao = descricao;
        this.tempoPreparo = tempoPreparo;
    }

    public void adicionarIngredientes (String nome, String quantidade) {
        ingredientes temp = new ingredientes(nome, quantidade);
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

    public ArrayList<ingredientes> getListaIngredientes() {
        return listaIngredientes;
    }

    public abstract void mostrarIngredientes ();

    public abstract void mostrarPrato ();
}
