import java.lang.IllegalArgumentException;

public class Garcom extends Funcionario {
    private double salarioBase;
    private EnumsFuncionarios.DiaSemana diaFolga;
    
    private static double limite = 100;

    public Garcom(String nome, String endereco, EnumsFuncionarios.EstadoCivil estadoCivil, int nroCarteiraTrabalho, String cpf, String rg, double salarioBase, EnumsFuncionarios.DiaSemana diaFolga){
        super(nome, endereco, estadoCivil, nroCarteiraTrabalho, cpf, rg);
        this.salarioBase = salarioBase;
        this.diaFolga = diaFolga;
    }

    public float calcularSalario(){
        return 0;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        if(salarioBase < 1320){
            throw new IllegalArgumentException("Valor abaixo do salário mínimo!");
        } else {
            this.salarioBase = salarioBase;
        }
    }

    public EnumsFuncionarios.DiaSemana getDiaFolga() {
        return diaFolga;
    }

    public void setDiaFolga(EnumsFuncionarios.DiaSemana diaFolga) {
        this.diaFolga = diaFolga;
    }

    public void mostrarFuncionario() {
        super.mostrarFuncionario();
        System.out.println("Salario base: " + salarioBase);
        System.out.println("Dia de folga: " + mostrarDia());
    }

    public String mostrarDia(){
        switch (diaFolga) {
            case DOMINGO:
                return "Domingo";
            case SEGUNDA:
                return "Segunda-feira";
            case TERCA:
                return "Terça-feira";
            case QUARTA:
                return "Quarta-feira";
            case QUINTA:
                return "Quinta-feira";
            case SEXTA:
                return "Sexta-feira";
            case SABADO:
                return "Sábado";
            default:
                return "Dia inválido";
        }
    }
}
