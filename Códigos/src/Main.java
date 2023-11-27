import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArrayList<Itens> socorro = new ArrayList<>();

        for (int i = 0; i < 1757701; i++) {
            try {
                 PratoPrincipal um = new PratoPrincipal("teste", "descricaooo", 22, 10, 5, socorro);
                 socorro.add(um);
                 if (socorro.get(i) instanceof PratoPrincipal) {
                     System.out.println(((PratoPrincipal) socorro.get(i)).getCodigo());
                 }
            }
            catch (ErroCodigoException error) {
                 System.out.println(error.getMessage());
            }
        }
    }
}