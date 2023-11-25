import java.util.Date;

public abstract class Funcionario {
    protected String nome;
    protected String cpf;
    protected String rg;
    protected String endereco;
    protected String estadoCivil;
    protected int nroCarteiraTrabalho;
    protected Date dataAdmissao;

    public Funcionario(){
        this.endereco = null;
        this.estadoCivil = null;
        this.nome = null;
        this.nroCarteiraTrabalho = 0;
        this.cpf = null;
        this.rg = null;
        this.dataAdmissao = new Date(0, 0, 0);
    }

    public Funcionario(String nome, String endereco, String estadoCivil, int nroCarteiraTrabalho, String cpf, String rg, int day, int month, int year){
        this.endereco = endereco;
        this.estadoCivil = estadoCivil;
        this.nome = nome;
        this.nroCarteiraTrabalho = nroCarteiraTrabalho;
        this.cpf = cpf;
        this.rg = rg;
        this.dataAdmissao = new Date(year, month, day);
    }

    public abstract float calcularSalario();

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public int getNroCarteiraTrabalho() {
        return nroCarteiraTrabalho;
    }

    public String getRg() {
        return rg;
    }
    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void mostrarFuncionario(){
        System.out.println("-- Informacoes de funcionario --");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("RG: " + rg);
        System.out.println("Endereço: " + endereco);
        System.out.println("Estado Civil: " + estadoCivil);
        System.out.println("Numero da Carteira de Trabalho: " + nroCarteiraTrabalho);
        System.out.println("Data de admissao: " + dataAdmissao.toString());
    }
}
