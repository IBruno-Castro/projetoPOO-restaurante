import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Funcionario implements EnumsGerais, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected String nome;
    protected String cpf;
    protected String rg;
    protected String endereco;
    protected EnumsGerais.EstadoCivil estadoCivil;
    protected int nroCarteiraTrabalho;
    protected LocalDateTime dataAdmissao;

    public Funcionario(){
        this.endereco = null;
        this.estadoCivil = null;
        this.nome = null;
        this.nroCarteiraTrabalho = 0;
        this.cpf = null;
        this.rg = null;
        this.dataAdmissao = LocalDateTime.of(0,0,0,0,0);
    }

    public Funcionario(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public Funcionario(String nome, String endereco, EnumsGerais.EstadoCivil estadoCivil, int nroCarteiraTrabalho, String cpf, String rg){
        this.endereco = endereco;
        this.estadoCivil = estadoCivil;
        this.nome = nome;
        this.nroCarteiraTrabalho = nroCarteiraTrabalho;
        this.cpf = cpf;
        this.rg = rg;
        this.dataAdmissao = LocalDateTime.now();
    }

    public abstract double calcularSalario();

    public abstract void zerarPedidos();

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public EnumsGerais.EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public int getNroCarteiraTrabalho() {
        return nroCarteiraTrabalho;
    }

    public String getRg() {
        return rg;
    }
    public LocalDateTime getDataAdmissao() {
        return dataAdmissao;
    }

    public void mostrarFuncionario(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("\nInformações de funcionário: \n");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("RG: " + rg);
        System.out.println("Endereço: " + endereco);
        System.out.println("Estado Civil: " + mostrarEstadoCivil());
        System.out.println("Numero da Carteira de Trabalho: " + nroCarteiraTrabalho);
        System.out.println("Data de admissao: " + dataAdmissao.format(formatter));
    }

    public String mostrarEstadoCivil() {
        switch (estadoCivil) {
            case SOLTEIRA:
                return "Solteira";
            case CASADA:
                return "Casada";
            case DIVORCIADA:
                return "Divorciada";
            case SEPARADA:
                return "Separada";
            case VIUVA:
                return "Viúva";
            default:
                return "Estado civil inválido";
        }
    }
}
