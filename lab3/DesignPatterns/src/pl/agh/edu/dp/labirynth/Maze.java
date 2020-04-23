package pl.agh.edu.dp.labirynth;

import pl.agh.edu.dp.labirynth.Models.Room;

import java.util.Vector;

public class Maze {
    public static final Integer START_ROOM = 0;
    public static final Integer FINAL_ROOM = 15;

    private final Vector<Room> rooms;

    public Maze() {
        this.rooms = new Vector<>();
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public int getRoomNumbers(){
        return rooms.size();
    }

    public Room getStartRoom(){
        return rooms.stream()
                .filter(room -> room.getRoomNumber().equals(START_ROOM))
                .findFirst().orElse(null);
    }

    public Room getEndRoom() {
        return rooms.stream()
                .filter(room -> room.getRoomNumber().equals(FINAL_ROOM))
                .findFirst().orElse(null);
    }
}
