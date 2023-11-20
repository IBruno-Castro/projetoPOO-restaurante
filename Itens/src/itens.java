public abstract class itens {
    protected String nome;
    protected double precoUnitario;
    protected double precoCusto;
    protected String codigo;

    public itens () {
    }
    public itens(String nome, double precoUnitario, double precoCusto) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.precoCusto = precoCusto;
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
}
