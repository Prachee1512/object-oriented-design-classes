package pl.agh.edu.dp.labirynth.Builders;

import pl.agh.edu.dp.labirynth.Exceptions.NoCommonWallException;
import pl.agh.edu.dp.labirynth.Interfaces.IMazeBuilder;
import pl.agh.edu.dp.labirynth.Maze;
import pl.agh.edu.dp.labirynth.Models.Direction;
import pl.agh.edu.dp.labirynth.Models.Door;
import pl.agh.edu.dp.labirynth.Models.Room;
import pl.agh.edu.dp.labirynth.Models.Wall;

import java.util.EnumSet;

public class StandardMazeBuilder implements IMazeBuilder {
    private Maze m = new Maze();

    public Maze getCurrentMaze(){
        return m;
    }

    @Override
    public void addRoom(Room room) {
        for(var d : Direction.values()){
            room.setSide(d, new Wall());
        }
        m.addRoom(room);
    }

    @Override
    public void addDoor(Room r1, Room r2) throws NoCommonWallException {
        Direction res = commonWall(r1, r2);
        Door d = new Door(r1, r2);
        r1.setSide(res, d);
        r2.setSide(res.getOpposite(), d);
    }

    private Direction commonWall(Room r1, Room r2) throws NoCommonWallException {
        return EnumSet.allOf(Direction.class).stream()
                .filter(d -> r1.equals(r2.getSide(d.getOpposite())) && r2.equals(r1.getSide(d)))
                .findFirst().orElseThrow(() -> new NoCommonWallException(r1, r2));
    }

    @Override
    public void addCommonWall(Room r1, Room r2, Direction commonDirRelativeToRoomOne) {
        r1.setSide(commonDirRelativeToRoomOne, r2);
        r2.setSide(commonDirRelativeToRoomOne.getOpposite(), r1);
    }
}
