package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.model.BoardElements.Animatable;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationController {

    public void animate(Node node, double endX, double endY, double duration) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setToX(endX);
        transition.setToY(endY);
        transition.setDuration(Duration.seconds(duration));
        transition.play();
    }

    public void animate(Animatable animatable) {
        animate(animatable.getNode(), animatable.getEndX(), animatable.getEndY(), animatable.getDuration());
    }
}
