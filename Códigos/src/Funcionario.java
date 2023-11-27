import java.time.LocalDateTime;

public abstract class Funcionario implements EnumsFuncionarios{
    protected String nome;
    protected String cpf;
    protected String rg;
    protected String endereco;
    protected EnumsFuncionarios.EstadoCivil estadoCivil;
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

    public Funcionario(String nome, String endereco, EnumsFuncionarios.EstadoCivil estadoCivil, int nroCarteiraTrabalho, String cpf, String rg, int day, int month, int year){
        this.endereco = endereco;
        this.estadoCivil = estadoCivil;
        this.nome = nome;
        this.nroCarteiraTrabalho = nroCarteiraTrabalho;
        this.cpf = cpf;
        this.rg = rg;
        this.dataAdmissao = LocalDateTime.of(year,month,day,0,0);
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

    public EnumsFuncionarios.EstadoCivil getEstadoCivil() {
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
        System.out.println("-- Informacoes de funcionario --");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("RG: " + rg);
        System.out.println("Endereço: " + endereco);
        System.out.println("Estado Civil: " + mostrarEstadoCivil());
        System.out.println("Numero da Carteira de Trabalho: " + nroCarteiraTrabalho);
        System.out.println("Data de admissao: " + dataAdmissao.toString());
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
