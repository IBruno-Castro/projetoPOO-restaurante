import java.lang.reflect.Array;
import java.util.ArrayList;

public class bebida extends itens implements codificavel{
    private String tipoEmbalagem;
    private String tamanhoEmbalagem;

    public bebida () {
    }
    public bebida(String nome, String tipoEmbalagem, String tamanhoEmbalagem, double precoUnitario, double precoCusto, ArrayList<itens> listaAtual) {
        this.nome = nome;
        this.tipoEmbalagem = tipoEmbalagem;
        this.tamanhoEmbalagem = tamanhoEmbalagem;
        this.precoUnitario = precoUnitario;
        this.precoCusto = precoCusto;
        codigo = geradorCodigo(listaAtual);
    }

    public String getNome() {
        return nome;
    }

    public String getTipoEmbalagem() {
        return tipoEmbalagem;
    }

    public String getTamanhoEmbalagem() {
        return tamanhoEmbalagem;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setTipoEmbalagem(String tipoEmbalagem) {
        this.tipoEmbalagem = tipoEmbalagem;
    }

    public void setTamanhoEmbalagem(String tamanhoEmbalagem) {
        this.tamanhoEmbalagem = tamanhoEmbalagem;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    //implementar gerador de código aqui com IF pra identificar o TIPO na lista GENÉRICA
    public String geradorCodigo (ArrayList<itens> listaAtual) {
        String codigo = null;

        return codigo;
    }
}
