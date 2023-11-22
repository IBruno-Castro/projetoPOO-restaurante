import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> codigosGerados = new ArrayList<>();
        int totalGeracoes = 1757600;

        for (int i = 0; i < totalGeracoes; i++) {
            String novoCodigo;
            if (codigosGerados.isEmpty()) {
                novoCodigo = "AAA00";
            } else {
                novoCodigo = gerarProximoCodigo(codigosGerados);
            }
            codigosGerados.add(novoCodigo);
            System.out.println("Código gerado: " + novoCodigo);
        }

        System.out.println("Total de códigos gerados: " + codigosGerados.size());
    }

    public static String gerarProximoCodigo(List<String> codigosGerados) {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";

        // Verificando o último código gerado (se existir) para gerar o próximo
        String ultimoCodigoGerado = codigosGerados.isEmpty() ? "AAA00" : codigosGerados.get(codigosGerados.size() - 1);

        // Verificando se o último código gerado é ZZZ99, se sim, retorna o mesmo código
        if (ultimoCodigoGerado.equals("ZZZ99")) {
            return ultimoCodigoGerado;
        }

        char[] codigoArray = ultimoCodigoGerado.toCharArray();

        // Gerando o próximo código de forma crescente
        for (int i = 4; i >= 0; i--) {
            if ((codigoArray[i] != '9') && (codigoArray[i] != 'Z')) {
                if (i >= 3) {
                    codigoArray[i] = numeros.charAt(numeros.indexOf(codigoArray[i]) + 1);
                } else {
                    codigoArray[i] = letras.charAt(letras.indexOf(codigoArray[i]) + 1);
                    if (codigoArray[i] == 'A') {
                        codigoArray[i] = 'A';
                    }
                }
                break;
            } else {
                if (i >= 3) codigoArray[i] = '0';
                else codigoArray[i] = 'A';
            }
        }

        return String.valueOf(codigoArray);
    }
}