import java.util.ArrayList;
import java.util.List;

public abstract class Itens {
    protected String nome;
    protected double precoUnitario;
    protected double precoCusto;
    protected String codigo;

    public Itens () {
    }

    public Itens(String nome, double precoUnitario, double precoCusto, ArrayList<Itens> listaDeItens) throws ErroCodigoException {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.precoCusto = precoCusto;
        this.codigo = gerarCodigo(listaDeItens);
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public String getCodigo() {
        return codigo;
    }

    protected String gerarCodigo(ArrayList<Itens> codigosGerados) throws ErroCodigoException {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";

        // Verificando o último código gerado (se existir) para gerar o próximo
        String ultimoCodigoGerado;
        if (codigosGerados.isEmpty()) return "AAA00";
        else ultimoCodigoGerado = codigosGerados.get(codigosGerados.size() - 1).getCodigo();

        // Verificando se o último código gerado é ZZZ99, se sim, retorna o erro
        if (ultimoCodigoGerado.equals("ZZZ99")) throw new ErroCodigoException("LIMITE DE CÓDIGOS ATINGIDO!");

        char[] codigoArray = ultimoCodigoGerado.toCharArray();

        // Gerando o próximo código de forma crescente
        for (int i = 4; i >= 0; i--) {
            if ((codigoArray[i] != '9') && (codigoArray[i] != 'Z')) {
                if (i >= 3) {
                    codigoArray[i] = numeros.charAt(numeros.indexOf(codigoArray[i]) + 1);
                } else {
                    codigoArray[i] = letras.charAt(letras.indexOf(codigoArray[i]) + 1);
                    if (codigoArray[i] == 'A') {
                        codigoArray[i] = 'A';
                    }
                }
                break;
            } else {
                if (i >= 3) codigoArray[i] = '0';
                else codigoArray[i] = 'A';
            }
        }

        return String.valueOf(codigoArray);
    }

    public void mostrarItem(){
        System.out.println("Nome: " + nome);
        System.out.println("Preço unitário: " + precoUnitario);
        System.out.println("Preço de custo: " + precoCusto);
        System.out.println("Código: " + codigo);
    }
}
