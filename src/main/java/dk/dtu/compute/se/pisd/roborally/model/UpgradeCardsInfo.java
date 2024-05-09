package dk.dtu.compute.se.pisd.roborally.model;

public class UpgradeCardsInfo {
    
    public static boolean getPermanent(Command command){
        if(command != null){
            switch(command){
                case DEFRAG_GIZMO_PUPG:
                return true;
            }
        }
    }


}
