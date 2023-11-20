import java.util.Date;

public abstract class Funcionario {
    protected String nome;
    protected String endereco;
    protected String estadoCivil;
    protected int nroCarteiraTrabalho;
    protected String cpf;
    protected String rg;
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
}
