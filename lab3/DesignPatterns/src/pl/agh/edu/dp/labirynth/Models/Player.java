package pl.agh.edu.dp.labirynth.Models;

public class Player {
    private Room currentRoom;
    private Boolean alive = Boolean.TRUE;

    public Player(){
    }

    public void kill(){
        alive = Boolean.FALSE;
    }

    public boolean isAlive(){
        return alive;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room r) {
        this.currentRoom = r;
    }
}
