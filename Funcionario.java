public abstract class Funcionario {
    protected String nome;
    protected String endereco;
    protected String estadoCivil;
    protected int nroCarteiraTrabalho;
    //cpf
    //rg
    //dataAdmissao

    public Funcionario(String nome, String endereco, String estadoCivil, int nroCarteiraTrabalho){
        this.endereco = endereco;
        this.estadoCivil = estadoCivil;
        this.nome = nome;
        this.nroCarteiraTrabalho = nroCarteiraTrabalho;
    }

    //public abstract float calcularSalario(); 
}
