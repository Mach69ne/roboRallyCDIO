package dk.dtu.compute.se.pisd.roborally.controller;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundController {
    private static SoundController instance;
    public MediaPlayer mediaPlayer;

    private SoundController() {}

    public static SoundController getInstance() {
        if(instance == null) {
            instance = new SoundController();
        }
        return instance;
    }

    public void playSound(String soundSrc) {
        try{
            java.net.URL resourceUrl = getClass().getResource("/Sounds/" + soundSrc + ".wav");
            if (resourceUrl == null) {
                throw new IllegalArgumentException("Sound file not found: " + soundSrc);
            }
            String soundPath = resourceUrl.toExternalForm();
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
