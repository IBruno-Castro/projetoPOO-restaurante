import java.io.*;
import java.util.*;

public class Restaurante implements EnumsGerais {
    public static Scanner scanner = new Scanner(System.in);
    public static SleepMetod sleep = new SleepMetod();
    public static SoundTrack sound = new SoundTrack();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        sleep.Sleeping(700);

        System.out.println(ANSI_GREEN + "\nBEM-VINDO AO RESTAURANTE BRASIL BOSSA-NOVA!\n" + ANSI_RESET);

        System.out.println("\nConfigurando a playlist do restaurante...\n");

        sleep.Sleeping(1000);

        sound.MusicFundo();

        ArrayList<Funcionario> funcionarios = (ArrayList<Funcionario>) lerArquivoFuncionario();
        ArrayList<Itens> itens = (ArrayList<Itens>) lerArquivoItens();
        ArrayList<Pedido> pedidos = (ArrayList<Pedido>) lerArquivoPedido();
        ArrayList<Ingredientes> ingredientes = (ArrayList<Ingredientes>) lerArquivoIngredientes();

        int nroPedidos = 0;
        int opcao;

        sleep.Sleeping(1000);

        do {
            System.out.println(ANSI_YELLOW + "\n_____________ MENU À LA CARTE _______________\n" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "       1. Cadastrar funcionário" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "       2. Cadastrar ingrediente" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "       3. Cadastrar item" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "       4. Fazer um pedido" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "       5. Checar item" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "       6. Checar funcionário" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "       7. Checar pedido" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "       8. Checar ingredientes" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "       9. Fechar mês" + ANSI_RESET);
            System.out.println(ANSI_RED + "       10. Excluir do sistema" + ANSI_RESET);
            System.out.println(ANSI_RED + "       0. Sair\n" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "_____________________________________________" + ANSI_RESET);
            System.out.print(ANSI_GREEN + "\nEscolha uma opção: " + ANSI_RESET);
            opcao = scanner.nextInt();

            scanner.nextLine();

            String escolha;
            int op;

            switch (opcao) {
                case 1 -> {
                    funcionarios.add(cadastrarFuncionario());
                    sound.MusicConcluido();
                    if (funcionarios.get(funcionarios.size() - 1) instanceof Cozinheiro) {

                        System.out.println("\nAdicione pratos preparados pelo cozinheiro");
                        do {
                            do {
                                System.out.println("\nLista de pratos: \n");
                                for (int index = 0; index < itens.size(); index++) {
                                    if (itens.get(index) instanceof Prato) {
                                        System.out.println(index + "." + itens.get(index).getNome());
                                    }
                                }

                                System.out.println("\nDigite quais das opções de prato deseja adicionar na lista do cozinheiro: ");
                                op = scanner.nextInt();
                                scanner.nextLine();

                                itens.get(op).mostrarItem();

                                System.out.println("\nAdicionar o prato?");
                                escolha = scanner.nextLine();
                                escolha = escolha.toUpperCase();
                            } while (!escolha.equals("SIM"));

                            ((Cozinheiro) funcionarios.get(funcionarios.size() - 1)).addPrato((Prato) itens.get(op));
                            sound.MusicConcluido();

                            System.out.println("\nQuer adicionar outro prato?");
                            escolha = scanner.nextLine();
                            escolha = escolha.toUpperCase();
                        } while (!escolha.equals("NÃO") && !escolha.equals("NAO"));
                    }
                    escreverNoArquivoFuncionarios(funcionarios.get(funcionarios.size() - 1));
                    funcionarios.get(funcionarios.size() - 1).mostrarFuncionario();
                }
                case 2 -> {
                    escreverNoArquivoIngredientes(cadastrarIngrediente());
                    sound.MusicConcluido();
                    ingredientes = (ArrayList<Ingredientes>) lerArquivoIngredientes();
                    ingredientes.get(ingredientes.size() - 1).mostrarIngrediente();
                }
                case 3 -> {
                    try {
                        itens.add(cadastrarItens(itens));
                        sound.MusicConcluido();
                        if (itens.get(itens.size() - 1) instanceof Prato) {
                            do {
                                do {
                                    System.out.println("\nLista ingredientes: \n");
                                    for (int index = 0; index < ingredientes.size(); index++) {
                                        System.out.println(index + "." + ingredientes.get(index).getNome());
                                    }

                                    System.out.println("\nDigite o índice dos ingredientes que deseja adicionar: ");
                                    op = scanner.nextInt();
                                    scanner.nextLine();

                                    ingredientes.get(op).mostrarIngrediente();

                                    System.out.println("\nConfirmar ingrediente?");
                                    escolha = scanner.nextLine();
                                    escolha = escolha.toUpperCase();
                                } while (!escolha.equals("SIM"));

                                ((Prato) itens.get(itens.size() - 1)).adicionarIngredientes(ingredientes.get(op));
                                sound.MusicConcluido();

                                System.out.println("\nQuer adicionar outro ingrediente?");
                                escolha = scanner.nextLine();
                                escolha = escolha.toUpperCase();
                            } while (!escolha.equals("NÃO") && !escolha.equals("NAO"));
                        }

                        escreverNoArquivoItens(itens.get(itens.size() - 1)); // escreve o item no arquivo já com ingredientes
                        itens.get(itens.size() - 1).mostrarItem();
                    } catch (ErroCodigoException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        pedidos.add(cadastrarPedido(funcionarios));
                        sound.MusicConcluido();

                        do {
                            do {
                                System.out.println("\nLista itens: \n");
                                for (int index = 0; index < itens.size(); index++) {
                                    System.out.println(index + "." + itens.get(index).getNome());
                                }

                                System.out.println("\nDigite o índice dos itens que deseja adicionar: ");
                                op = scanner.nextInt();
                                scanner.nextLine();

                                itens.get(op).mostrarItem();

                                System.out.println("\nConfirmar item?");
                                escolha = scanner.nextLine();
                                escolha = escolha.toUpperCase();
                            } while (!escolha.equals("SIM"));

                            System.out.println("\nDigite a que quantidade deseja pedir: ");
                            int qtd = scanner.nextInt();
                            scanner.nextLine();

                            if (itens.get(op) instanceof PratoPrincipal) {
                                Cozinheiro cozinheiro = pedidos.get(pedidos.size() - 1).getCozinheiro();
                                cozinheiro.increasePrincipal(qtd);
                                funcionarios = atualizarCozinheiro(funcionarios, cozinheiro);
                            }

                            if (itens.get(op) instanceof Sobremesa) {
                                Cozinheiro cozinheiro = pedidos.get(pedidos.size() - 1).getCozinheiro();
                                cozinheiro.increaseSobremesa(qtd);
                                funcionarios = atualizarCozinheiro(funcionarios, cozinheiro);
                            }

                            ItemPedido item = new ItemPedido(itens.get(op), qtd);
                            pedidos.get(pedidos.size() - 1).adicionarItem(item);

                            System.out.println("\nDeseja adicionar outro item?");
                            escolha = scanner.nextLine();
                            escolha = escolha.toUpperCase();
                        } while (escolha.equals("SIM"));

                        pedidos.get(pedidos.size() - 1).calcularValorTotal();

                        System.out.println("\nDigite a forma de pagamento: ");
                        String formaPag = scanner.nextLine();
                        pedidos.get(pedidos.size() - 1).setFormaPag(formaPag);
                        pedidos.get(pedidos.size() - 1).confirmarPagamento();

                        escreverNoArquivoPedido(pedidos.get(pedidos.size() - 1));
                        pedidos.get(pedidos.size() - 1).mostrarPedido();

                        nroPedidos++;
                    } catch (PagamentoException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> {
                    itens = (ArrayList<Itens>) lerArquivoItens();
                    mostrarItens(itens);
                }
                case 6 -> {
                    funcionarios = (ArrayList<Funcionario>) lerArquivoFuncionario();
                    mostrarFuncionarios(funcionarios);
                }
                case 7 -> {
                    pedidos = (ArrayList<Pedido>) lerArquivoPedido();
                    mostrarPedidos(pedidos);
                }
                case 8 -> { // mostrar ingredientes
                    System.out.println("\nIngredientes cadastrados: \n");
                    ingredientes = (ArrayList<Ingredientes>) lerArquivoIngredientes();
                    for (Ingredientes ingrediente : ingredientes) {
                        ingrediente.mostrarIngrediente();
                        System.out.print("\n");
                    }
                }
                case 9 -> fecharMes(funcionarios, nroPedidos);
                case 10 -> {
                    System.out.println(ANSI_RED + "\nVocê quer excluir: " +
                            "\n1. Funcionários?" +
                            "\n2. Itens?" +
                            "\n3. Ingredientes?" + ANSI_RESET);

                    int exc = scanner.nextInt();

                    switch (exc) {
                        case 1 -> {
                            System.out.print("\nDigite o nome do funcionário: ");
                            scanner.nextLine();
                            String nome = scanner.next();
                            excluirObjetoFuncionario(nome);
                            funcionarios = (ArrayList<Funcionario>) lerArquivoFuncionario();
                        }
                        case 2 -> {
                            System.out.print("\nDigite o nome do item: ");
                            scanner.nextLine();
                            String nome = scanner.next();
                            excluirObjetoItens(nome);
                            itens = (ArrayList<Itens>) lerArquivoItens();
                        }
                        case 3 -> {
                            System.out.print("\nDigite o nome do ingrediente: ");
                            scanner.nextLine();
                            String nome = scanner.next();
                            excluirObjetoIngredientes(nome);
                            ingredientes = (ArrayList<Ingredientes>) lerArquivoIngredientes();
                        }
                        default -> System.out.println(ANSI_RED + "\nOpção inválida!" + ANSI_RESET);
                    }
                }
                case 0 -> System.out.println(ANSI_YELLOW + "\nAdoraríamos te receber no restaurante novamente. Até mais!" + ANSI_RESET);
                default -> System.out.println(ANSI_RED + "\nOpção inválida. Tente novamente." + ANSI_RESET);
            }
        } while (opcao != 0);
        
    }

    private static ArrayList<Funcionario> atualizarCozinheiro(ArrayList<Funcionario> funcionarios, Cozinheiro cozinheiro) {
        int j = 0;

        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario func = funcionarios.get(i);
            if (func instanceof Cozinheiro) {
                if (cozinheiro.getNome().equals(func.getNome())) {
                    j = i;
                    break;
                }
            }
        }

        excluirObjetoFuncionario(cozinheiro.getNome());
        escreverNoArquivoFuncionarios(funcionarios.get(j));
        funcionarios = (ArrayList<Funcionario>) lerArquivoFuncionario();
        return funcionarios;
    }

