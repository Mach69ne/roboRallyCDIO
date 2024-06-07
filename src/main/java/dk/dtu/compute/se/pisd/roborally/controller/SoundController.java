package dk.dtu.compute.se.pisd.roborally.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class SoundController {

    private MediaPlayer mediaPlayer;

    public SoundController(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void playSound(String soundSrc) {
        try {
            String soundPath = getClass().getResource("/sounds/" + soundSrc + ".wav").toExternalForm();
            Media soundMedia = new Media(soundPath);
            mediaPlayer = new MediaPlayer(soundMedia);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
