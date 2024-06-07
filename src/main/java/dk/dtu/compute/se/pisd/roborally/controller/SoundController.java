package dk.dtu.compute.se.pisd.roborally.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundController {
    private static SoundController instance;
    private MediaPlayer mediaPlayer;

    private SoundController() {}

    public static SoundController getInstance() {
        if(instance == null) {
            instance = new SoundController();
        }
        return instance;
    }

    public void playSound(String soundSrc) {
        try{
            String soundPath = getClass().getResource("/sounds"+soundSrc+".wav").toExternalForm();
            if(soundPath == null) {
                throw new IllegalArgumentException("Sound file not found: " + soundSrc);
            }
            Media soundMedia = new Media(soundPath);
            mediaPlayer = new MediaPlayer(soundMedia);
            mediaPlayer.play();
        }catch (NullPointerException e) {
            System.err.println("Sound file not found: " + soundSrc);
        }
        catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void stopSound() {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
