import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<itens> socorro = new ArrayList<>();
         for (int i = 0; i < 15; i+=3) {
             pratoPrincipal um = new pratoPrincipal("nomeee", "descricaooo", 22, 10, 5, socorro);
             socorro.add(um);
             bebida dois = new bebida("bebidaaa", 15, 10, socorro, "lata", "310ml");
             socorro.add(dois);
             sobremesa tres = new sobremesa("sobremessaaaaa", "descriptionnn", 54, 25, 20, 1500, socorro);
             socorro.add(tres);
         }

         for (int i = 0; i < socorro.size(); i++) {
             if (socorro.get(i) instanceof pratoPrincipal) {
                 ((pratoPrincipal) socorro.get(i)).mostrarPrato();
             }
             if (socorro.get(i) instanceof sobremesa) {
                 ((sobremesa) socorro.get(i)).mostrarPrato();
             }
             if (socorro.get(i) instanceof bebida) {
                 ((bebida) socorro.get(i)).mostrarBebida();
             }
         }
    }
}