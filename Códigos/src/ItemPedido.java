import java.io.Serializable;

public class ItemPedido implements Serializable{
    private Itens item;
    private int qtd;

    public ItemPedido(Itens item, int qtd){
        this.item = item;
        this.qtd = qtd;
    }

    public Itens getItem() {
        return item;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd){
        this.qtd = qtd;
    }

    public void mostrarItem(){
        System.out.println("Nome: " + item.getNome());
        System.out.println("Quantidade: " + qtd);
    }
}