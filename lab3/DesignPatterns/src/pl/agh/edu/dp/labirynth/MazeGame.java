package pl.agh.edu.dp.labirynth;

import pl.agh.edu.dp.labirynth.Builders.StandardMazeBuilder;
import pl.agh.edu.dp.labirynth.Exceptions.NoCommonWallException;
import pl.agh.edu.dp.labirynth.Factories.BombedMazeFactory;
import pl.agh.edu.dp.labirynth.Factories.EnchantedMazeFactory;
import pl.agh.edu.dp.labirynth.Factories.MazeFactory;
import pl.agh.edu.dp.labirynth.Models.*;

import java.util.stream.IntStream;

public class MazeGame {
    private final Player player;
    private Maze maze;

    GameState gameState = GameState.NONE;

    public MazeGame(Player p){
        this.player = p;
    }

    public Player getPlayer(){
        return player;
    }

    public void movePlayer(Direction dir){
        Room currentRoom = player.getCurrentRoom();
        MapSite toMoveSite = currentRoom.getSide(dir);

        toMoveSite.Enter(player);

        if(!player.isAlive()){
            gameState = GameState.LOSE;
        }else if(player.getCurrentRoom().equals(maze.getEndRoom())){
            gameState = GameState.WIN;
        }else{
            gameState = GameState.PENDING;
        }
    }

    public GameState getState() {
        return gameState;
    }

    public void createMaze(StandardMazeBuilder builder,
                           MazeFactory mazeFactory,
                           BombedMazeFactory bombedMazeFactory,
                           EnchantedMazeFactory enchantedMazeFactory) throws NoCommonWallException {
        Room[] r = new Room[16];
        IntStream.range(0, 16).forEach(i -> {
            if(i == 3 || i == 4 || i == 8 || i == 11){ //special rooms
                r[i] = bombedMazeFactory.createRoom(i);
            }else if(i == 15){
                r[i] = enchantedMazeFactory.createRoom(i);
            }else{
                r[i] = mazeFactory.createRoom(i);
            }
            builder.addRoom(r[i]);
        });

        builder.addCommonWall(r[0], r[4], Direction.North);
        builder.addDoor(r[0], r[4]);

        builder.addCommonWall(r[0], r[1], Direction.West);
        builder.addDoor(r[0], r[1]);

        builder.addCommonWall(r[1], r[2], Direction.West);
        builder.addDoor(r[1], r[2]);

        builder.addCommonWall(r[2], r[3], Direction.West);
        builder.addDoor(r[2], r[3]);

        builder.addCommonWall(r[2], r[6], Direction.North);
        builder.addDoor(r[2], r[6]);

        builder.addCommonWall(r[4], r[5], Direction.West);
        builder.addDoor(r[4], r[5]);

        builder.addCommonWall(r[6], r[7], Direction.West);
        builder.addDoor(r[6], r[7]);

        builder.addCommonWall(r[6], r[10], Direction.North);
        builder.addDoor(r[6], r[10]);

        builder.addCommonWall(r[8], r[12], Direction.North);
        builder.addDoor(r[8], r[12]);

        builder.addCommonWall(r[9], r[10], Direction.West);
        builder.addDoor(r[9], r[10]);

        builder.addCommonWall(r[9], r[13], Direction.North);
        builder.addDoor(r[9], r[13]);

        builder.addCommonWall(r[10], r[11], Direction.West);
        builder.addDoor(r[10], r[11]);

        builder.addCommonWall(r[12], r[13], Direction.West);
        builder.addDoor(r[12], r[13]);

        builder.addCommonWall(r[13], r[14], Direction.West);
        builder.addDoor(r[13], r[14]);

        builder.addCommonWall(r[14], r[15], Direction.West);
        builder.addDoor(r[14], r[15]);

        //bomb walls
        r[5].setSide(Direction.North, bombedMazeFactory.createWall());
        r[5].setSide(Direction.West, bombedMazeFactory.createWall());
        r[6].setSide(Direction.East, bombedMazeFactory.createWall());
        r[9].setSide(Direction.South, bombedMazeFactory.createWall());

        r[10].setSide(Direction.North, bombedMazeFactory.createWall());
        r[14].setSide(Direction.South, bombedMazeFactory.createWall());

        maze = builder.getCurrentMaze();
        player.setCurrentRoom(maze.getStartRoom());
    }
}
