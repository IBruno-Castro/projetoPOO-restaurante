public class ingredientes {
    private String nome;
    private String quantidade;

    public ingredientes () {
    }

    public ingredientes (String nome, String quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }
}
