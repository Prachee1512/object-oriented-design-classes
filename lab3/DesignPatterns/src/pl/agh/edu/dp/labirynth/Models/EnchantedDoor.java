package pl.agh.edu.dp.labirynth.Models;

public class EnchantedDoor extends Door {
    public EnchantedDoor(Room r1, Room r2) {
        super(r1, r2);
    }

    @Override
    public void Enter(Player p) {
        System.out.println("You went through enchanted door");
    }
}
