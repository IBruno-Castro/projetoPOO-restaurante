import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Itens> socorro = new ArrayList<>();
         for (int i = 0; i < 15; i+=3) {
             PratoPrincipal um = new PratoPrincipal("nomeee", "descricaooo", 22, 10, 5, socorro);
             socorro.add(um);
             Bebida dois = new Bebida("bebidaaa", 15, 10, socorro, "lata", "310ml");
             socorro.add(dois);
             Sobremesa tres = new Sobremesa("sobremessaaaaa", "descriptionnn", 54, 25, 20, 1500, socorro);
             socorro.add(tres);
         }

         for (int i = 0; i < socorro.size(); i++) {
             if (socorro.get(i) instanceof PratoPrincipal) {
                 ((PratoPrincipal) socorro.get(i)).mostrarPrato();
             }
             if (socorro.get(i) instanceof Sobremesa) {
                 ((Sobremesa) socorro.get(i)).mostrarPrato();
             }
             if (socorro.get(i) instanceof Bebida) {
                 ((Bebida) socorro.get(i)).mostrarBebida();
             }
         }
    }
}