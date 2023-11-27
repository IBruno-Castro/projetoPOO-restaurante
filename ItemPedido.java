public class ItemPedido{
    private Itens item;
    private int qtd;

    public ItemPedido(Itens item, int qtd){
        this.item = item;
        this.qtd = qtd;
    }
    
    public int getQtd() {
        return qtd;
    }

    public Itens getItem() {
        return item;
    }
}
