public class Cozinheiro extends Funcionario{
    //private listaPratos

    private static float pratoP = 30;
    private static float sobremesa = 20;

    public Cozinheiro(String nome, String endereco, String estadoCivil, int nroCarteiraTrabalho, String cpf, String rg, int day, int month, int year){
        super(nome, endereco, estadoCivil, nroCarteiraTrabalho, cpf, rg, day, month, year);
    }

    @Override
    public float calcularSalario() {
        return 0;
    }
}