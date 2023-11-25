public class Garcom extends Funcionario{
    private float salarioBase;
    private String diaFolga;
    
    private static float limite = 100;

    public Garcom(String nome, String endereco, String estadoCivil, int nroCarteiraTrabalho, String cpf, String rg, int day, int month, int year, float salarioBase, String diaFolga){
        super(nome, endereco, estadoCivil, nroCarteiraTrabalho, cpf, rg, day, month, year);
        this.salarioBase = salarioBase;
        this.diaFolga = diaFolga;
    }

    public float calcularSalario(){
        return 0;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getDiaFolga() {
        return diaFolga;
    }

    public void setDiaFolga(String diaFolga) {
        this.diaFolga = diaFolga;
    }

    public void mostrarFuncionario() {
        super.mostrarFuncionario();
        System.out.println("Salario base: " + salarioBase);
        System.out.println("Dia de folga: " + diaFolga);
    }
}
