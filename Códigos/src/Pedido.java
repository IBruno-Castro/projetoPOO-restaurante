import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {
    private ArrayList<ItemPedido> itens = new ArrayList<>();
    private float valorTotal;
    private LocalDateTime dataPedido;
    private LocalDateTime horaPagamento;
    private String formaPag;
    private Garcom garcom;
    private Cozinheiro cozinheiro;

    public Pedido(Garcom garcom, Cozinheiro cozinheiro) {
        this.valorTotal = 0;
        this.dataPedido = LocalDateTime.now();
        this.formaPag = null;
        this.garcom = garcom;
        this.cozinheiro = cozinheiro;
    }

    public void confirmarPagamento() throws PagamentoException {
        this.formaPag = formaPag.toUpperCase();
        if (formaPag.equals("DINHEIRO")) {
            System.out.println("Pagamento confirmado!");
            this.horaPagamento = LocalDateTime.now();
        } else if (formaPag.equals("CARTÃO") || formaPag.equals("CARTAO")) {
            System.out.println("Pagamento confirmado!");
            this.horaPagamento = LocalDateTime.now();
        } else if (formaPag.equals("PIX")) {
            System.out.println("Pagamento confirmado!");
            this.horaPagamento = LocalDateTime.now();
        } else {
            throw new PagamentoException("Forma de pagamento inválida!\nAceitamos apenas dinheiro, cartão ou pix.");
        }
    }

    public String getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(String formaPag) {
        this.formaPag = formaPag;
    }

    public void calcularValorTotal() {
        for (ItemPedido item : itens) {
            this.valorTotal += item.getItem().getPrecoUnitario() * item.getQtd();
        }
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public void removerItem(ItemPedido item) {
        itens.remove(item);
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

    public Garcom getGarcom() {
        return garcom;
    }

    public Cozinheiro getCozinheiro() {
        return cozinheiro;
    }

    public void mostrarPedido() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy\tHH:mm:ss");
        System.out.println(dataPedido.format(formatter));
        System.out.println("Garçom: " + garcom.getNome());
        System.out.println("Cozinheiro: " + cozinheiro.getNome());
        System.out.println("Itens: ");
        for (ItemPedido item : itens) {
            item.mostrarItem();
        }
        System.out.println("Forma de pagamento: " + formaPag);
        System.out.println("Pagamento confirmado: " + horaPagamento.format(formatter));
    }
     
}