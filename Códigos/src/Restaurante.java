import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Restaurante implements EnumsFuncionarios{
    static Scanner scanner = new Scanner(System.in);
    static SleepMetod sleep = new SleepMetod();
    static SoundTrack sound = new SoundTrack();

    public static void main(String[] args){
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        ArrayList<Itens> itens = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        ArrayList<Ingredientes> ingredientes = new ArrayList<>();

        int nroPedidos = 0;

        try {
            // Bebida instances
            itens.add(new Bebida("Nome1", 10.0, 5.0, itens, "TipoEmbalagem1", "TamanhoEmbalagem1"));
            itens.add(new Bebida("Nome2", 15.0, 8.0, itens, "TipoEmbalagem2", "TamanhoEmbalagem2"));

            // Sobremesa instances
            itens.add(new Sobremesa("Nome1", "Descricao1", 20.0, 12.0, 7.0,  13000.0,itens));
            itens.add(new Sobremesa("Nome2", "Descricao2", 25.0, 18.0, 10.0, 1200.0, itens));

            // PratoPrincipal instances
            itens.add(new PratoPrincipal("Nome1", "Descricao1", 30.0, 25.0, 15.0, itens));
            itens.add(new PratoPrincipal("Nome2", "Descricao2", 35.0, 30.0, 20.0, itens));
        } catch (ErroCodigoException e) {
            System.out.println(e);
        }

        // Cozinheiro instances
        funcionarios.add(new Cozinheiro("Nome1", "Endereco1", EstadoCivil.DIVORCIADA, 4, "CPF1", "RG1"));
        funcionarios.add(new Cozinheiro("Nome2", "Endereco2", EstadoCivil.CASADA, 3, "CPF2", "RG2"));

        // Garcom instances
        funcionarios.add(new Garcom("Nome1", "Endereco1", EstadoCivil.DIVORCIADA, 2, "CPF1", "RG1", 1500, DiaSemana.SEXTA));
        funcionarios.add(new Garcom("Nome2", "Endereco2", EstadoCivil.CASADA, 1, "CPF2", "RG2", 1800, DiaSemana.DOMINGO));

        // Ingredientes instances
        ingredientes.add(new Ingredientes("NomeIngrediente1", "Quantidade1"));
        ingredientes.add(new Ingredientes("NomeIngrediente2", "Quantidade2"));

        int opcao;

        do {
            sound.MusicFundo();
            System.out.println("BEM-VINDO AO RESTAURANTE\n");
            System.out.println("============== Menu ==============");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Cadastrar Ingrediente");
            System.out.println("3. Cadastrar Item");
            System.out.println("4. Cadastrar Pedido");
            System.out.println("5. Checar Item");
            System.out.println("6. Checar Funcionário");
            System.out.println("7. Checar Pedido");
            System.out.println("0. Sair");
            System.out.println("==================================");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            String escolha;
            int op;

            switch (opcao) {
                case 1:
                    funcionarios.add(cadastrarFuncionario());

                    if(funcionarios.get(funcionarios.size() - 1) instanceof Cozinheiro){
                        
                        System.out.println("Adicione pratos preparados pelo cozinheiro");
                        do{
                            do {
                                System.out.println("\nLista de pratos: ");
                                for (int index = 0; index < itens.size(); index++) {
                                    if(itens.get(index) instanceof Prato){
                                        System.out.println(index + "." + itens.get(index).getNome());
                                    }
                                }

                                System.out.println("Digite o index do prato para adicioná-lo na lista do cozinheiro: ");
                                op = scanner.nextInt();
                                scanner.nextLine();

                                itens.get(op).mostrarItem();

                                System.out.println("Adicionar o prato? s/n");
                                escolha = scanner.nextLine();
                            } while (!escolha.equals("s"));

                            ((Cozinheiro) funcionarios.get(funcionarios.size() - 1)).addPrato((Prato)itens.get(op));

                            System.out.println("Quer adicionar outro prato? s/n");
                            escolha = scanner.nextLine();
                        } while(!escolha.equals("n"));
                    }

                    funcionarios.get(funcionarios.size() - 1).mostrarFuncionario();
                    break;
                case 2:
                    ingredientes.add(cadastrarIngrediente());
                    ingredientes.get(ingredientes.size() - 1).mostrarIngrediente();
                    break;
                case 3:
                    try {
                        itens.add(cadastrarItens(itens, ingredientes));

                        if(itens.get(itens.size() - 1) instanceof Prato){
                            do{
                                do {
                                    System.out.println("\nLista ingredientes: ");
                                    for (int index = 0; index < ingredientes.size(); index++) {
                                        System.out.println(index + "." + ingredientes.get(index).getNome());
                                    }

                                    System.out.println("Digite o index do ingrediente para adicionar: ");
                                    op = scanner.nextInt();
                                    scanner.nextLine();

                                    ingredientes.get(op).mostrarIngrediente();

                                    System.out.println("Confirmar ingrediente? s/n");
                                    escolha = scanner.nextLine();
                                } while (!escolha.equals("s"));

                                ((Prato) itens.get(itens.size() - 1)).adicionarIngredientes(ingredientes.get(op));

                                System.out.println("Quer adicionar outro ingrediente? s/n");
                                escolha = scanner.nextLine();
                            } while(!escolha.equals("n"));
                        }

                        itens.get(itens.size() - 1).mostrarItem();
                    } catch (ErroCodigoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        pedidos.add(cadastrarPedido(funcionarios));

                        do
                        {
                            do {
                                System.out.println("\nLista itens: ");
                                for (int index = 0; index < itens.size(); index++) {
                                    System.out.println(index + "." + itens.get(index).getNome());
                                }

                                System.out.println("Digite a opcao do item que deseja adicionar: ");
                                op = scanner.nextInt();
                                scanner.nextLine();

                                itens.get(op).mostrarItem();

                                System.out.println("Confirmar item? s/n");
                                escolha = scanner.nextLine();
                            } while(!escolha.equals("s"));
                            
                            System.out.println("Digite a quantidade deseja adicionar: ");
                            int qtd = scanner.nextInt();
                            scanner.nextLine();

                            if(itens.get(op) instanceof PratoPrincipal){
                                Cozinheiro cozinheiro = pedidos.get(pedidos.size() - 1).getCozinheiro();
                                cozinheiro.increasePrincipal(qtd);
                            }
                            
                            if (itens.get(op) instanceof Sobremesa) {
                                Cozinheiro cozinheiro = pedidos.get(pedidos.size() - 1).getCozinheiro();
                                cozinheiro.increaseSobremesa(qtd);
                            }
                            
                            ItemPedido item = new ItemPedido(itens.get(op), qtd);
                            pedidos.get(pedidos.size() - 1).adicionarItem(item);

                            System.out.println("Deseja adicionar outro item? s/n");
                            escolha = scanner.nextLine();
                        } while(escolha.equals("s"));

                        //pedidos.get(pedidos.size() - 1).calcularValorTotal();

                        System.out.println("Digite a forma de pagamento: ");
                        String formaPag = scanner.nextLine();
                        pedidos.get(pedidos.size() - 1).setFormaPag(formaPag);
                        pedidos.get(pedidos.size() - 1).confirmarPagamento();

                        pedidos.get(pedidos.size() - 1).mostrarPedido();

                        nroPedidos++;
                    } catch (PagamentoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    mostrarItens(itens);
                case 6:
                    mostrarFuncionarios(funcionarios);
                case 7: 
                    mostrarPedidos(pedidos);
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

        String cpf;
        do {
            System.out.print("\nDigite o CPF do funcionário: ");
            cpf = scanner.nextLine();

            if(!ValidaCPF.validaCPF(cpf)){
                System.out.println("CPF inválido!");
            }
        } while (!ValidaCPF.validaCPF(cpf));

        System.out.print("\nDigite o RG do funcionário: ");
        String rg = scanner.nextLine();

        System.out.print("\nDigite o endereço do funcionário (Rua ..., número): ");
        String end = scanner.nextLine();

        String ec;
        EnumsFuncionarios.EstadoCivil ecConvertido;
        do {
            System.out.print("\nDigite o estado civil do funcionário: ");
            ec = scanner.nextLine();

            ecConvertido = converterEstadoCivil(ec);

            if(ecConvertido == null){
                System.out.println("Estado Civil inválido!");
            }
        } while (ecConvertido == null);
        ec = ec.toUpperCase();

        System.out.print("\nDigite o número da carteira de trabalho do funcionário: ");
        int nroC = scanner.nextInt();
        scanner.nextLine();

        if(x.equals("GARCOM") || x.equals("GARÇOM")){
            System.out.println("\nDigite o salário base do garçom: ");
            double sal = scanner.nextDouble();
            scanner.nextLine();

            String dia;
            EnumsFuncionarios.DiaSemana diaConvertido;
            do {
                System.out.println("\nDigite o dia da semana de folga do garçom: ");
                dia = scanner.nextLine();

                diaConvertido = converterFolga(dia);

                if(diaConvertido == null){
                    System.out.println("Dia da semana inválido!");
                }
            } while (diaConvertido == null);
            dia = dia.toUpperCase();

            return new Garcom(nome, end, ecConvertido, nroC, cpf, rg, sal, diaConvertido);
        }

        return new Cozinheiro(nome, end, ecConvertido, nroC, cpf, rg);
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

    public static EnumsFuncionarios.DiaSemana converterFolga(String folga){
        if(Objects.equals(folga, "SEGUNDA")){
        return DiaSemana.SEGUNDA;
        }
        if(Objects.equals(folga, "TERÇA") || Objects.equals(folga, "TERCA")){
            return DiaSemana.TERCA;
        }
        if(Objects.equals(folga, "QUARTA")){
            return DiaSemana.QUARTA;
        }
        if(Objects.equals(folga, "QUINTA")){
            return DiaSemana.QUINTA;
        }
        if(Objects.equals(folga, "SEXTA")){
            return DiaSemana.SEXTA;
        }
        if(Objects.equals(folga, "SÁBADO") || folga.equals("SABADO")){
            return DiaSemana.SABADO;
        }
        if(Objects.equals(folga, "DOMINGO")){
            return DiaSemana.DOMINGO;
        }
        else {
            return null;
        }
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

    public static Itens cadastrarItens(ArrayList<Itens> itens, ArrayList<Ingredientes> ingredientes) throws ErroCodigoException{
        System.out.println("CADASTRAMENTO DE ITENS\n");
        Itens item = null;

        sleep.Sleeping(1000);

        System.out.println("Iniciando cadastro...\n");

        sleep.Sleeping(1000);

        String x;
        do {
            System.out.println("Deseja cadastrar um prato principal, sobremesa ou bebida?");
            x = scanner.nextLine();
            x = x.toUpperCase();

            if (!x.equals("BEBIDA") && !x.equals("PRATO PRINCIPAL") && !x.equals("SOBREMESA")) {
                System.out.println("Entrada inválida!\n");
            }
        } while(!x.equals("BEBIDA") && !x.equals("PRATO PRINCIPAL") && !x.equals("SOBREMESA"));

        System.out.print("Nome do item: ");
        String nome = scanner.nextLine();

        System.out.print("Preço unitário: ");
        double precoUnitario = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Preço de custo: ");
        double precoCusto = scanner.nextDouble();
        scanner.nextLine();

        if (x.equals("PRATO PRINCIPAL") || x.equals("SOBREMESA")) {
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();

            System.out.print("Tempo de preparo: ");
            double tempoPreparo = scanner.nextDouble();
            scanner.nextLine();

            if (x.equals("PRATO PRINCIPAL")) {
                item = new PratoPrincipal(nome, descricao, tempoPreparo, precoUnitario, precoCusto, itens);
            } else{
                System.out.print("Número de calorias: ");
                double nroCal = scanner.nextDouble();
                scanner.nextLine();

                item = new Sobremesa(nome, descricao, tempoPreparo, precoUnitario, precoCusto, nroCal, itens);
            }
        } else {
            System.out.print("Tipo de embalagem: ");
            String tipoEmbalagem = scanner.nextLine();

            System.out.print("Tamanho da embalagem: ");
            String tamanhoEmbalagem = scanner.nextLine();

            item = new Bebida(nome, precoUnitario, precoCusto, itens, tipoEmbalagem, tamanhoEmbalagem);
        }

        return item;
    }

    public static Pedido cadastrarPedido(ArrayList<Funcionario> funcionarios){
        System.out.println("CADASTRAMENTO DE PEDIDOS\n");
        Garcom garcom = null;
        Cozinheiro cozinheiro = null;

        sleep.Sleeping(1000);

        System.out.println("Iniciando cadastro...\n");

        sleep.Sleeping(1000);

        String c;
        int op;
        do 
        {
            do {
                System.out.println("\nLista de garçons: ");
                for (int index = 0; index < funcionarios.size(); index++) {
                    if(funcionarios.get(index) instanceof Garcom){
                        System.out.println(index + "." + funcionarios.get(index).getNome());
                    }
                }
                System.out.println("Digite o codigo do garçom: ");
                op = scanner.nextInt();
                scanner.nextLine();

                funcionarios.get(op).mostrarFuncionario();

                System.out.println("Confirmar o garçom? s/n");
                c = scanner.nextLine();
            } while (!c.equals("s"));

            if (funcionarios.get(op) instanceof Garcom) {
                garcom = (Garcom) funcionarios.get(op);
            } else {
                garcom = null;
            }
            
        } while (garcom == null);

        do 
        {
            do {
                System.out.println("\nLista de cozinheiros: ");
                for (int index = 0; index < funcionarios.size(); index++) {
                    if(funcionarios.get(index) instanceof Cozinheiro){
                        System.out.println(index + "." + funcionarios.get(index).getNome());
                    }
                }
                System.out.println("Digite o codigo do garçom: ");
                op = scanner.nextInt();
                scanner.nextLine();

                funcionarios.get(op).mostrarFuncionario();

                System.out.println("Confirmar o cozinheiro? s/n");
                c = scanner.nextLine();
            } while (!c.equals("s"));

            if (funcionarios.get(op) instanceof Cozinheiro) {
                cozinheiro = (Cozinheiro) funcionarios.get(op);
            } else {
                cozinheiro = null;
            }
            
        } while (cozinheiro == null);

        return new Pedido(garcom, cozinheiro);
    }

    /*public static void fecharMes(){

    }*/

    public static void mostrarItens(ArrayList<Itens> itens){
        String input;
        int op;
        do {
            System.out.println("\nLista de itens: ");
            for (int index = 0; index < itens.size(); index++) {
                System.out.println(index + "." + itens.get(index).getNome());
            }

            System.out.println("Digite o index do item para mais informacoes: ");
            op = scanner.nextInt();
            scanner.nextLine();

            itens.get(op).mostrarItem();

            System.out.println("Deseja verificar outro item?");
            input = scanner.nextLine();
        } while (!input.equals("n"));
    }

    public static void mostrarFuncionarios(ArrayList<Funcionario> funcionarios){
        String input;
        int op;
        do {
            System.out.println("\nLista de funcionarios: ");
            for (int index = 0; index < funcionarios.size(); index++) {
                System.out.println(index + "." + funcionarios.get(index).getNome());
            }

            System.out.println("Digite o index do funcionario para mais informacoes: ");
            op = scanner.nextInt();
            scanner.nextLine();

            funcionarios.get(op).mostrarFuncionario();

            System.out.println("Deseja verificar outro funcionario?");
            input = scanner.nextLine();
        } while (!input.equals("n"));
    }

    public static void mostrarPedidos(ArrayList<Pedido> pedidos){
        String input;
        int op;
        do {
            System.out.println("\nLista de pedidos: ");
            for (int index = 0; index < pedidos.size(); index++) {
                System.out.println("Pedido " + index);
            }

            System.out.println("Digite o numero do pedido para mais informacoes: ");
            op = scanner.nextInt();
            scanner.nextLine();

            pedidos.get(op).mostrarPedido();

            System.out.println("Deseja verificar outro pedido?");
            input = scanner.nextLine();
        } while (!input.equals("n"));
    }
}