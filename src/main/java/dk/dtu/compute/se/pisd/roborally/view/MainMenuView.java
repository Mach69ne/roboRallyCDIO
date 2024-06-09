package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.controller.AppController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        // Move all elements 50 pixels down.
        // Format is Top, Left, Down, Right (I think, i cant really remember at this point)
        this.setPadding(new Insets(50, 0, 0, 0));

        Label label = new Label("RoboRally");
        this.getChildren().add(label);

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
        imageView.setFitHeight(100); // Adjust height as needed
        imageView.setFitWidth(350);  // Adjust width as needed
        button.setGraphic(imageView);
        return button;
    }
}
