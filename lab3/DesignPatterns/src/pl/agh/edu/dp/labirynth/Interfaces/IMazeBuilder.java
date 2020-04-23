package pl.agh.edu.dp.labirynth.Interfaces;

import pl.agh.edu.dp.labirynth.Exceptions.NoCommonWallException;
import pl.agh.edu.dp.labirynth.Models.Direction;
import pl.agh.edu.dp.labirynth.Models.Room;

public interface IMazeBuilder {
    void addRoom(Room room);
    void addDoor(Room r1, Room r2) throws NoCommonWallException;
    void addCommonWall(Room r1, Room r2, Direction commonDirRelativeToRoomOne);
}
