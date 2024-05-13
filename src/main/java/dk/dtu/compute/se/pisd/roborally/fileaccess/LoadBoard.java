/**
 * This file is part of the initial project provided for the
 * course "Project in Software Development (02362)" held at
 * DTU Compute at the Technical University of Denmark.
 * <p>
 * Copyright (C) 2019, 2020: Ekkart Kindler, ekki@dtu.dk
 * <p>
 * This software is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 2 of the License.
 * <p>
 * This project is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this project; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package dk.dtu.compute.se.pisd.roborally.fileaccess;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardElementTemplate;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.SpaceTemplate;
import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.*;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.Conveyors.BlueConveyor;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.Conveyors.GreenConveyor;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.Walls.CornerWall;
import dk.dtu.compute.se.pisd.roborally.model.BoardElements.Walls.Wall;
import dk.dtu.compute.se.pisd.roborally.model.Space;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public class LoadBoard
{

    private static final String BOARDSFOLDER = "boards";
    private static final String DEFAULTBOARD = "default";
    private static final String JSON_EXT = "json";

    /**
     * @param boardname
     * @return
     * @author Elias & Frederik
     */
    public static Board loadBoard(String boardname)
    {
        if (boardname == null)
        {
            boardname = DEFAULTBOARD;
        }
        ClassLoader classLoader = LoadBoard.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(BOARDSFOLDER + "/" + boardname + "." + JSON_EXT);
        if (inputStream == null)
        {
            // TODO these constants should be defined somewhere
            return new Board(8, 8);
        }
        // In simple cases, we can create a Gson object with new Gson():
        GsonBuilder simpleBuilder = new GsonBuilder().registerTypeAdapter(Board.class, new Adapter<Board>());
        Gson gson = simpleBuilder.create();
        Board result;
        // FileReader fileReader = null;
        JsonReader reader = null;
        try
        {
            // fileReader = new FileReader(filename);
            reader = gson.newJsonReader(new InputStreamReader(inputStream));
            BoardTemplate template = gson.fromJson(reader, BoardTemplate.class);
            result = new Board(template.width, template.height);
            for (SpaceTemplate spaceTemplate : template.spaces)
            {
                Space space = result.getSpace(spaceTemplate.x, spaceTemplate.y);
                BoardElementTemplate elementTemplate = spaceTemplate.boardElementTemplate;
                BoardElement boardElement = new NullBoardElement(true, space);
                switch (elementTemplate.type)
                {
                    case WALL -> boardElement = new Wall(elementTemplate.heading, space);
                    case ANTENNA -> boardElement = new Antenna(space);
                    case PUSHPANEL -> boardElement = new PushPanel(elementTemplate.heading, space);
                    case CHECKPOINT -> boardElement = new Checkpoint(space);
                    case ENERGYCUBE -> boardElement = new EnergyCube(space);
                    case GREEN_CONVEYOR -> boardElement = new GreenConveyor(elementTemplate.heading, space);
                    case BLUE_CONVEYOR -> boardElement = new BlueConveyor(elementTemplate.heading, space);
                    case BOARDLASER -> boardElement = new BoardLaser(space, elementTemplate.heading);
                    case REBOOTTOKEN -> boardElement = new RebootToken(elementTemplate.heading, space);
                    case GEAR -> boardElement = new Gear(space, elementTemplate.isClockwise);
                    case CORNERWALL ->
                            boardElement = new CornerWall(elementTemplate.heading, elementTemplate.heading2, space);
                }
                space.setBoardElement(boardElement);
            }
            reader.close();
            return result;
        }
        catch (IOException e1)
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                    inputStream = null;
                }
                catch (IOException e2)
                {
                }
            }
            if (inputStream != null)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException e2)
                {
                }
            }
        }
        return null;
    }

    /**
     * @param board
     * @param name
     * @author Elias & Frederik
     */
    public static void saveBoard(Board board, String name)
    {
        BoardTemplate template = new BoardTemplate();
        template.width = board.width;
        template.height = board.height;

        for (int i = 0; i < board.width; i++)
        {
            for (int j = 0; j < board.height; j++)
            {
                Space space = board.getSpace(i, j);
                BoardElement boardElement = space.getBoardElement();


                SpaceTemplate spaceTemplate = new SpaceTemplate();
                BoardElementTemplate boardElementTemplate = new BoardElementTemplate();
                boardElementTemplate.heading = boardElement.getHeading();
                boardElementTemplate.isWalkable = boardElement.getIsWalkable();
                boardElementTemplate.type = boardElement.getType();
                if (boardElement instanceof Gear)
                {
                    boardElementTemplate.isClockwise = ((Gear) boardElement).isClockwise;
                }
                if (boardElement instanceof CornerWall)
                {
                    boardElementTemplate.heading2 = ((CornerWall) boardElement).heading2;
                }
                spaceTemplate.x = space.x;
                spaceTemplate.y = space.y;
                spaceTemplate.boardElementTemplate = boardElementTemplate;
                template.spaces.add(spaceTemplate);
            }
        }
        String filename = "src/main/Resources/" + BOARDSFOLDER + "/" + name + "." + JSON_EXT;

        // In simple cases, we can create a Gson object with new:
        //
        //   Gson gson = new Gson();
        //
        // But, if you need to configure it, it is better to create it from
        // a builder (here, we want to configure the JSON serialisation with
        // a pretty printer):
        GsonBuilder simpleBuilder =
                new GsonBuilder().registerTypeAdapter(Board.class, new Adapter<Board>()).setPrettyPrinting();
        Gson gson = simpleBuilder.create();

        FileWriter fileWriter = null;
        JsonWriter writer = null;
        try
        {
            fileWriter = new FileWriter(filename);
            writer = gson.newJsonWriter(fileWriter);
            gson.toJson(template, template.getClass(), writer);
            writer.close();
        }
        catch (IOException e1)
        {
            if (writer != null)
            {
                try
                {
                    writer.close();
                    fileWriter = null;
                }
                catch (IOException e2)
                {
                }
            }
            if (fileWriter != null)
            {
                try
                {
                    fileWriter.close();
                }
                catch (IOException e2)
                {
                }
            }
        }
    }
}
