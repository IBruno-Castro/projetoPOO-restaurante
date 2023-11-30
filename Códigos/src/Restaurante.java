import java.io.*;
import java.util.*;

public class Restaurante implements EnumsGerais {
    static Scanner scanner = new Scanner(System.in);
    static SleepMetod sleep = new SleepMetod();
    static SoundTrack sound = new SoundTrack();

    public static void main(String[] args) {
        sound.MusicFundo();

        ArrayList<Funcionario> funcionarios = (ArrayList<Funcionario>) lerArquivoFuncionario();
        ArrayList<Itens> itens = (ArrayList<Itens>) lerArquivoItens();
        ArrayList<Pedido> pedidos = (ArrayList<Pedido>) lerArquivoPedido();
        ArrayList<Ingredientes> ingredientes = (ArrayList<Ingredientes>) lerArquivoIngredientes();

        int nroPedidos = 0;
        int opcao;

        do {
            System.out.println("BEM-VINDO AO RESTAURANTE\n");
            System.out.println("============== Menu ==============");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Cadastrar Ingrediente");
            System.out.println("3. Cadastrar Item");
            System.out.println("4. Cadastrar Pedido");
            System.out.println("5. Checar Item");
            System.out.println("6. Checar Funcionário");
            System.out.println("7. Checar Pedido");
            System.out.println("8. Checar Ingredientes");
            System.out.println("9. Fechar mês");
            System.out.println("10. Excluir do sistema");
            System.out.println("0. Sair");
            System.out.println("==================================");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            String escolha;
            int op;

            switch (opcao) {
                case 1 -> {
                    funcionarios.add(cadastrarFuncionario());
                    sound.MusicConcluido();
                    if (funcionarios.get(funcionarios.size() - 1) instanceof Cozinheiro) {

                        System.out.println("Adicione pratos preparados pelo cozinheiro");
                        do {
                            do {
                                System.out.println("\nLista de pratos: ");
                                for (int index = 0; index < itens.size(); index++) {
                                    if (itens.get(index) instanceof Prato) {
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

                            ((Cozinheiro) funcionarios.get(funcionarios.size() - 1)).addPrato((Prato) itens.get(op));
                            sound.MusicConcluido();

                            System.out.println("Quer adicionar outro prato? s/n");
                            escolha = scanner.nextLine();
                        } while (!escolha.equals("n"));
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
                                sound.MusicConcluido();

                                System.out.println("Quer adicionar outro ingrediente? s/n");
                                escolha = scanner.nextLine();
                            } while (!escolha.equals("n"));
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
                            } while (!escolha.equals("s"));

                            System.out.println("Digite a quantidade deseja adicionar: ");
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

                            System.out.println("Deseja adicionar outro item? s/n");
                            escolha = scanner.nextLine();
                        } while (escolha.equals("s"));

                        pedidos.get(pedidos.size() - 1).calcularValorTotal();

                        System.out.println("Digite a forma de pagamento: ");
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
                    System.out.println("Ingredientes cadastrados: \n");
                    ingredientes = (ArrayList<Ingredientes>) lerArquivoIngredientes();
                    for (Ingredientes ingrediente : ingredientes) {
                        ingrediente.mostrarIngrediente();
                        System.out.print("\n");
                    }
                }
                case 9 -> fecharMes(funcionarios, nroPedidos);
                case 10 -> {
                    System.out.println("\nVocê quer excluir: " +
                            "\n 1. Funcionários" +
                            "\n 2. Itens" +
                            "\n 3. Ingredientes");

                    int exc = scanner.nextInt();

                    switch (exc) {
                        case 1 -> {
                            System.out.println("Digite o nome do funcionário: ");
                            String nome = scanner.nextLine();
                            excluirObjetoFuncionario(nome);
                            funcionarios = (ArrayList<Funcionario>) lerArquivoFuncionario();
                        }
                        case 2 -> {
                            System.out.println("Digite o nome do item: ");
                            String nome = scanner.nextLine();
                            excluirObjetoItens(nome);
                            itens = (ArrayList<Itens>) lerArquivoItens();
                        }
                        case 3 -> {
                            System.out.println("Digite o nome do ingrediente: ");
                            String nome = scanner.nextLine();
                            excluirObjetoIngredientes(nome);
                            ingredientes = (ArrayList<Ingredientes>) lerArquivoIngredientes();
                        }
                        default -> System.out.println("\nOpção inválida!");
                    }
                }
                case 0 -> System.out.println("Saindo do programa. Até mais!");
                default -> System.out.println("Opção inválida. Tente novamente.");
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
        EnumsGerais.EstadoCivil ecConvertido;
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
            EnumsGerais.DiaSemana diaConvertido;
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

    public static Itens cadastrarItens(ArrayList<Itens> itens) throws ErroCodigoException{
        System.out.println("CADASTRAMENTO DE ITENS\n");
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
            String tipoEmbalagem;
            EnumsGerais.EmbalagemBebidas embalagemConvertida;
            do {
                System.out.println("\nDigite o tipo da sua embalagem: ");
                tipoEmbalagem = scanner.nextLine();

                embalagemConvertida = converterTipoEmbalagem(tipoEmbalagem);

                if(embalagemConvertida == null){
                    System.out.println("Tipo de embalagem inválida!");
                }
            } while (embalagemConvertida == null);

            System.out.print("Tamanho da embalagem (em mL): ");
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
        System.out.println("CADASTRAMENTO DE PEDIDOS\n");
        Garcom garcom;
        Cozinheiro cozinheiro;

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
                garcom.registrarPedido();
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
                System.out.println("Digite o codigo do cozinheiro: ");
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

    public static void fecharMes(ArrayList<Funcionario> funcionarios, int nroPedidos){

        demitirFuncionario(funcionarios, nroPedidos);

        nroPedidos = 0;

        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof Cozinheiro) {
                System.out.println("Salario de " + funcionario.getNome() + ": " + funcionario.calcularSalario());
            } else {
                System.out.println("Salario de " + funcionario.getNome() + ": " + ((Garcom) funcionario).calcularSalario(nroPedidos));
            }
        }
    }

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

            System.out.println("Digite o numero do pedido para mais informações: ");
            op = scanner.nextInt();
            scanner.nextLine();

            pedidos.get(op).mostrarPedido();

            System.out.println("Deseja verificar outro pedido?");
            input = scanner.nextLine();
        } while (!input.equals("n"));
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

            FileOutputStream fileOut = new FileOutputStream("itens.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever todos os objetos no arquivo
            for (Itens objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();
            System.out.println("O item foi cadastrado no sistema.");

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao cadastrar: " + e.getMessage());
        }
    }

    public static List<Itens> lerArquivoItens() {
        List<Itens> objetos = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("itens.bin");
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
        while (iterator.hasNext()) {
            Itens objeto = iterator.next();
            if (objeto.getNome().equals(nome)) {
                iterator.remove();
                System.out.println("Objeto com o nome '" + nome + "' excluído.");
            }
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("itens.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever os objetos atualizados no arquivo
            for (Itens objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    // para funcionários

    public static void escreverNoArquivoFuncionarios(Funcionario objetoA) {
        try {
            List<Funcionario> objetos = lerArquivoFuncionario();
            objetos.add(objetoA);

            FileOutputStream fileOut = new FileOutputStream("funcionario.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever todos os objetos no arquivo
            for (Funcionario objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();
            System.out.println("O funcionário foi cadastrado no sistema.");

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao cadastrar: " + e.getMessage());
        }
    }

    public static List<Funcionario> lerArquivoFuncionario() {
        List<Funcionario> objetos = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("funcionario.bin");
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
        while (iterator.hasNext()) {
            Funcionario objeto = iterator.next();
            if (objeto.getNome().equals(nome)) {
                iterator.remove();
                System.out.println("Objeto com o nome '" + nome + "' excluído.");
            }
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("funcionario.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever os objetos atualizados no arquivo
            for (Funcionario objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    // para ingredientes

    public static void escreverNoArquivoIngredientes(Ingredientes objetoA) {
        try {
            List<Ingredientes> objetos = lerArquivoIngredientes();
            objetos.add(objetoA);

            FileOutputStream fileOut = new FileOutputStream("ingredientes.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever todos os objetos no arquivo
            for (Ingredientes objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();
            System.out.println("O ingrediente foi armazenado no sistema.");

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao armazenar: " + e.getMessage());
        }
    }

    public static List<Ingredientes> lerArquivoIngredientes() {
        List<Ingredientes> objetos = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("ingredientes.bin");
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
        while (iterator.hasNext()) {
            Ingredientes objeto = iterator.next();
            if (objeto.getNome().equals(nome)) {
                iterator.remove();
                System.out.println("Objeto com o nome '" + nome + "' excluído.");
            }
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("ingredientes.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever os objetos atualizados no arquivo
            for (Ingredientes objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    // para pedidos

    public static void escreverNoArquivoPedido(Pedido objetoA) {
        try {
            List<Pedido> objetos = lerArquivoPedido();
            objetos.add(objetoA);

            FileOutputStream fileOut = new FileOutputStream("pedido.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escrever todos os objetos no arquivo
            for (Pedido objeto : objetos) {
                objectOut.writeObject(objeto);
            }

            // Fechar o ObjectOutputStream após a escrita
            objectOut.close();
            System.out.println("O pedido foi armazenado no sistema.");

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao armazenar o pedido no sistema: " + e.getMessage());
        }
    }

    public static List<Pedido> lerArquivoPedido() {
        List<Pedido> objetos = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("pedido.bin");
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