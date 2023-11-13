public abstract class Item{
    protected String nomeComercial;
    protected float precoUni;
    protected float precoCusto;
    //codigoId

    public Item(String nomeComercial, float precoUni, float precoCusto){
        this.nomeComercial = nomeComercial;
        this.precoUni = precoUni;
        this.precoCusto = precoCusto;
    }
}