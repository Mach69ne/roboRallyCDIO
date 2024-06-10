package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import javafx.scene.Node;

public interface Animatable {
    void startAnimation();
    void stopAnimation();
    void setAnimationProperties(double endX, double endY, double duration);
    Node getNode();
    double getEndX();
    double getEndY();
    double getDuration();
}
