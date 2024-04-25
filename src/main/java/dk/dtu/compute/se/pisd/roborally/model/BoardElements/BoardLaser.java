package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Position;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import dk.dtu.compute.se.pisd.roborally.model.Heading;

public class BoardLaser extends BoardElement{

   public BoardLaser (Space space, Heading heading){
        super(heading, true, space);
        space.board.addBoardElement(Board.BOARD_LASER_INDEX, this);
   }

   @Override
    public void activate()
    {



    }

}