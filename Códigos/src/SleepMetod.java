public class SleepMetod {
    public SleepMetod() {
    }

    public void Sleeping(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException error) {
            System.out.println("Erro: " + error);
        }
    }
}