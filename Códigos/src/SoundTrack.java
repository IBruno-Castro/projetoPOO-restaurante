import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

public class SoundTrack {

    public void MusicFundo () {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("SoundTracks\\BossaNova.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); //Para repetir o som até o fim da execução. 0 para repetir uma só vez
            // clip.close(); fecha o som
            // clip.stop(); pausa a música

            //genérico, pois há diversos erros possíveis
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            //ex.printStackTrace();
        }
    }

    public void MusicConcluido () {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("SoundTracks\\Concluido.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            clip.loop(0); //Para repetir o som até o fim da execução. 0 para repetir uma só vez
            // clip.close(); fecha o som
            // clip.stop(); pausa a música

            //genérico, pois há diversos erros possíveis
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            //ex.printStackTrace();
        }
    }
}
