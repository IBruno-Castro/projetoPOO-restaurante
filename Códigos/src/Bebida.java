import java.util.ArrayList;

public class Bebida extends Itens {
    private EnumsGerais.EmbalagemBebidas tipoEmbalagem;
    private double tamanhoEmbalagem;

    public Bebida () {
    }

    public Bebida(String nome, double precoUnitario, double precoCusto, ArrayList<Itens> listaDeItens, EnumsGerais.EmbalagemBebidas tipoEmbalagem, double tamanhoEmbalagem) throws ErroCodigoException {
        super(nome, precoUnitario, precoCusto, listaDeItens);
        this.tipoEmbalagem = tipoEmbalagem;
        this.tamanhoEmbalagem = tamanhoEmbalagem;
    }

    public String getNome() {
        return nome;
    }

    public EnumsGerais.EmbalagemBebidas getTipoEmbalagem() {
        return tipoEmbalagem;
    }

    public double getTamanhoEmbalagem() {
        return tamanhoEmbalagem;
    }

    public void setTipoEmbalagem(EnumsGerais.EmbalagemBebidas tipoEmbalagem) {
        this.tipoEmbalagem = tipoEmbalagem;
    }

    public void setTamanhoEmbalagem(double tamanhoEmbalagem) {
        this.tamanhoEmbalagem = tamanhoEmbalagem;
    }

    public void mostrarItem () {
        super.mostrarItem();
        System.out.println("Tipo embalagem: " + getTipoEmbalagem());
        System.out.println("Tamanho embalagem: " + getTamanhoEmbalagem());
    }
}