    private static ArrayList<Funcionario> atualizarGarcom(ArrayList<Funcionario> funcionarios, Garcom garcom) {
        int j = 0;

        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario func = funcionarios.get(i);
            if (func instanceof Garcom) {
                if (garcom.getNome().equals(func.getNome())) {
                    j = i;
                    break;
                }
            }
        }

        excluirObjetoFuncionario(garcom.getNome());
        escreverNoArquivoFuncionarios(funcionarios.get(j));
        funcionarios = (ArrayList<Funcionario>) lerArquivoFuncionario();
        return funcionarios;
    }

    public static Funcionario cadastrarFuncionario() {
        System.out.println(ANSI_BLUE + "\nCADASTRAMENTO DE FUNCIONÁRIOS\n" + ANSI_RESET);

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
        EnumsGerais.EstadoCivil ecConvertido;
        do {
            System.out.print("\nDigite o estado civil do funcionário: ");
            ec = scanner.nextLine();
            ec = ec.toUpperCase();

            ecConvertido = converterEstadoCivil(ec);

            if(ecConvertido == null){
                System.out.println("Estado Civil inválido!");
            }
        } while (ecConvertido == null);

        System.out.print("\nDigite o número da carteira de trabalho do funcionário: ");
        int nroC = scanner.nextInt();
        scanner.nextLine();

        if(x.equals("GARCOM") || x.equals("GARÇOM")){
            System.out.println("\nDigite o salário base do garçom: ");
            double sal = scanner.nextDouble();
            scanner.nextLine();

            String dia;
            EnumsGerais.DiaSemana diaConvertido;
            do {
                System.out.print("\nDigite o dia da semana de folga do garçom: ");
                dia = scanner.nextLine();
                dia = dia.toUpperCase();

                diaConvertido = converterFolga(dia);

                if(diaConvertido == null){
                    System.out.println("\nDia da semana inválido!");
                }
            } while (diaConvertido == null);

            return new Garcom(nome, end, ecConvertido, nroC, cpf, rg, sal, diaConvertido);
        }

        return new Cozinheiro(nome, end, ecConvertido, nroC, cpf, rg);
    }

    public static EnumsGerais.EstadoCivil converterEstadoCivil(String ec){
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

    public static EnumsGerais.DiaSemana converterFolga(String folga){
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
        System.out.println(ANSI_BLUE + "\nCADASTRAMENTO DE INGREDIENTES\n" + ANSI_RESET);

        sleep.Sleeping(1000);

        System.out.println("Iniciando cadastro...\n");

        sleep.Sleeping(1000);

        System.out.print("Digite o nome do ingrediente: ");
        String nome = scanner.nextLine();
        
        System.out.print("\nDigite a quantidade do ingrediente " + ANSI_YELLOW + "(Kg, Litro, Unidade, etc.): " + ANSI_RESET);
        String qtd = scanner.nextLine();

        return new Ingredientes(nome, qtd);
    }

    public static Itens cadastrarItens(ArrayList<Itens> itens) throws ErroCodigoException{
        System.out.println(ANSI_BLUE + "\nCADASTRAMENTO DE ITENS\n" + ANSI_RESET);
        Itens item;

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

        System.out.print("\nNome do item: ");
        String nome = scanner.nextLine();

        System.out.print("\nPreço unitário: ");
        double precoUnitario = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("\nPreço de custo: ");
        double precoCusto = scanner.nextDouble();
        scanner.nextLine();

        if (x.equals("PRATO PRINCIPAL") || x.equals("SOBREMESA")) {
            System.out.print("\nDescrição: ");
            String descricao = scanner.nextLine();

            System.out.print("\nTempo de preparo " + ANSI_YELLOW + "(Em minutos): " + ANSI_RESET);
            double tempoPreparo = scanner.nextDouble();
            scanner.nextLine();

            if (x.equals("PRATO PRINCIPAL")) {
                item = new PratoPrincipal(nome, descricao, tempoPreparo, precoUnitario, precoCusto, itens);
            }
            else{
                System.out.print("\nNúmero de calorias: ");
                double nroCal = scanner.nextDouble();
                scanner.nextLine();

                item = new Sobremesa(nome, descricao, tempoPreparo, precoUnitario, precoCusto, nroCal, itens);
            }
        }
        else {
            String tipoEmbalagem;
            EnumsGerais.EmbalagemBebidas embalagemConvertida;
            do {
                System.out.println("\nDigite o tipo da sua embalagem: ");
                tipoEmbalagem = scanner.nextLine();
                tipoEmbalagem = tipoEmbalagem.toUpperCase();

                embalagemConvertida = converterTipoEmbalagem(tipoEmbalagem);

                if(embalagemConvertida == null){
                    System.out.println(ANSI_RED + "\nTipo de embalagem inválida!" + ANSI_RESET);
                }
            } while (embalagemConvertida == null);

            System.out.print("Tamanho da embalagem " + ANSI_YELLOW + "(em mL): " + ANSI_RESET);
            double tamanhoEmbalagem = scanner.nextDouble();

            item = new Bebida(nome, precoUnitario, precoCusto, itens, embalagemConvertida, tamanhoEmbalagem);
        }

        return item;
    }

    public static EnumsGerais.EmbalagemBebidas converterTipoEmbalagem(String tipoEmbalagem){
        if(Objects.equals(tipoEmbalagem, "PLÁSTICO") || Objects.equals(tipoEmbalagem, "PLASTICO")){
            return EmbalagemBebidas.PLASTICO;
        }
        if(Objects.equals(tipoEmbalagem, "LATA")){
            return EmbalagemBebidas.LATA;
        }
        if(Objects.equals(tipoEmbalagem, "VIDRO")){
            return EmbalagemBebidas.VIDRO;
        }
        if(Objects.equals(tipoEmbalagem, "OUTROS")){
            return EmbalagemBebidas.OUTROS;
        }
        else{
            return null;
        }
    }

    public static Pedido cadastrarPedido(ArrayList<Funcionario> funcionarios) throws PagamentoException{
        System.out.println(ANSI_BLUE + "\nFAZENDO PEDIDO\n" + ANSI_RESET);
        Garcom garcom;
        Cozinheiro cozinheiro;

        sleep.Sleeping(1000);

        System.out.println("Chamando garçom...\n");

        sleep.Sleeping(1000);

        String c;
        int op;
        do 
        {
            do {
                System.out.println("Lista de garçons: \n");
                for (int index = 0; index < funcionarios.size(); index++) {
                    if(funcionarios.get(index) instanceof Garcom){
                        System.out.println(index + "." + funcionarios.get(index).getNome());
                    }
                }
                System.out.println("\nDigite o código do garçom: ");
                op = scanner.nextInt();
                scanner.nextLine();

                funcionarios.get(op).mostrarFuncionario();

                System.out.println("\nConfirmar o garçom?");
                c = scanner.nextLine();
                c = c.toUpperCase();
            } while (!c.equals("SIM"));

            if (funcionarios.get(op) instanceof Garcom) {
                garcom = (Garcom) funcionarios.get(op);
                garcom.registrarPedido();
                atualizarGarcom(funcionarios, garcom);
            } else {
                garcom = null;
            }
            
        } while (garcom == null);

        do 
        {
            do {
                System.out.println("\nLista de cozinheiros: \n");
                for (int index = 0; index < funcionarios.size(); index++) {
                    if(funcionarios.get(index) instanceof Cozinheiro){
                        System.out.println(index + "." + funcionarios.get(index).getNome());
                    }
                }
                System.out.println("\nDigite o código do cozinheiro: ");
                op = scanner.nextInt();
                scanner.nextLine();

                funcionarios.get(op).mostrarFuncionario();

                System.out.println("\nConfirmar o cozinheiro?");
                c = scanner.nextLine();
                c = c.toUpperCase();
            } while (!c.equals("SIM"));

            if (funcionarios.get(op) instanceof Cozinheiro) {
                cozinheiro = (Cozinheiro) funcionarios.get(op);
            } else {
                cozinheiro = null;
            }
            
        } while (cozinheiro == null);

        return new Pedido(garcom, cozinheiro);
    }

    public static void fecharMes(ArrayList<Funcionario> funcionarios, int nroPedidos){

        demitirFuncionario(funcionarios, nroPedidos);

        nroPedidos = 0;

        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof Cozinheiro) {
                System.out.println(ANSI_CYAN + "\nSalário do cozinheiro: " + funcionario.getNome() + " - R$ " + funcionario.calcularSalario() + ANSI_RESET);
                funcionario.zerarPedidos();
                atualizarCozinheiro(funcionarios, (Cozinheiro) funcionario);
            } else {
                System.out.println(ANSI_CYAN + "\nSalário do garçom: " + funcionario.getNome() + " R$ " + ((Garcom) funcionario).calcularSalario(nroPedidos) + ANSI_RESET);
                funcionario.zerarPedidos();
                atualizarGarcom(funcionarios, (Garcom) funcionario);
            }
        }
    }

    public static void mostrarItens(ArrayList<Itens> itens){
        String input;
        int op;
        do {
            System.out.println("\nLista de itens: \n");
            for (int index = 0; index < itens.size(); index++) {
                System.out.println(index + "." + itens.get(index).getNome());
            }

            System.out.println("\nDigite o índice do item que deseja obter mais informações: \n");
            op = scanner.nextInt();
            scanner.nextLine();

            itens.get(op).mostrarItem();

            System.out.println("\nDeseja verificar outro item?");
            input = scanner.nextLine();
            input = input.toUpperCase();
        } while (!input.equals("NÃO") && !input.equals("NAO"));
    }

    public static void mostrarFuncionarios(ArrayList<Funcionario> funcionarios){
        String input;
        int op;
        do {
            System.out.println("\nLista de funcionários: \n");
            for (int index = 0; index < funcionarios.size(); index++) {
                System.out.println(index + "." + funcionarios.get(index).getNome());
            }

            System.out.println("\nDigite o índice do funcionário que deseja obter mais informações: ");
            op = scanner.nextInt();
            scanner.nextLine();

            funcionarios.get(op).mostrarFuncionario();

            System.out.println("\nDeseja verificar outro funcionario?");
            input = scanner.nextLine();
            input = input.toUpperCase();
        } while (!input.equals("NÃO") && !input.equals("NAO"));
    }

    public static void mostrarPedidos(ArrayList<Pedido> pedidos){
        String input;
        int op;
        do {
            System.out.println("\nLista de pedidos: \n");
            for (int index = 0; index < pedidos.size(); index++) {
                System.out.println("Pedido " + index);
            }

            System.out.println("\nDigite o número do pedido para mais informações: ");
            op = scanner.nextInt();
            scanner.nextLine();

            pedidos.get(op).mostrarPedido();

            System.out.println("\nDeseja verificar outro pedido?");
            input = scanner.nextLine();
            input = input.toUpperCase();
        } while (!input.equals("NÃO") && !input.equals("NAO"));
    }

    public static void demitirFuncionario(ArrayList<Funcionario> funcionarios, int pedidosTotais){

        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof Garcom) {
                ((Garcom)funcionario).verificarPerformance(pedidosTotais);
                if (((Garcom)funcionario).verificarDemissao()) {
                    funcionarios.remove(funcionario);
                }
            }
        }
    }

    // para itens

    public static void escreverNoArquivoItens(Itens objetoA) {
        try {
            List<Itens> objetos = lerArquivoItens();
            objetos.add(objetoA);

            FileOutputStream fileOut = new FileOutputStream("Itens.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever todos os objetos no arquivo
            for (Itens objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();
            System.out.println(ANSI_GREEN + "\nO item foi cadastrado no sistema!" + ANSI_RESET);

        } catch (IOException e) {
            System.out.println(ANSI_RED + "\nOcorreu um erro ao cadastrar: " + e.getMessage() + ANSI_RESET);
        }
    }

    public static List<Itens> lerArquivoItens() {
        List<Itens> objetos = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("Itens.bin");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Itens objetoLido;
            while (true) {
                try {
                    objetoLido = (Itens) objectIn.readObject();
                    objetos.add(objetoLido);
                } catch (EOFException e) {
                    // Fim do arquivo alcançado, não há mais objetos para ler
                    break;
                }
            }

            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            // Ignorar se o arquivo não existir ou estiver vazio
        }

        return objetos;
    }

    public static void excluirObjetoItens(String nome) {
        List<Itens> objetos = lerArquivoItens();

        Iterator<Itens> iterator = objetos.iterator();
        scanner.nextLine();
        while (iterator.hasNext()) {
            Itens objeto = iterator.next();
            if (objeto.getNome().equals(nome)) {
                iterator.remove();
                System.out.println(ANSI_GREEN + "\nItem '" + nome + "' foi excluído com sucesso!" + ANSI_RESET);
            }
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("Itens.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever os objetos atualizados no arquivo
            for (Itens objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();

        } catch (IOException e) {
            System.out.println(ANSI_RED + "\nOcorreu um erro ao escrever no arquivo: " + e.getMessage() + ANSI_RESET);
        }
    }

    // para funcionários

    public static void escreverNoArquivoFuncionarios(Funcionario objetoA) {
        try {
            List<Funcionario> objetos = lerArquivoFuncionario();
            objetos.add(objetoA);

            FileOutputStream fileOut = new FileOutputStream("Funcionario.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever todos os objetos no arquivo
            for (Funcionario objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();
            System.out.println(ANSI_GREEN + "\nO funcionário foi cadastrado no sistema!" + ANSI_RESET);

        } catch (IOException e) {
            System.out.println(ANSI_RED + "\nOcorreu um erro ao cadastrar: " + e.getMessage() + ANSI_RESET);
        }
    }

    public static List<Funcionario> lerArquivoFuncionario() {
        List<Funcionario> objetos = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("Funcionario.bin");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Funcionario objetoLido;
            while (true) {
                try {
                    objetoLido = (Funcionario) objectIn.readObject();
                    objetos.add(objetoLido);
                } catch (EOFException e) {
                    // Fim do arquivo alcançado, não há mais objetos para ler
                    break;
                }
            }

            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            // Ignorar se o arquivo não existir ou estiver vazio
        }

        return objetos;
    }

    public static void excluirObjetoFuncionario(String nome) {
        List<Funcionario> objetos = lerArquivoFuncionario();

        Iterator<Funcionario> iterator = objetos.iterator();
        scanner.nextLine();
        while (iterator.hasNext()) {
            Funcionario objeto = iterator.next();
            if (objeto.getNome().equals(nome)) {
                iterator.remove();
                System.out.println(ANSI_GREEN + "\nFuncionário '" + nome + "' foi excluído com sucesso!");
            }
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("Funcionario.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever os objetos atualizados no arquivo
            for (Funcionario objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();

        } catch (IOException e) {
            System.out.println(ANSI_RED + "\nOcorreu um erro ao escrever no arquivo: " + e.getMessage() + ANSI_RESET);
        }
    }

    // para ingredientes

    public static void escreverNoArquivoIngredientes(Ingredientes objetoA) {
        try {
            List<Ingredientes> objetos = lerArquivoIngredientes();
            objetos.add(objetoA);

            FileOutputStream fileOut = new FileOutputStream("Ingredientes.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever todos os objetos no arquivo
            for (Ingredientes objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();
            System.out.println(ANSI_GREEN + "\nO ingrediente foi armazenado no sistema!" + ANSI_RESET);

        } catch (IOException e) {
            System.out.println(ANSI_RED + "\nOcorreu um erro ao armazenar: " + e.getMessage() + ANSI_RESET);
        }
    }

    public static List<Ingredientes> lerArquivoIngredientes() {
        List<Ingredientes> objetos = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("Ingredientes.bin");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Ingredientes objetoLido;
            while (true) {
                try {
                    objetoLido = (Ingredientes) objectIn.readObject();
                    objetos.add(objetoLido);
                } catch (EOFException e) {
                    // Fim do arquivo alcançado, não há mais objetos para ler
                    break;
                }
            }

            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            // Ignorar se o arquivo não existir ou estiver vazio
        }

        return objetos;
    }

    public static void excluirObjetoIngredientes(String nome) {
        List<Ingredientes> objetos = lerArquivoIngredientes();

        Iterator<Ingredientes> iterator = objetos.iterator();
        scanner.nextLine();
        while (iterator.hasNext()) {
            Ingredientes objeto = iterator.next();
            if (objeto.getNome().equals(nome)) {
                iterator.remove();
                System.out.println(ANSI_GREEN + "\nO ingrediente '" + nome + "' foi excluído com sucesso!" + ANSI_RESET);
            }
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("Ingredientes.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever os objetos atualizados no arquivo
            for (Ingredientes objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();

        } catch (IOException e) {
            System.out.println(ANSI_RED + "\nOcorreu um erro ao escrever no arquivo: " + e.getMessage() + ANSI_RESET);
        }
    }

    // para pedidos

    public static void escreverNoArquivoPedido(Pedido objetoA) {
        try {
            List<Pedido> objetos = lerArquivoPedido();
            objetos.add(objetoA);

            FileOutputStream fileOut = new FileOutputStream("Pedido.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever todos os objetos no arquivo
            for (Pedido objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();
            System.out.println(ANSI_GREEN + "\nO pedido foi armazenado no sistema!" + ANSI_RESET);

        } catch (IOException e) {
            System.out.println(ANSI_RED + "\nOcorreu um erro ao armazenar o pedido no sistema: " + e.getMessage() + ANSI_RESET);
        }
    }

    public static List<Pedido> lerArquivoPedido() {
        List<Pedido> objetos = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("Pedido.bin");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Pedido objetoLido;
            while (true) {
                try {
                    objetoLido = (Pedido) objectIn.readObject();
                    objetos.add(objetoLido);
                } catch (EOFException e) {
                    // Fim do arquivo alcançado, não há mais objetos para ler
                    break;
                }
            }

            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            // Ignorar se o arquivo não existir ou estiver vazio
        }

        return objetos;
    }
}