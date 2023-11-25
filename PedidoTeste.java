import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PedidoTeste {
    private LocalDateTime dataRealizacaoPedido;
    private LocalDateTime horaRegistro;
    private LocalDateTime horaPagamento;

    // Construtor que inicializa a data de realização do pedido
    public PedidoTeste() {
        this.dataRealizacaoPedido = LocalDateTime.now();
    }

    // Método para realizar o pedido
    public void realizarPedido() {
        this.horaRegistro = LocalDateTime.now();
        System.out.println("Pedido realizado em: " + horaRegistro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    // Método para confirmar o pagamento
    public void confirmarPagamento() {
        this.horaPagamento = LocalDateTime.now();
        System.out.println("Pagamento confirmado em: " + horaPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    // Métodos getters para obter as informações
    public LocalDateTime getDataRealizacaoPedido() {
        return dataRealizacaoPedido;
    }

    public LocalDateTime getHoraRegistro() {
        return horaRegistro;
    }

    public LocalDateTime getHoraPagamento() {
        return horaPagamento;
    }
}



