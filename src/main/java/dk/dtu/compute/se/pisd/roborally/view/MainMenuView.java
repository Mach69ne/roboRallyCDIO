package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.controller.AppController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * @author Elias
 */
public class MainMenuView extends VBox
{

    /**
     * @param appController The controller for the application
     * @author Elias
     *
     */
    public MainMenuView(AppController appController)
    {
        Image backgroundImage = new Image("file:src/main/resources/Images/dizzyHighway.png");
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImg));

        // Move all elements 50 pixels down.
        // Format is Top, Left, Down, Right (I think, i cant really remember at this point)
        this.setPadding(new Insets(50, 0, 0, 0));

        Label newGameLabel = createLabel("file:src/main/resources/Images/roborally.png", "RoboRally");
        this.getChildren().add(newGameLabel);

        Button newGameButton = createButton("file:src/main/resources/Images/newGame.png", "New Game");
        this.getChildren().add(newGameButton);
        newGameButton.setOnAction(e -> appController.newGame());

        Button loadGameButton = createButton("file:src/main/resources/Images/loadGame.png", "Load Game");
        this.getChildren().add(loadGameButton);
        loadGameButton.setOnAction(e -> appController.loadGame());

        Button exitGameButton = createButton("file:src/main/resources/Images/exitGame.png", "Exit Game");
        this.getChildren().add(exitGameButton);
        exitGameButton.setOnAction(e -> appController.exit());

        // Set spacing between buttons
        this.setSpacing(30);
    }
    private Button createButton(String imagePath, String tooltipText) {
        Button button = new Button();
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(350);
        button.setGraphic(imageView);
        return button;
    }
    private Label createLabel(String imagePath, String tooltipText) {
        Label label = new Label();
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(280);
        label.setGraphic(imageView);
        return label;
    }

}
