import java.util.ArrayList;
import java.util.List;

public class Cozinheiro extends Funcionario{
    private List<Prato> listaPratos = new ArrayList<Prato>();

    private static float pratoP = 30;
    private static float sobremesa = 20;

    public Cozinheiro(String nome, String endereco, EnumsFuncionarios.EstadoCivil estadoCivil, int nroCarteiraTrabalho, String cpf, String rg, int day, int month, int year){
        super(nome, endereco, estadoCivil, nroCarteiraTrabalho, cpf, rg, day, month, year);
    }

    public float calcularSalario() {
        return 0;
    }

    public void addPrato(Prato prato){
        listaPratos.add(prato);
        System.out.println(prato.getNome() + "adicionado a lista de pratos do cozinheiro.");
    }

    public void mostrarPratos(){
        System.out.println("Lista de pratos preparados por " + nome);
        for (Prato prato : listaPratos) {
            prato.mostrarPrato();
        }
    }

    public void mostrarFuncionario(){
        super.mostrarFuncionario();
        mostrarPratos();
    }
}