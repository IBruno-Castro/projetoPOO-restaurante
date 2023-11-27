public class ItemPedido{
    private Itens item;
    private int qtd;

    public ItemPedido(Itens item, int qtd){
        this.item = item;
        this.qtd = qtd;
    }

    public Itens getItem(){
        return item;
    }

    public int getQtd(){
        return qtd;
    }

    public void setItem(Itens item){
        this.item = item;
    }

    public void setQtd(int qtd){
        this.qtd = qtd;
    }

}
