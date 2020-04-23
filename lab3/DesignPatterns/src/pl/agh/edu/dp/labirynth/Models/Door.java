package pl.agh.edu.dp.labirynth.Models;

public class Door extends MapSite {
    private final Room room1;
    private final Room room2;

    public Door(Room r1, Room r2){
        this.room1 = r1;
        this.room2 = r2;
    }

    @Override
    public void Enter(Player p){
        System.out.println("You went through the door.");
        Room otherSideRoom = getOtherSideRoom(p.getCurrentRoom());
        p.setCurrentRoom(otherSideRoom);
        otherSideRoom.Enter(p);
    }

    public Room getOtherSideRoom(Room r){
        if(r == room1){
            return room2;
        }else if(r == room2){
            return room1;
        }
        return null;
    }
}
