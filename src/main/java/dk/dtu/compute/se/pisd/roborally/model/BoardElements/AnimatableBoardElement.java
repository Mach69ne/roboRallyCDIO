package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import javafx.scene.Node;

public class AnimatableBoardElement implements Animatable{
    private Node node;
    private double endX;
    private double endY;
    private double duration;

    public AnimatableBoardElement(Node node) {
        this.node = node;

    }

    @Override
    public void startAnimation() {

    }

    @Override
    public void stopAnimation() {

    }

    @Override
    public void setAnimationProperties(double endX, double endY, double duration) {
        this.endX = endX;
        this.endY = endY;
        this.duration = duration;
    }

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public double getEndX() {
        return endX;
    }

    @Override
    public double getEndY() {
        return endY;
    }

    @Override
    public double getDuration() {
        return duration;
    }
}
