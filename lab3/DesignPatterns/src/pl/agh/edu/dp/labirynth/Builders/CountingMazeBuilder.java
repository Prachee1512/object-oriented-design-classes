package pl.agh.edu.dp.labirynth.Builders;

import pl.agh.edu.dp.labirynth.Exceptions.NoCommonWallException;
import pl.agh.edu.dp.labirynth.Interfaces.IMazeBuilder;
import pl.agh.edu.dp.labirynth.Models.Direction;
import pl.agh.edu.dp.labirynth.Models.Room;

import java.util.HashMap;
import java.util.Map;

public class CountingMazeBuilder implements IMazeBuilder {
    private final Map<String, Integer> counter = new HashMap<>();

    private void add(String s, int val){
        counter.putIfAbsent(s, 0);
        counter.put(s, counter.get(s) + val);
    }

    @Override
    public void addRoom(Room room) {
        add("rooms", 1);
        add("walls", 4);
    }

    @Override
    public void addDoor(Room r1, Room r2) throws NoCommonWallException {
        add("doors", 1);
        add("walls", -1);
    }

    @Override
    public void addCommonWall(Room r1, Room r2, Direction commonDirRelativeToRoomOne) {
        add("walls", -1);
    }

    public Integer getCounts(){
        return counter.values().stream().reduce(0, Integer::sum);
    }
}
