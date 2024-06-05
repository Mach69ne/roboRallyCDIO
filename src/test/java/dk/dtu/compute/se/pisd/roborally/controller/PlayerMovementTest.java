package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.model.Board;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import javafx.application.Platform;
import org.junit.jupiter.api.*;

/**
 * Test class for playermovement.
 * @Author Emil
 */
public class PlayerMovementTest {
    private final int TEST_WIDTH = 8;
    private final int TEST_HEIGHT = 8;

    private GameController gameController;
    private MoveController moveController;

        @BeforeAll
        static void setUpAll() {
            Platform.startup(() -> {});
        }

        @BeforeEach
        void setUp() {
            Board board = new Board(TEST_WIDTH, TEST_HEIGHT);
            gameController = new GameController(board);
            moveController = new MoveController(gameController);

            for (int i = 0; i < 2; i++) {
                Player player = new Player(board, null,"Player " + i, moveController);
                board.addPlayer(player);
                player.setSpace(board.getSpace(i, i));
                player.setHeading(Heading.values()[i % Heading.values().length]);
            }
            board.setCurrentPlayer(board.getPlayer(0));
        }

        @AfterEach
        void tearDown() {
            gameController = null;
            moveController = null;
        }

        @Test
        void moveForward() {
            Board board = gameController.board;
            Player current = board.getCurrentPlayer();
            current.moveController.moveForward(current);
            Assertions.assertEquals(current.getSpace(), board.getSpace(0, 1));
        }

        @Test
        void moveBack() {
            Board board = gameController.board;
            Player current = board.getCurrentPlayer();
            current.setSpace(board.getSpace(0, 1));
            current.moveController.moveBack(current);
            Assertions.assertEquals(current.getSpace(), board.getSpace(0, 0));
        }

        @Test
        void turnRight() {
            Board board = gameController.board;
            Player current = board.getCurrentPlayer();
            current.moveController.turnRight(current);
            Assertions.assertEquals(current.getHeading(), Heading.WEST);
        }

        @Test
        void turnLeft() {
            Board board = gameController.board;
            Player current = board.getCurrentPlayer();
            current.moveController.turnLeft(current);
            Assertions.assertEquals(current.getHeading(), Heading.EAST);
        }

        @Test
        void uTurn() {
            Board board = gameController.board;
            Player current = board.getCurrentPlayer();
            current.moveController.uTurn(current);
            Assertions.assertEquals(current.getHeading(), Heading.NORTH);
        }

        @Test
        void fastForward() {
            Board board = gameController.board;
            Player current = board.getCurrentPlayer();
            current.moveController.fastForward(current);
            Assertions.assertEquals(current.getSpace(), board.getSpace(0, 2));
        }

        @Test
        void moveThree() {
            Board board = gameController.board;
            Player current = board.getCurrentPlayer();
            current.moveController.moveThree(current);
            Assertions.assertEquals(current.getSpace(), board.getSpace(0, 3));
        }

        @Test
        void pushPlayer() {
            Board board = gameController.board;
            Player current = board.getCurrentPlayer();
            Player playerToBePushed = board.getPlayer(1);
            current.setSpace(board.getSpace(1, 0));
            playerToBePushed.setSpace(board.getSpace(1, 1));
            current.moveController.moveForward(current);
            Assertions.assertEquals(playerToBePushed.getSpace(), board.getSpace(1, 2));
            Assertions.assertEquals(current.getSpace(), board.getSpace(1, 1));
        }

}
