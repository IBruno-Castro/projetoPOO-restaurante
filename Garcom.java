public class Garcom extends Funcionario{
    private float salarioBase;
    private String diaFolga;

    public Garcom(String nome, String endereco, String estadoCivil, int nroCarteiraTrabalho, float salarioBase, String diaFolga){
        super(nome, endereco, estadoCivil, nroCarteiraTrabalho);
        this.salarioBase = salarioBase;
        this.diaFolga = diaFolga;
    }
}
