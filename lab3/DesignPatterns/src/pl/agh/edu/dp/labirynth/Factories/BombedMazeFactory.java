package pl.agh.edu.dp.labirynth.Factories;

import pl.agh.edu.dp.labirynth.Models.BombedRoom;
import pl.agh.edu.dp.labirynth.Models.BombedWall;

public class BombedMazeFactory extends MazeFactory {
    private static final BombedMazeFactory instance = new BombedMazeFactory();
    protected BombedMazeFactory() {};

    public static BombedMazeFactory getInstance(){
        return instance;
    }

    @Override
    public BombedRoom createRoom(Integer id) {
        return new BombedRoom(id);
    }

    @Override
    public BombedWall createWall() {
        return new BombedWall();
    }
}
