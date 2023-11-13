public class Sobremesa extends Prato{
    private float nroCal;

    public Sobremesa(String nomeComercial, float precoUni, float precoCusto, String descricao, int tempoPreparo, float nroCal){
        super(nomeComercial, precoUni, precoCusto, descricao, tempoPreparo);
        this.nroCal = nroCal;
    }
}
