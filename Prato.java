public abstract class Prato extends Item{
    protected String descricao;
    protected int tempoPreparo;
    //ingredientes

    public Prato(String nomeComercial, float precoUni, float precoCusto, String descricao, int tempoPreparo){
        super(nomeComercial, precoUni, precoCusto);
        this.descricao = descricao;
        this.tempoPreparo = tempoPreparo;
    }
}
