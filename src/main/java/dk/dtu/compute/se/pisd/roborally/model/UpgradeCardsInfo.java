package dk.dtu.compute.se.pisd.roborally.model;

import java.util.ArrayList;

/**
 * @author Mads
 */
public class UpgradeCardsInfo {

    /**
     * @return
     * @author Mads
     */
    public static ArrayList<UpgradeCards> createUpgradeCards(){

        ArrayList<UpgradeCards> upgradeCards = new ArrayList<>();

        upgradeCards.add(new UpgradeCards("BRAKES", 3));
        upgradeCards.add(new UpgradeCards("DOUBLE BARREL LASER", 2));
        upgradeCards.add(new UpgradeCards("DEFRAG GIZMO", 5));

        return upgradeCards;
    } 

}
