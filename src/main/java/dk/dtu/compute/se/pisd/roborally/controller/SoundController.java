package dk.dtu.compute.se.pisd.roborally.controller;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundController {
    private static SoundController instance;
    private Clip clip;

    private SoundController() {}

    public static SoundController getInstance() {
        if(instance == null) {
            instance = new SoundController();
        }
        return instance;
    }

    public void playSound(String soundSrc) {
        try {
            // Open an audio input stream.
            URL url = getClass().getResource("/sounds/" + soundSrc + ".wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(-30.00f);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopSound() {
        if(clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}