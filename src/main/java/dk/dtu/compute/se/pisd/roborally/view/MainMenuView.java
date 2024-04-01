package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.controller.AppController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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


        Button newGameButton = new Button();
        newGameButton.setText("New Game");
        this.getChildren().add(newGameButton);
        newGameButton.setOnAction(e -> appController.newGame());

        Button loadGameButton = new Button();
        loadGameButton.setText("Load Game");
        this.getChildren().add(loadGameButton);
        loadGameButton.setOnAction(e -> appController.loadGame());

        Button exitGameButton = new Button();
        exitGameButton.setText("Exit Game");
        this.getChildren().add(exitGameButton);
        exitGameButton.setOnAction(e -> appController.exit());

        //Set spacing between buttons here
        this.setSpacing(30);
    }
}
