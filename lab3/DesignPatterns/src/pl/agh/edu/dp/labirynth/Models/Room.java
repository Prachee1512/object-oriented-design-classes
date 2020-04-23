package pl.agh.edu.dp.labirynth.Models;

import java.util.EnumMap;
import java.util.Map;

public class Room extends MapSite
{
    private final int roomNumber;
    private final Map<Direction, MapSite> sides;

    public Room(int number){
        this.sides = new EnumMap<>(Direction.class);
        this.roomNumber = number;
    }

    public MapSite getSide(Direction direction){
        return this.sides.get(direction);
    }

    public void setSide(Direction direction, MapSite ms){
        this.sides.put(direction, ms);
    }

    public Integer getRoomNumber(){
        return this.roomNumber;
    }

    @Override
    public String toString() {
        return "Room number: " + roomNumber;
    }

    @Override
    public void Enter(Player p){
        System.out.println("You entered to room number: " + roomNumber);
    }
}
