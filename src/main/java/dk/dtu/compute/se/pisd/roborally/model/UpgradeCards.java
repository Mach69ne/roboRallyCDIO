package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;

public class UpgradeCards extends Subject {
    
    private String name;
    private int price;

    /**
     * @param name
     * @param price
     * @author Mads
     */
    UpgradeCards(String name, int price) {
        this.name = name;
        this.price = price;
    }

    /**
     * @return
     * @author Mads
     */
    public String getName() {
        return this.name;
    }


    /**
     * @return
     * @author Mads
     */
    public int getPrice() {
        return this.price;
    }

}
