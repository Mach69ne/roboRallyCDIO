package dk.dtu.compute.se.pisd.roborally.model.BoardElements;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import dk.dtu.compute.se.pisd.roborally.model.Position;
import dk.dtu.compute.se.pisd.roborally.model.Space;

import java.util.Arrays;

public class Antenna extends NullBoardElement
{
    private Player[] players;
    private Position[] offsetArr;

    public Antenna(Space space)
    {
        super(space);
        space.board.addBoardElement(Board.ANTENNA_INDEX, this);
    }

    @Override
    public void activate()
    {
        Board board = this.getSpace().board;
        Position positionOfSpace = board.getIndexOfSpace(this.getSpace());
        this.players = new Player[board.getPlayersNumber()];
        this.offsetArr = new Position[board.getPlayersNumber()];
        Arrays.fill(players, null);
        Arrays.fill(offsetArr, null);
        for (int i = 0; i < board.getPlayersNumber(); i++)
        {
            insertPlayerIntoTempArr(board.getPlayer(i));
        }
    }

    private void insertPlayerIntoTempArr(Player playerToInsert)
    {
        Board board = this.getSpace().board;
        Position positionOfPlayer = board.getIndexOfSpace(playerToInsert.getSpace());
        if (positionOfPlayer == null)
        {
            //This should not happen, perhaps if player has fallen into pit
            return;
        }
        Position positionOfAntenna = board.getIndexOfSpace(this.getSpace());
        Position offset = new Position(Math.abs(positionOfPlayer.x() - positionOfAntenna.x()),
                Math.abs(positionOfPlayer.y() - positionOfAntenna.y()));

        for (int i = 0; i < this.players.length; i++)
        {
            if (players[i] == null)
            {
                players[i] = playerToInsert;
                offsetArr[i] = offset;
            }
            if (players[i].equals(playerToInsert))
            {
                continue;
            }
            int totalOffset = offset.x() + offset.y();
            if ((offsetArr[i].x() + offsetArr[i].y()) > totalOffset)
            {
                Player tempPlayer = players[i];
                players[i] = playerToInsert;
                offsetArr[i] = offset;
                insertPlayerIntoTempArr(tempPlayer);
            }
            if ((offsetArr[i].x() + offsetArr[i].y()) == totalOffset)
            {
                if (offsetArr[i].x() < offset.x())
                {
                    Player tempPlayer = players[i];
                    players[i] = playerToInsert;
                    offsetArr[i] = offset;
                    insertPlayerIntoTempArr(tempPlayer);
                }
            }

        }
    }
}
