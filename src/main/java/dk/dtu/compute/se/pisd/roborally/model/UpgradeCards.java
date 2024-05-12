package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;

public class UpgradeCards extends Subject {
    
    private String name;
    private int price;

    UpgradeCards(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName(){

        return this.name;
    }


    public int getPrice(){

        return this.price;
    }

}
