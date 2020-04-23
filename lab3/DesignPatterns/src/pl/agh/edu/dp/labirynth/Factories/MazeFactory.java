package pl.agh.edu.dp.labirynth.Factories;

import pl.agh.edu.dp.labirynth.Models.Door;
import pl.agh.edu.dp.labirynth.Models.Room;
import pl.agh.edu.dp.labirynth.Models.Wall;

public class MazeFactory {
    private static final MazeFactory instance = new MazeFactory();
    protected MazeFactory() {};

    public static MazeFactory getInstance(){
        return instance;
    }

    public Room createRoom(Integer id){
        return new Room(id);
    }
    Wall createWall(){
        return new Wall();
    }
    Door createDoor(Room r1, Room r2){
        return new Door(r1, r2);
    }
}
