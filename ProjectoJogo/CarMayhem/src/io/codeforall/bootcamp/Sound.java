package io.codeforall.bootcamp;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioSystem;




public class Sound {

    private Clip clip;

    private AudioInputStream audioInputStream;

    public void playSound(String soundFilePath) {
        try {

            File soundLife = new File(soundFilePath);

            audioInputStream = AudioSystem.getAudioInputStream(soundLife);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void stopSound() throws IOException {
        clip.stop();
        audioInputStream.close();
    }
    public void loopSound(int count){
        if(clip != null){
            clip.loop(count);
        }
    }
}



