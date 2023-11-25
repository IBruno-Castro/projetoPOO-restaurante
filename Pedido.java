import java.util.ArrayList;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {
    private ArrayList<ItemPedido> itens;
    private float valorTotal;
    private LocalDateTime dataPedido;
    private LocalDateTime horaPagamento;
    private String formaPag; //futuramente será um enum
    private Garcom garcom;
    private Cozinheiro cozinheiro;

    public Pedido(ArrayList<ItemPedido> itens, String formaPag, Garcom garcom, Cozinheiro cozinheiro) {
        this.itens = new ArrayList<>();
        this.valorTotal = 0;
        this.dataPedido = LocalDateTime.now();
        this.horaPagamento = null;
        this.formaPag = formaPag;
        this.garcom = garcom;
        this.cozinheiro = cozinheiro;
    }

/*
 * Função realizar pedido recebe a ArrayList de itens e registra o pedido no sistema emitindo a hora quando há confirmação de pagamento.
 */
    public void realizarPedido(ArrayList<ItemPedido> itens) {
        //this.garcom.registrarPedido(); vamos mudar quando for fazer a classe restaurante
        if (this.formaPag.equals("Dinheiro") || this.formaPag.equals("Cartão") || this.formaPag.equals("PIX")) {
            this.confirmarPagamento();
        } /*else {
            System.out.println("Forma de pagamento inválida!");
            criar uma excepcão para tratamento de erro de forma de pagamento inválida
        }*/
    }

/*
 * Fazer funcao de contabilizacao do salario dos funcionarios de acordo com a quantidade de pedidos etc?
 */

    public void confirmarPagamento() {
        this.horaPagamento = LocalDateTime.now();
    }

    public void calcularValorTotal() {
        for (ItemPedido item : itens) {
            //this.valorTotal += item./getItem().getPrecoUni() * item.getQtd();
        }
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemPedido> itens) {
        this.itens = itens;
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
     
}
