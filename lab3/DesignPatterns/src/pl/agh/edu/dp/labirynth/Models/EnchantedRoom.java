package pl.agh.edu.dp.labirynth.Models;

public class EnchantedRoom extends Room {
    public EnchantedRoom(int number) {
        super(number);
    }

    @Override
    public void Enter(Player p) {
        System.out.println("You are in enchanted room!");
    }
}
