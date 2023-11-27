import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

public class SoundTrack {

    public void Musica1 () {
        try {
            //local path do arquivo .wav de audio
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\jeans\\OneDrive\\IntelliJ\\POO1\\testes\\lalala.wav.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); //Para repetir o som até o fim da execução. 0 para repetir uma só vez
            // clip.close(); fecha o som
            // clip.stop(); pausa a música

            // só de teste:
            /*SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(null, "Clique pra fechar!");
                }
            });*/

            //genérico, pois há diversos erros possíveis
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
    }
}
