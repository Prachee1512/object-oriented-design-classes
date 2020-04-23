package pl.agh.edu.dp.labirynth.Factories;

import pl.agh.edu.dp.labirynth.Models.EnchantedDoor;
import pl.agh.edu.dp.labirynth.Models.EnchantedRoom;
import pl.agh.edu.dp.labirynth.Models.EnchantedWall;
import pl.agh.edu.dp.labirynth.Models.Room;

public class EnchantedMazeFactory extends MazeFactory {
    private static final EnchantedMazeFactory instance = new EnchantedMazeFactory();
    protected EnchantedMazeFactory() {};

    public static EnchantedMazeFactory getInstance(){
        return instance;
    }
    @Override
    public EnchantedRoom createRoom(Integer id) {
        return new EnchantedRoom(id);
    }

    @Override
    EnchantedWall createWall() {
        return new EnchantedWall();
    }

    @Override
    EnchantedDoor createDoor(Room r1, Room r2) {
        return new EnchantedDoor(r1, r2);
    }
}
