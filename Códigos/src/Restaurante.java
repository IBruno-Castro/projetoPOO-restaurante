import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Restaurante implements EnumsFuncionarios{
    static Scanner scanner = new Scanner(System.in);
    static SleepMetod sleep = new SleepMetod();
    public static void main(String[] args){

        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        ArrayList<Itens> itens = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        ArrayList<Ingredientes> ingredientes = new ArrayList<>();
        int opcao;

        do {
            System.out.println("BEM-VINDO AO RESTAURANTE\n");
            System.out.println("============== Menu ==============");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Cadastrar Ingrediente");
            System.out.println("3. Cadastrar Item");
            System.out.println("4. Cadastrar Pedido");
            System.out.println("0. Sair");
            System.out.println("==================================");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    cadastrarIngrediente();
                    break;
                case 3:
                    //cadastrarItem();
                    break;
                case 4:
                    //cadastrarPedido();
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
        
    }

    public static Funcionario cadastrarFuncionario() {
        System.out.println("CADASTRAMENTO DE FUNCIONÁRIOS\n");

        sleep.Sleeping(1000);

        System.out.println("Iniciando cadastro...\n");

        sleep.Sleeping(1000);

        String x;
        do {
            System.out.println("Deseja cadastrar um garçom ou um cozinheiro?");
            x = scanner.nextLine();
            x = x.toUpperCase();

            if (!x.equals("GARCOM") && !x.equals("GARÇOM") && !x.equals("COZINHEIRO")) {
                System.out.println("Entrada inválida!\n");
            }
        } while(!x.equals("GARCOM") && !x.equals("GARÇOM") && !x.equals("COZINHEIRO"));

        System.out.print("\nDigite o nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.print("\nDigite o CPF do funcionário: ");
        String cpf = scanner.nextLine();
        System.out.print("\nDigite o RG do funcionário: ");
        String rg = scanner.nextLine();
        System.out.print("\nDigite o endereço do funcionário (Rua ..., número): ");
        String end = scanner.nextLine();
        System.out.print("\nDigite o estado civil do funcionário: ");
        String ec = scanner.nextLine();
        ec = ec.toUpperCase();
        System.out.print("\nDigite o número da carteira de trabalho do funcionário: ");
        int nroC = scanner.nextInt();

        if(x.equals("GARCOM") || x.equals("GARÇOM")){
            System.out.println("\nDigite o salário base do garçom: ");
            double sal = scanner.nextDouble();
            System.out.println("\nSelecione o dia de folga do garçom: ");
            System.out.println("""
                    1. Domingo | 2.Segunda-Feira | 3.Terça-feira | 4.Quarta-feira | 5.Quinta-feira | 6.Sexta-feira | 7.Sábado
                    """);

            int folga = scanner.nextInt();

            return new Garcom(nome, end, converterEstadoCivil(ec), nroC, cpf, rg, sal, converterFolga(folga));
        }

        return new Cozinheiro(nome, end, converterEstadoCivil(ec), nroC, cpf, rg);
    }

    public static EnumsFuncionarios.EstadoCivil converterEstadoCivil(String ec){
        if(Objects.equals(ec, "SOLTEIRO") || Objects.equals(ec, "SOLTEIRA")){
            return EstadoCivil.SOLTEIRA;
        }
        if(Objects.equals(ec, "CASADO") || Objects.equals(ec, "CASADA")){
            return EstadoCivil.CASADA;
        }
        if(Objects.equals(ec, "DIVORCIADO") || Objects.equals(ec, "DIVORCIADA")){
            return EstadoCivil.DIVORCIADA;
        }
        if(Objects.equals(ec, "SEPARADO") || Objects.equals(ec, "SEPARADA")){
            return EstadoCivil.SEPARADA;
        }
        if(Objects.equals(ec, "VIUVO") || Objects.equals(ec, "VIUVA") || Objects.equals(ec, "VIÚVO") || Objects.equals(ec, "VIÚVA")){
            return EstadoCivil.VIUVA;
        }
        else {
            return null;
        }
    }

    public static EnumsFuncionarios.DiaSemana converterFolga(int folga){
        return switch (folga){
            case 1 -> DiaSemana.DOMINGO;
            case 2 -> DiaSemana.SEGUNDA;
            case 3 -> DiaSemana.TERCA;
            case 4 -> DiaSemana.QUARTA;
            case 5 -> DiaSemana.QUINTA;
            case 6 -> DiaSemana.SEXTA;
            case 7 -> DiaSemana.SABADO;
            default -> null;
        };
    }

    public static Ingredientes cadastrarIngrediente(){
        System.out.println("CADASTRAMENTO DE INGREDIENTES\n");

        sleep.Sleeping(1000);

        System.out.println("Iniciando cadastro...\n");

        sleep.Sleeping(1000);

        System.out.print("\nDigite o nome do ingrediente: ");
        String nome = scanner.nextLine();
        System.out.print("\nDigite a quantidade do ingrediente: (Ex: 1kg, 1L, 1 unidade)");
        String qtd = scanner.nextLine();

        return new Ingredientes(nome, qtd);
    }

    /*public static Itens cadastrarItens(){

    }

    public static Pedido cadastrarPedido(){

    }

    public static void fecharMes(){

    }*/
}
