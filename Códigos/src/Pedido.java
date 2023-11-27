import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {
    private ArrayList<ItemPedido> itens;
    private float valorTotal;
    private LocalDateTime dataPedido;
    private LocalDateTime horaPagamento;
    private String formaPag;
    private Garcom garcom;
    private Cozinheiro cozinheiro;

    public Pedido(ItemPedido[] itens, String formaPag, Garcom garcom, Cozinheiro cozinheiro) {
        this.itens = new ArrayList<>();
        this.valorTotal = 0;
        this.dataPedido = LocalDateTime.now();
        this.formaPag = formaPag;
        this.garcom = garcom;
        this.cozinheiro = cozinheiro;
    }

    /*public void realizarPedido() {
        this.garcom.registrarPedido(this);
        this.cozinheiro.registrarPedido(this);
    }*/

    public void confirmarPagamento() {
        this.horaPagamento = LocalDateTime.now();
    }

    /*public void calcularValorTotal() {
        for (ItemPedido item : itens) {
            //this.valorTotal += item./getItem().getPrecoUni() * item.getQtd();
        }
    }*/

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public LocalDateTime getHoraPagamento() {
        return horaPagamento;
    }

    public String getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(String formaPag) {
        this.formaPag = formaPag;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public Cozinheiro getCozinheiro() {
        return cozinheiro;
    }

    public void setItens(ArrayList<ItemPedido> itens) {
        this.itens = itens;
    }
     
}