import java.util.ArrayList;
import java.util.List;

public class Cozinheiro extends Funcionario{
    private List<Prato> listaPratos = new ArrayList<Prato>();

    private static double pratoP = 30;
    private static double sobremesa = 20;
    private int qtdSobremesa;
    private int qtdPrincipal;

    public Cozinheiro(String nome, String endereco, EnumsFuncionarios.EstadoCivil estadoCivil, int nroCarteiraTrabalho, String cpf, String rg){
        super(nome, endereco, estadoCivil, nroCarteiraTrabalho, cpf, rg);
        qtdPrincipal = 0;
        qtdSobremesa = 0;
    }

    public double calcularSalario() {
        return pratoP*qtdPrincipal + sobremesa*qtdSobremesa;
    }

    public void increaseSobremesa(){
        qtdSobremesa++;
    }

    public void increasePrincipal(){
        qtdPrincipal++;
    }

    public void addPrato(Prato prato){
        listaPratos.add(prato);
        System.out.println(prato.getNome() + "adicionado a lista de pratos do cozinheiro.");
    }

    public void mostrarPratos(){
        System.out.println("Lista de pratos preparados por " + nome);
        for (Prato prato : listaPratos) {
            prato.mostrarItem();
        }
    }

    public void mostrarFuncionario(){
        super.mostrarFuncionario();
        mostrarPratos();
    }
}