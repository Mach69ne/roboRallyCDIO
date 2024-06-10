package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.UpgradeCard;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;

public class UpgradeShopView extends Dialog<UpgradeCard>
{

    private final ListView<UpgradeCard> upgradeListView;

    public UpgradeShopView(Player player)
    {
        setTitle("Upgrade Shop");
        setHeaderText("Select an upgrade to purchase:");

        upgradeListView = new ListView<>();
        upgradeListView.getItems().addAll(player.board.getUpgradeCards());

        upgradeListView.setCellFactory(lv -> new ListCell<>()
        {
            @Override
            protected void updateItem(UpgradeCard upgrade, boolean empty)
            {
                super.updateItem(upgrade, empty);
                if (empty)
                {
                    setText(null);
                }
                else
                {
                    setText(upgrade.getName() + " - Price: " + upgrade.getPrice() + " Energy Cubes");
                }
            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().add(new Label("Available Upgrades:"));
        vbox.getChildren().add(upgradeListView);
        getDialogPane().setContent(vbox);

        ButtonType purchaseButtonType = new ButtonType("Purchase", ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(purchaseButtonType, ButtonType.CANCEL);

        setResultConverter(dialogButton -> {
            if (dialogButton == purchaseButtonType)
            {
                return upgradeListView.getSelectionModel().getSelectedItem();
            }
            return null;
        });


        setOnShowing(event -> {
            Button purchaseButton = (Button) getDialogPane().lookupButton(purchaseButtonType);
            purchaseButton.addEventFilter(ActionEvent.ACTION, e -> {
                UpgradeCard selectedUpgrade = upgradeListView.getSelectionModel().getSelectedItem();
                if (selectedUpgrade != null && player.getEnergyCubes() >= selectedUpgrade.getPrice())
                {
                    selectedUpgrade.purchaseUpgrade(player, selectedUpgrade);
                }
                else
                {
                    event.consume();
                }
            });
        });
    }
}

