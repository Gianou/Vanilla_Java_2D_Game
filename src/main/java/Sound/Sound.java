package Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    File soundFile [] = new File [30];
    AudioInputStream [] ais = new AudioInputStream[soundFile.length];

    public Sound(){
        soundFile[0] = new File ("src/main/resources/sounds/ChillPrairie.wav");
        soundFile[1] =  new File ("src/main/resources/sounds/PickUp.wav");
        soundFile[2] =  new File ("src/main/resources/sounds/DoorOpen.wav");
    }


    public void setFile(int i){

        try {
            ais[i] = AudioSystem.getAudioInputStream(soundFile[i].getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(ais[i]);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
