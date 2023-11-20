public class Garcom extends Funcionario{
    private float salarioBase;
    private String diaFolga;
    private static float limite = 100;
    private static float grat = 500;

    public Garcom(String nome, String endereco, String estadoCivil, int nroCarteiraTrabalho, String cpf, String rg, int day, int month, int year, float salarioBase, String diaFolga){
        super(nome, endereco, estadoCivil, nroCarteiraTrabalho, cpf, rg, day, month, year);
        this.salarioBase = salarioBase;
        this.diaFolga = diaFolga;
    }

    @Override
    public float calcularSalario(){
        if(/*pedidos*/ >= limite){
            return salarioBase + grat;
        }
        else{
            return salarioBase;

        }
    }
}
