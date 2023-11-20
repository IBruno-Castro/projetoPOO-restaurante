import java.util.ArrayList;

public class pratoPrincipal extends prato implements codificavel {
    public pratoPrincipal() {
    }

    public pratoPrincipal(String nome, String descricao, double tempoPreparo, double precoUnitario, double precoDeCusto, ArrayList<itens> listaAtual) {
        super(nome, descricao, tempoPreparo, precoUnitario, precoDeCusto);
        codigo = geradorCodigo(listaAtual);
    }

    public void mostrarIngredientes () {
        System.out.println("\nQuantidade de ingredientes: " + getListaIngredientes().size());
        for (ingredientes ingred : getListaIngredientes()) {
            System.out.println("\nNome: " + ingred.getNome());
            System.out.println("Quantidade: " + ingred.getQuantidade());
        }
    }

    public void mostrarPrato () {
        System.out.println("\nNome: " + getNome());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Código: " + getCodigo());
        System.out.println("Tempo de preparo: " + getTempoPreparo());
        System.out.println("Preço unitário: " + getPrecoUnitario());
        System.out.println("Preço de custo: " + getPrecoCusto());
        mostrarIngredientes();
    }

    //implementar gerador de código aqui com IF pra identificar o TIPO na lista GENÉRICA
    public String geradorCodigo (ArrayList<itens> listaAtual) {
        String codigo = null;
        
        return codigo;
    }
}
