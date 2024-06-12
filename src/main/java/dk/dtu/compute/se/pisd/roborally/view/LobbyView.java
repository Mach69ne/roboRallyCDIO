package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LobbyView extends VBox {

    private TextField searchBar;
    private TextArea chatArea;

    public LobbyView() {
        searchBar = new TextField();
        searchBar.setPromptText("Search lobbies...");
        chatArea = new TextArea();
        chatArea.setEditable(false);
        this.getChildren().addAll(searchBar, chatArea);
    }

    public TextField getSearchBar() {
        return searchBar;
    }

    public TextArea getChatArea() {
        return chatArea;
    }
}
