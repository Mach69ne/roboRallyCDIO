package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.controller.AppController;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenuView extends VBox
{

    public MainMenuView(AppController appController)
    {
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
