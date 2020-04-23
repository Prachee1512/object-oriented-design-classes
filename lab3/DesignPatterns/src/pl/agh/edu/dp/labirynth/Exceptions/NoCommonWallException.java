package pl.agh.edu.dp.labirynth.Exceptions;

import pl.agh.edu.dp.labirynth.Models.Room;

public class NoCommonWallException extends Exception {
    private final Room r1;
    private final Room r2;

    public NoCommonWallException(Room r1, Room r2){
        this.r1 = r1;
        this.r2 = r2;
    }

    public String toString(){
        return "Room " + r1.getRoomNumber() + " and " + r2.getRoomNumber() + " don't have common wall";
    }
}
